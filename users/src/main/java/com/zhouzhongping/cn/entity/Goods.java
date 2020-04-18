package com.zhouzhongping.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;

@TableName("goods")
public class Goods {
    @TableId(value = "id",type = IdType.UUID)
    private String id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "pic")
    private String pic;
    @TableField(value = "status")
    private String status;
    @TableField(value = "url")
    private String url;
    @TableField(value = "descption")
    private String descption;
    @TableField(value = "price")
    private BigDecimal price;
    @TableField(value = "promotionprice")
    private BigDecimal promotionprice ;
    @TableField(value = "tag")
    private String tag;
    @TableField(value = "banner")
    private String banner;

    public String getBigpic() {
        return bigpic;
    }

    public void setBigpic(String bigpic) {
        this.bigpic = bigpic;
    }

    @TableField(value = "isbanner")
    private String isbanner;
    @TableField(value = "bigpic")
    private String bigpic;
    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getIsbanner() {
        return isbanner;
    }

    public void setIsbanner(String isbanner) {
        this.isbanner = isbanner;
    }



    public String getDescption() {
        return descption;
    }

    public void setDescption(String descption) {
        this.descption = descption;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPromotionprice() {
        return promotionprice;
    }

    public void setPromotionprice(BigDecimal promotionprice) {
        this.promotionprice = promotionprice;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
