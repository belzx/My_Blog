package com.lizhi.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lizhi.bean.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lizhi.common.FileUtil;
import com.lizhi.common.FileUtilTX;
import com.lizhi.service.IImageService;


/**
 * Created by xinghailong on 2017/7/21.
 */
@Controller
@RequestMapping(value="/file")
public class MultilFileController {
	
	@Value("${uploadTX.bucketName}")
	private  String bucketName;
	
	@Value("${uploadTX.bucketArea}")
	private  String bucketArea;
	
	@Value("${uploadTX.secretId}")
	private  String secretId ;
	
	@Value("${uploadTX.secretKey}")
	private  String secretKey ;
	
	@Value("${uploadTX.prixx}")
	private  String prixx ;
	
	@Resource
	private IImageService imageService;
	

    /**
     * 处理头像文件的上传
     * @param file
     * @param request
     * @return
     * @throws IOException 
     */
    @RequestMapping(value="/uploadIco", method = RequestMethod.POST)
    @ResponseBody 
    public ResponseBean uploadImg(@RequestParam("file") MultipartFile file,
            HttpServletRequest request) throws IOException {
        String contentType = file.getContentType().split("/")[1];
        String fileName = file.getOriginalFilename();
        Md5Hash md5Hash = new Md5Hash(file.getInputStream());
        String fileURL = FileURLs.FILE_ICO+md5Hash.toString()+"."+contentType;
        String fileRealURL = prixx+fileURL;
        HttpSession session = request.getSession();
        /*
         * 这里是验证文件的过程，过程省略
         */
        
        System.out.println(fileURL);
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
        
        FileUtilTX.instanceCosClient(secretId, secretKey, bucketName, bucketArea);
        FileUtilTX.upload(FileURLs.FILE_ICO+md5Hash.toString()+"."+contentType, file.getBytes());
        FileUtilTX.shutdown();
        
        //保存图片数据到数据库中
        TFile tFile = new TFile();
        
        Object attribute = session.getAttribute("user");
        int id = 0;
        if(attribute instanceof User) {
        	id = ((User)attribute).getId();
        	((User)attribute).setIco(fileRealURL);
        }
        tFile.setCreatorUserid(id);
        tFile.setFileUrl(fileRealURL);
        tFile.setFileType(FileType.FILE_TYPE_ICO);
        tFile.setMd5(md5Hash.toString());
        
        imageService.tfileInsertOrUpdate(tFile);
        //路径保存到数据库中,要考虑事务
        
        ResponseBean responseBean = new ResponseBean();
        responseBean.ok(fileRealURL);
        return responseBean; 
    }
}
