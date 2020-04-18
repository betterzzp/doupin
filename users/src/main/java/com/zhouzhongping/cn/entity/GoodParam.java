package com.zhouzhongping.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

public class GoodParam {
    @TableId(value = "id",type = IdType.UUID)
    private String id;
    @TableField(value = "url")
    private String url;
    @TableField(value = "sort")
    private String sort;
    @TableField(value = "good_id")
    private String goodId;
    public String getId() {
        return id;
    }
    public String getUrl() {
        return url;
    }
    public String getSort() {
        return sort;
    }
    public String getGoodId() {
        return goodId;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }
}
