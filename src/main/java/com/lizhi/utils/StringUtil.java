package com.lizhi.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class StringUtil  {

    public static synchronized String getUUID(){
        return  UUID.randomUUID().toString();
    }

    public static boolean isNullOrEmpty(String str){
        return str == null || str.length() ==0;
    }

    public static List<String> split(String content ,String regex ){
        if(isNullOrEmpty(content)){
            return new ArrayList<>();
        }
        String[] split = content.split(regex);
        if(split == null){
            return new ArrayList<>();
        }else {
            return Arrays.asList(split);
        }
    }
}
