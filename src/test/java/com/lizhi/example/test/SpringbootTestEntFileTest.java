package com.lizhi.example.test;

import com.lizhi.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by lightClouds917
 * Date 2018/2/2
 * Description:测试类
 */

@RunWith(SpringRunner.class)
@SpringBootTest
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class SpringbootTestEntFileTest {
    private Logger log =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    RedisService redisService;

    //@Ignore("not ready yet")
    @Test
    public void testGetEntFileById(){
        System.out.println(redisService);
    }

}