package com.example.recycling.commons.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class R implements Serializable {
	private static final long serialVersionUID = 1L;

	private int code;
	private Map<String, Object> data;
	private String msg;
	private String sign;
	private Date reqtime;

	public static R newInstance() {
		R instance = new R();
		instance.code = 0;
		instance.msg = "操作成功！";
		instance.setReqtime(new Date());
		return instance;
	}

	public static R ok() {
		return newInstance();
	}

	public int getCode() {
		return this.code;
	}

	public Map<String, Object> getData() {
		return this.data;
	}

	public String getMsg() {
		return msg;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Date getReqtime() {
		return reqtime;
	}

	public void setReqtime(Date reqtime) {
		this.reqtime = reqtime;
	}

	public R code(int code) {
		this.code = code;
		return this;
	}

	public R msg(String msg) {
		this.msg = msg;
		return this;
	}

	public R put(String key, Object value) {
		if (this.data == null) {
			this.data = new HashMap<String, Object>();
		}
		this.data.put(key, value);
		return this;
	}

	public R putAll(Map<String, Object> map) {
		if (this.data == null) {
			this.data = new HashMap<String, Object>();
		}
		this.data.putAll(map);
		return this;
	}

	public String json() {
		return JSON.toJSONString(this);
	}

	public void json(Writer writer) {
		JSON.writeJSONString(writer, this, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty);
	}

	public R tokenFailure() {
		this.code = 6666;
		this.msg = "token已过期！";
		return this;
	}

	public R error() {
		this.code = 9999;
		this.msg = "操作失败！";
		return this;
	}

	public R error(String msg) {
		this.code = 9999;
		if (msg == null || msg.trim().length() == 0 || "null".equals(msg)) this.msg = "未知错误！";
		else {
			this.msg = msg;
		}
		return this;
	}

	public R error(int code, String msg) {
		this.code = code;
		this.msg = msg;
		return this;
	}


}