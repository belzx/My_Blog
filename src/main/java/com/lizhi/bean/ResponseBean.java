package com.lizhi.bean;

import java.util.List;

public class ResponseBean<T> {
	private String code;
	private String msg;
	private T data;
	private int count;
	
	public ResponseBean() {
        super();
    }

    public ResponseBean(String code, String msg) {//讲道理应该调用此构造方法
        super();
        this.code = code;
        this.msg = msg;
    }

    public ResponseBean(String code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    
    public ResponseBean(String code,String msg,T data,int count){//不讲道理的调用了此方法
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count=count;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
    public void ok(String msg) {
    	this.code="200";
    	this.msg="sucess";
    	this.msg=msg;
    }
    public static ResponseBean ok() {
    	ResponseBean responseBean = new ResponseBean();
    	responseBean.ok(null);
    	return responseBean;
    }
}
