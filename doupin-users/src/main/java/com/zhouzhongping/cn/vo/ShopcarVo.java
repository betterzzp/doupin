package com.zhouzhongping.cn.vo;

import com.zhouzhongping.cn.entity.Shopcar;

import java.math.BigDecimal;

public class ShopcarVo extends Shopcar {
    private String goodName;
    private String goodPic;
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodPic() {
        return goodPic;
    }

    public void setGoodPic(String goodPic) {
        this.goodPic = goodPic;
    }
}
