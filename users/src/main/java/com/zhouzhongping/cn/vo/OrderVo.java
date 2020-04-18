package com.zhouzhongping.cn.vo;

import java.math.BigDecimal;

import com.zhouzhongping.cn.entity.Goods;

public class OrderVo extends Goods {
	private Integer goodsNumber;
	private BigDecimal total;
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Integer getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
}
