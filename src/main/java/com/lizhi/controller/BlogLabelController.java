package com.lizhi.controller;

import com.lizhi.bean.BlogLabel;
import com.lizhi.bean.CURDParam;
import com.lizhi.bean.ResponseMessage;
import com.lizhi.service.CustomService;
import com.lizhi.service.impl.BlogLabelServiceImpl;
import com.lizhi.utils.StringUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签名称
 */
@RestController
@RequestMapping("/bloglabel")
public class BlogLabelController implements CustomController<BlogLabel, String> {
    @Resource
    private BlogLabelServiceImpl blogService;

    @Override
    public CustomService<BlogLabel, String> getService() {
        return blogService;
    }

    @GetMapping(path = "/getsonlabel")
    public ResponseMessage getClassBLabel(String labelAId) {
        if (StringUtil.isNullOrEmpty(labelAId)) {
            return ResponseMessage.error("传入参数有误");
        }

        List<BlogLabel> lables = this.getService().query(CURDParam.getInstans().where("parentId", labelAId));

        return ResponseMessage.ok(lables);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public ResponseMessage add(@RequestBody BlogLabel data) {
        data.setId(StringUtil.getUUID());
        int insert = this.getService().insert(data);
        if(insert == 0){
            return ResponseMessage.error("保存失败");
        }
        return ResponseMessage.ok(data);
    }
}