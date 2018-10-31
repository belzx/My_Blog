package com.lizhi;

import com.lizhi.conf.LZXAutoConfiguration;
import jdk.internal.instrumentation.InstrumentationTarget;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;
import java.util.Iterator;


@EnableScheduling//启动定时任务配置
@SpringBootApplication
//指定扫描的mapper接口所在的包
@MapperScan(basePackages ="com.lizhi.mybatis.mapper")
@Import(LZXAutoConfiguration.class)
@ImportResource("classpath:context.xml")
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
//        MutablePropertySources propertySources = run.getEnvironment().getPropertySources();
//        Iterator<PropertySource<?>> iterator = propertySources.iterator();
//        while (iterator.hasNext()){
//            PropertySource<?> next = iterator.next();
//            System.out.println(next.getName()+":"+next.getSource().toString());
//        }
    }
}
