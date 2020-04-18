package com.zhouzhongping.cn.controller;

import cn.zhouzhongping.CommonResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhouzhongping.cn.entity.Goods;
import com.zhouzhongping.cn.mapper.GoodsMapper;
import com.zhouzhongping.cn.vo.GoodVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("render")
public class DetailRender {
    @Autowired
    private GoodsMapper goodMapper;
    @RequestMapping( value = "/goodDetail/{goodsId}", method = RequestMethod.POST)
    public CommonResponse GetGoods(@PathVariable String goodsId){
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        List<GoodVo> goodsVo = goodMapper.selectGoodDetail(goodsId);
        CommonResponse cr = new CommonResponse();
        cr.setCode("10001");
        cr.setMessage("获取成功");
        cr.setSuccess(true);
        cr.setContent(goodsVo);
        return cr;
    }
}
