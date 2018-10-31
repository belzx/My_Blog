package com.lizhi.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import com.lizhi.bean.*;
import com.lizhi.exception.LZException;
import com.lizhi.opration.ImageEnum;
import com.lizhi.service.CustomService;
import com.lizhi.service.ITFileService;
import com.lizhi.utils.StringUtil;
import com.lizhi.utils.WebUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lizhi.utils.FileUtilTX;


/**
 * Created by xinghailong on 2017/7/21.
 */
@RestController
@RequestMapping(value = "/file")
@RequiresAuthentication
public class MultilFileController implements CustomController<TFile, String> {
    private static Logger log = LoggerFactory.getLogger(MultilFileController.class);

    @Resource
    private ITFileService itFileService;

    @Override
    public CustomService<TFile, String> getService() {
        return itFileService;
    }

    @Value("${uploadTX.bucketName}")
    private String bucketName;

    @Value("${uploadTX.bucketArea}")
    private String bucketArea;

    @Value("${uploadTX.secretId}")
    private String secretId;

    @Value("${uploadTX.secretKey}")
    private String secretKey;

    @Value("${uploadTX.prixx}")
    private String prixx;

    @Value("${uploadTX.profile}")
    private String profile;

    /**
     * 处理图片文件的上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseMessage uploadImg(@RequestParam("file") MultipartFile file) throws IOException {
        User onlineUser = WebUtil.getOnlineUser();
        if (onlineUser == null) {
            return ResponseMessage.ok("用户验证失败，刷新页面重新登录");
        }
        String name = file.getOriginalFilename();
        if(StringUtil.isNullOrEmpty(name)){
            return ResponseMessage.error("名称不能为空");
        }

        if(!name.contains(".")){
            return ResponseMessage.error("非法名称");
        }

        String[] split = name.split("\\.");
        if(split.length != 2){
            return ResponseMessage.error("非法名称");
        }

        if( !ImageEnum.isExist(split[1]) ){
            return ResponseMessage.error("非法文件类型");
        }


        //检验文件名称的合法性
        String md5Hash = new Md5Hash(file.getInputStream()).toString();
        String key =profile+ split[1] + "/" + md5Hash + "." + split[1];
        String fileRealURL = prixx +  key;
        TFile tFile = this.getService().selectSingle(CURDParam.getInstans().where("md5", md5Hash));


        if(tFile == null){

            FileUtilTX.instanceCosClient(secretId, secretKey, bucketName, bucketArea);
            FileUtilTX.upload(key, file.getBytes());
            FileUtilTX.shutdown();

            //保存图片数据到数据库中
            tFile = new TFile();
            tFile.setId(StringUtil.getUUID());
            tFile.setFileUrl(fileRealURL);
            tFile.setFileType(split[1].toLowerCase());
            tFile.setMd5(md5Hash);
            tFile.setLinknum(1);
            tFile.setCreatorUserid(onlineUser.getId());
            tFile.setSource(TFile.SOURCE_LOCAL);
            itFileService.insert(tFile);
        }else {
            if(tFile.getCreatorUserid().equals(onlineUser.getId())){
                return ResponseMessage.error("不能上传已经存在的图片");
            }

            tFile.setLinknum(tFile.getLinknum()+1);
            itFileService.update(tFile);

            tFile.setId(StringUtil.getUUID());
            tFile.setCreatorUserid(onlineUser.getId());
            itFileService.insert(tFile);
        }

        ResponseMessage<Object> ok = ResponseMessage.ok();
        ok.setMessage(fileRealURL);
        return ok;
    }

    @DeleteMapping(
            path = {"/{id:.+}"}
    )
    public ResponseMessage deleteByPrimaryKey(@PathVariable String id) {

        try {
            TFile tFile = this.getService().selectByPK(id);

            if (tFile == null) throw new LZException("数据库不存在此图片");

            //查看有没有相同md5
            this.getService().deleteByPK(id);

            List<TFile> tFile1 = this.getService().select(CURDParam.getInstans().where("md5", tFile.getMd5())).getData();
            if(tFile1 == null || tFile1.isEmpty()){
                if(tFile.getSource() == TFile.SOURCE_LOCAL){
                    FileUtilTX.instanceCosClient(secretId, secretKey, bucketName, bucketArea);
                    FileUtilTX.delete(tFile.getFileUrl());
                    FileUtilTX.shutdown();
                }
            }else {
                tFile1.stream().forEach(d ->{
                    if((d.getLinknum()-1) <= 0){
                        this.getService().deleteByPK(d.getId());
                    }else {
                        d.setLinknum(d.getLinknum()-1);
                        this.getService().update(d);
                    }
                });
            }
        } catch (LZException e) {
            log.warn("{}",id,e);
        } catch (Exception e) {
            log.error("操作删除图片发生问题",e);
        }

        return ResponseMessage.ok(this.getService().deleteByPK(id));
    }

}
