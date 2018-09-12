/**
 * Copyright [2016-2017] [yadong.zhang]
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lizhi.webSocked;

import org.springframework.stereotype.Component;

import com.lizhi.bean.CacheUtils;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * springboot Created by yadong.zhang on com.zyd.websocket
 *
 * @author: yadong.zhang
 * @date: 2017/11/23 13:42
 */
@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket {
	public static final String KEY_ID = "stringId:";
	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	 */
	private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();

	/**
	 * 与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	private Session session;

	/**
	 * 群发自定义消息
	 */
	public static void sendInfo(String message) {
		// 群发信息之前，先把信息保存在缓存中 用来记录
		for (MyWebSocket item : webSocketSet) {
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				continue;
			}
		}
	}

	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		// 第一次调用连接时，能查看之前的记录
		sendOldMessage();
		webSocketSet.add(this);
		// addOnlineCount();
		System.out.println("有新连接加入！当前在线人数为" + webSocketSet.size());
		try {
			sendMessage("当前共有" + webSocketSet.size() + " 位用户在线");
		} catch (IOException e) {
			System.out.println("IO异常");
		}
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);
		System.out.println("有一连接关闭！当前在线人数为" + webSocketSet.size());
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message
	 *            客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		// 打印信息到控制太
		System.out.println("来自客户端的消息:" + message);
		message = "(" + df.format(new Date()) + ")" + message;
		String stringId = KEY_ID + System.currentTimeMillis();
		;

		System.out.println(stringId);

		// 保存信息到缓存中
		CacheUtils.setStringMap(stringId, message);

		// 群发消息
		sendInfo(message);
	}

	/**
	 * 发生错误时调用
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}

	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}

	private void sendOldMessage() {
		try {
			// 先获取所有的信息
			Map<String, Object> objectByKey = CacheUtils.getObjectByKey(KEY_ID);
			
			// 排序
			Map<String, String> map = new TreeMap<String, String>(new Comparator<String>() {
				public int compare(String obj1, String obj2) {
					// 升排序
					return -obj2.compareTo(obj1);
				}
			});
			
			Set<Entry<String, Object>> entrySet = objectByKey.entrySet();
			Iterator<Entry<String, Object>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> next = iterator.next();
				map.put(next.getKey(), (String) next.getValue());
			}
			
			// 发送
			Set<String> keySet = map.keySet();
			Iterator<String> iter = keySet.iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				this.session.getBasicRemote().sendText(map.get(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
