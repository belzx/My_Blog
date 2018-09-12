package com.lizhi.scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * 调度功能
 * 需要配置@EnableScheduling//启动定时任务配置 即可
 * @author lx
 *
 */
@Component
public class ScheduledTest {

    @Scheduled(cron="0 0/2 8-20 * * ?") 
    public void executeFileDownLoadTask() {

        // 间隔2分钟,执行工单上传任务     
        Thread current = Thread.currentThread();  
        System.out.println("定时任务1:"+current.getId());
    }

    @Scheduled(cron="0 0/1 8-20 * * ?") 
    public void executeUploadTask() {
        // 间隔1分钟,执行工单上传任务              
        Thread current = Thread.currentThread();  
        System.out.println("定时任务2:"+current.getId());
    }

    @Scheduled(cron="0 0/3 5-23 * * ?") 
    public void executeUploadBackTask() {

        // 间隔3分钟,执行工单上传任务                          
        Thread current = Thread.currentThread();  
        System.out.println("定时任务3:"+current.getId());
    }    
    
    	//  每分钟启动
	  @Scheduled(cron = "0 0/1 * * * ?")
	  public void timerToNow(){
	      System.out.println("定时任务test");
	  }

}