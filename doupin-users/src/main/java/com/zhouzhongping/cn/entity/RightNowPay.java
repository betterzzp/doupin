package com.zhouzhongping.cn.entity;

import java.math.BigDecimal;

public class RightNowPay {
	private String menuId;
	private Integer number;
	private BigDecimal payamount;
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public BigDecimal getPayamount() {
		return payamount;
	}
	public void setPayamount(BigDecimal payamount) {
		this.payamount = payamount;
	}
}
