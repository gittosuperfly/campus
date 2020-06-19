package com.cai.campus.push.utils;

/**
 * 推送范围选项
 * hlliu@youzu.com
 * 推送范围:1广播；2别名；3标签；4regid；5地理位置；6用户分群
 */
public enum TargetEnum {

	ALL(1,"广播"),
	PHONE(2,"手机号"),
	GROUP(3,"群组");

	private int code;

	private String desc;

	TargetEnum(int code ,String desc){
		this.desc = desc;
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}


}
