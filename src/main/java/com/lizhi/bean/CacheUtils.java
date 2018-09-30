package com.lizhi.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpSession;

public class CacheUtils {
	private Object obj;

	private static Map<String,Object> map = new HashMap();

	private int times;//系统里面保存的时间
	
	private int saveTime;//系统里面保存时间戳

	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public int getSaveTime() {
		return saveTime;
	}
	public void setSaveTime(int saveTime) {
		this.saveTime = saveTime;
	}
	public static void addSession(String sessionId,HttpSession session) {
		map.put(sessionId, session);
	}
	
	public static void cleanSession(String sessionId) {
		map.remove(sessionId);
	}
	
	public static void cleanAllSession(String sessionId) {
		Set<Entry<String, Object>> entrySet = map.entrySet();
		Iterator<Entry<String, Object>> iterator = entrySet.iterator();
		while(iterator.hasNext()) {
			Entry<String, Object> next = iterator.next();
			if(next.getValue() instanceof HttpSession) {
				iterator.remove();
			}
		}
	}
	
	public static  HttpSession getSession(String sessionId) {
		Object object = map.get(sessionId);
		if(object instanceof HttpSession) {
			return  (HttpSession)object;
		}
		return null;
		
	}
	
	public static  void setStringMap(String stringId,String data) {
		map.put(stringId, data);
	}
	
	public static  void setObjectMap(String stringId,Object data) {
		map.put(stringId, data);
	}
	
	public static  String getStringMap(String stringId) {
		return map.get(stringId)==null?null:map.get(stringId).toString();
	}
	
	public static  Object getObjectMap(String stringId) {
		return map.get(stringId);
	}
	
	
	
	public static Map<String,Object> getObjectByKey(String key) {
		Map<String,Object> maps = new HashMap();
		//得到所有包含key开头关键字的对象
		Set<Entry<String, Object>> entrySet = map.entrySet();
		Iterator<Entry<String, Object>> iterator = entrySet.iterator();
		while(iterator.hasNext()) {
			Entry<String, Object> next = iterator.next();
			if(next.getKey().startsWith(key)) {
				maps.put(next.getKey(), next.getValue());
			}
		}
		return maps;
	}
}	
