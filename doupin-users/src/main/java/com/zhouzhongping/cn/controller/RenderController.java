package com.zhouzhongping.cn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhouzhongping.cn.entity.Goods;
import com.zhouzhongping.cn.mapper.BannerMapper;
import com.zhouzhongping.cn.mapper.GoodsMapper;

import cn.zhouzhongping.CommonResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("render")
@Slf4j
public class RenderController {
    @Autowired
    private BannerMapper bannerMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @RequestMapping(value = "goods",method = {RequestMethod.POST})
    public CommonResponse GetGoods(){
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        //Users user = UserUtil.getLoginUser();
        queryWrapper.lambda().eq(Goods::getIsbanner,"0");
        List<Goods> goodsList = goodsMapper.selectList(queryWrapper);
        CommonResponse cr = new CommonResponse();
        cr.setCode("10001");
        cr.setMessage("登录成功");
        cr.setSuccess(true);
        cr.setContent(goodsList);
        log.info("查询成功");
        log.debug("查询成功");
        log.error("查询成功");
        log.warn("查询成功");
        return cr;
    }
    @RequestMapping(value = "banner",method = {RequestMethod.POST})
    public CommonResponse bannerList(){
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Goods::getIsbanner,"1");
        List<Goods> goodsList = goodsMapper.selectList(queryWrapper);
        CommonResponse cr = new CommonResponse();
        cr.setCode("10001");
        cr.setMessage("登录成功");
        cr.setSuccess(true);
        cr.setContent(goodsList);
        log.info("查询成功");
        log.debug("查询成功");
        log.error("查询成功");
        log.warn("查询成功");
        return cr;
    }
}
