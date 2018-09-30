import com.lizhi.bean.User;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.util.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeDemo {
    //测试入口
    public static void main(String[] args) throws Exception{
//        User student=new User();
//        student.setId(1);
//        student.setUsername("王老三");
//        Object o = deserializeToObject(serializeToString("1"));
//        System.out.println(o);//输出王老三

        Object o = SerializationUtils.deserialize(SerializationUtils.serialize("1"));
        System.out.println(o);//输出王老三
        System.out.println(new Md5Hash("11","11").toString());
    }
    //序列化
    public static String serializeToString(Object obj) throws Exception{
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
        objOut.writeObject(obj);  
        String str = byteOut.toString("ISO-8859-1");//此处只能是ISO-8859-1,但是不会影响中文使用
        return str;
    }
    //反序列化
    public static Object deserializeToObject(String str) throws Exception{
         ByteArrayInputStream byteIn = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
         ObjectInputStream objIn = new ObjectInputStream(byteIn);
         Object obj =objIn.readObject();  
         return obj;  
    }
}
