package com.ufo.judicature.Entity;

import com.google.gson.annotations.SerializedName;

/***
 * 
 * 请求结果
 * 
 * @author lsd
 *
 */

public abstract class ServiceResult {
	@SerializedName("error")
	public boolean error;

	@SerializedName("msg")
	public String msg;

	public boolean getError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
