package com.zhouzhongping.cn.vo;

import com.zhouzhongping.cn.entity.GoodDetail;
import com.zhouzhongping.cn.entity.GoodMeasurement;
import com.zhouzhongping.cn.entity.GoodParam;
import com.zhouzhongping.cn.entity.Goods;

import java.util.List;

public class GoodVo extends Goods {
    private List<GoodDetail> goodDetail;
    private List<GoodParam> goodParam;

    public List<GoodDetail> getGoodDetail() {
        return goodDetail;
    }

    public void setGoodDetail(List<GoodDetail> goodDetail) {
        this.goodDetail = goodDetail;
    }

    public List<GoodParam> getGoodParam() {
        return goodParam;
    }

    public void setGoodParam(List<GoodParam> goodParam) {
        this.goodParam = goodParam;
    }

    public List<GoodMeasurement> getGoodsMeasurement() {
        return goodsMeasurement;
    }

    public void setGoodsMeasurement(List<GoodMeasurement> goodsMeasurement) {
        this.goodsMeasurement = goodsMeasurement;
    }

    private List<GoodMeasurement> goodsMeasurement;
}