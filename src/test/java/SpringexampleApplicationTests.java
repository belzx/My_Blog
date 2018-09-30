import com.lizhi.Application;
import com.lizhi.bean.User;
import com.lizhi.service.RedisService;
import com.lizhi.utils.SerializeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.ObjectInputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringexampleApplicationTests {

    @Resource
    RedisService redisService;

    @Test
    public void contextLoads() {
        System.out.println(redisService);
        redisService.set("1234567890123:%sMark",new User());
        System.out.println((User)redisService.get("1234567890123:%sMark"));
        try {
redisService.remove("1234567890123:11");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
