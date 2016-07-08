package com.fj.hiwetoptools.web.bean;

import com.fj.hiwetoptools.GsonUtil;

/**
 * 响应model
 * @author linyu
 *
 */
public class ResultModel {
	private boolean success = true;
	private String msg;
	private int code;
	private Object datas = null;

	public ResultModel(boolean success, BaseResultEnums respEnum) {
		this.success = success;
		this.msg = respEnum.getMsg();
		this.code = respEnum.getCode();
	}

	public ResultModel(boolean success, BaseResultEnums respEnum, Object datas) {
		this.success = success;
		this.msg = respEnum.getMsg();
		this.code = respEnum.getCode();
		this.datas = datas;
	}

	public Object getDatas() {
		return this.datas;
	}

	public void setDatas(Object datas) {
		this.datas = datas;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder("{\"success\":");
		strb.append(this.success ? "true" : "false")
		.append(",\"msg\":\"")
		.append(this.msg == null ? "" : this.msg)
		.append(",\"code\":\"")
		.append(this.code)
		.append("\",\"datas\":")
		.append(this.datas == null ? "{}" : GsonUtil.toJson(this.datas)).append("}");
		return strb.toString();
	}
}
