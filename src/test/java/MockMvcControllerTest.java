//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.env.Environment;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * 利用mockmvc测试controller端口的
// * 有点类似httpclient
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class MockMvcControllerTest {
//
//    @Autowired
//    private Environment environment;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testAirplaneAdd() throws Exception {
//        System.out.println(mockMvc);
//
//        String responseString = mockMvc.perform(
//                get("/jpa/2")    //请求的url,请求的方法是get
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //数据的格式
////                       .param("pcode","root")         //添加参数
//        )
////                .andExpect(status().isOk())//返回的状态是200
//                .andExpect(status().is(302))//返回的状态是302
//                .andDo(print())         //打印出请求和相应的内容
//                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
//
//        System.out.println("返回值：" + responseString);
//    }
//
//}
