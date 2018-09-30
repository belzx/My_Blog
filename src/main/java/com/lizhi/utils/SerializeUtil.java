package com.lizhi.utils;

import org.springframework.util.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
    //序列化
    public static String serializeToString(Object obj) throws Exception{
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
         new ObjectOutputStream(byteOut).writeObject(obj);
        return byteOut.toString("ISO-8859-1");//此处只能是ISO-8859-1,但是不会影响中文使用


    }
    //反序列化
    public static Object deserializeToObject(String str) throws Exception{
        return   new ObjectInputStream(new ByteArrayInputStream(str.getBytes("ISO-8859-1"))).readObject();
    }
}

