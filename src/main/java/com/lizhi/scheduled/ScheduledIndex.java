package com.lizhi.scheduled;

import com.lizhi.bean.CURDParam;
import com.lizhi.bean.TFile;
import com.lizhi.controller.IndexController;
import com.lizhi.service.ITFileService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzx
 */
@Component
public class ScheduledIndex {

    @Resource
    private ITFileService itFileService;

    //  每分钟启动
    @Scheduled(cron = "0 0 */2 * * ?")
    public void switchSowingMap() {
        List<TFile> maps = itFileService.query(CURDParam.getInstans().limit(0, 3));
        for (int i = 0; i < 3; i++) {
            if (i  < maps.size()) {
                if (maps.get(i) != null) {
                    IndexController.sowingMaps.remove(i);
                    IndexController.sowingMaps.add(maps.get(i));
                }
            }else {

            }

        }

    }
}