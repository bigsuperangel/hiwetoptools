package com.fj.hiwetoptools.web.bean;

/**
 * 业务响应
 * @author linyu
 *
 */
public enum BizResultEnums implements BaseResultEnums{
	
	SUCCESS(1001,"操作成功"),
	FAILURE(9999,"操作失败");
	
	private int code;
	private String msg;
	
	BizResultEnums(int code,String msg){
		this.code = code;
		this.msg = msg;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}
}
