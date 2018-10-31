//package com.lizhi.config;
//
//import com.lizhi.core.WCCUtils;
//import com.lizhi.utils.StringUtil;
//import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
//import org.springframework.stereotype.Component;
//
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Properties;
//import java.util.Set;
//
//public class MyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
//    @Override
//    protected void convertProperties(Properties props) {
//        Set<Map.Entry<Object, Object>> entries = props.entrySet();
//        Iterator<Map.Entry<Object, Object>> iterator = entries.iterator();
//        while(iterator.hasNext()){
//            Map.Entry<Object, Object> entry = iterator.next();
//            if(entry.getValue() == null || "".equals(entry.getValue().toString())){
//                continue;
//            }else {
//                String s = entry.getValue().toString();
//                if(s.startsWith("wcc:")){
//                    String substring = s.substring(4);
//                    String decode = WCCUtils.Decode(substring);
//                    System.out.println(decode);
//                    entry.setValue(decode);
//                }
//            }
//        }
//        super.convertProperties(props);
//    }
//}