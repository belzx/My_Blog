package com.lizhi.controller;

import com.lizhi.bean.ResponseMessage;
import com.lizhi.bean.TFile;
import com.lizhi.scheduled.ScheduledIndex;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 博客首页
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    public static List<TFile> sowingMaps = new ArrayList<>();

    public static final TFile DEFAULT_SOWING_MAP = new TFile();

    static {
        DEFAULT_SOWING_MAP.setFileUrl("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2221334627,1101895271&fm=27&gp=0.jpg");
        Collections.synchronizedList(sowingMaps);
        sowingMaps.add(DEFAULT_SOWING_MAP);
        sowingMaps.add(DEFAULT_SOWING_MAP);
        sowingMaps.add(DEFAULT_SOWING_MAP);
    }

    @GetMapping(path = "/sowingmap")
    public ResponseMessage getSowingmap(){
        ResponseMessage<Object> ok = ResponseMessage.ok((Object) sowingMaps);
        return ok;
    }

}
