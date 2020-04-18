package com.zhouzhongping.cn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhouzhongping.cn.entity.Goods;
import com.zhouzhongping.cn.entity.Shopcar;
import com.zhouzhongping.cn.entity.ShopcarParam;
import com.zhouzhongping.cn.entity.Users;
import com.zhouzhongping.cn.mapper.GoodsMapper;
import com.zhouzhongping.cn.mapper.ShopcarMapper;
import com.zhouzhongping.cn.util.UserInfoUtil;
import com.zhouzhongping.cn.vo.ShopcarVo;

import cn.zhouzhongping.CommonResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("shopcar")
@Slf4j
public class ShopcarController {
    @Autowired
    private ShopcarMapper shopcarMapper;
    @Autowired
    private GoodsMapper goodsMapper;


    @RequestMapping(value = "getShopCar",method = {RequestMethod.POST})
    public CommonResponse getShopCar() {
        Users user = UserInfoUtil.getLoginUser();
        QueryWrapper<Shopcar > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Shopcar::getUserId,user.getId());
        List<Shopcar> shopCarsList = shopcarMapper.selectList(queryWrapper);
        CommonResponse cr = new CommonResponse();
        cr.setCode("10001");
        cr.setMessage("登录成功");
        cr.setSuccess(true);
       // cr.setContent(goodsList);
        return cr;
    }
    @RequestMapping(value = "addToShopCar",method = {RequestMethod.POST})
    public CommonResponse addToShopCar(@RequestBody  ShopcarParam shopcarParam) {
        Users user = UserInfoUtil.getLoginUser();
        QueryWrapper<Shopcar> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Shopcar::getGoodId,shopcarParam.getGoodId());
        List<Shopcar> shopcarsList = shopcarMapper.selectList(queryWrapper);
        if(shopcarsList.size()>0){
            shopcarsList.get(0).setNumber(shopcarsList.get(0).getNumber()+Integer.valueOf(shopcarParam.getNumber()));
            shopcarMapper.updateById(shopcarsList.get(0));
        }else{
            Shopcar shopCar = new Shopcar();
            shopCar.setId(UUID.randomUUID().toString());
            shopCar.setUserId(user.getId());
            shopCar.setNumber(Integer.valueOf(shopcarParam.getNumber()));
            shopCar.setGoodId(shopcarParam.getGoodId());
            shopcarMapper.insert(shopCar);
        }
        CommonResponse cr = new CommonResponse();
        cr.setCode("200");
        cr.setMessage("加入购物车成功");
        cr.setSuccess(true);
        cr.setContent(null);
        return cr;
    }
    /**
     * 获取购物车数量
     * @return
     */
    @RequestMapping(value = "getShopcarNumber",method = {RequestMethod.POST})
    public CommonResponse getShopCarNumber(){
        //获取到用户的信息
        Users user = UserInfoUtil.getLoginUser();
        CommonResponse cr = new CommonResponse();
        if(user == null){
            cr.setCode("200");
            cr.setMessage("尚未登录");
            cr.setSuccess(true);
            cr.setContent(0);
            return cr;
        }
        QueryWrapper<Shopcar> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Shopcar::getUserId,user.getId());
        List<Shopcar> shopcarsList = shopcarMapper.selectList(queryWrapper);
        Integer number = 0;
        for(Shopcar shopcar:shopcarsList) {
            number = number + shopcar.getNumber();
        }
        cr.setCode("200");
        cr.setMessage("获取购物车商品数量成功");
        cr.setSuccess(true);
        cr.setContent(number);
        return cr;
    }
    @RequestMapping(value = "getShopCarList",method = {RequestMethod.POST})
    public CommonResponse getShopcarList(){
    	long  start = System.currentTimeMillis();
        Users user = UserInfoUtil.getLoginUser();
        long  end = System.currentTimeMillis();
        log.info("查询用户用时："+(end-start));
        QueryWrapper<Shopcar> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Shopcar::getUserId,user.getId());

        //查询到购物车列表
        List<Shopcar> shopcarsList = shopcarMapper.selectList(queryWrapper);

        List<String> shopIdList = new ArrayList<String>();
        for(Shopcar shopcar:shopcarsList){
            shopIdList.add(shopcar.getGoodId());
        }

        QueryWrapper<Goods> goodWrapper = new QueryWrapper<>();
        goodWrapper.lambda().in(Goods::getId,shopIdList);

        //查询到购买的商品列表
        List<Goods> goodsList = goodsMapper.selectList(goodWrapper);

        List<ShopcarVo> result = new ArrayList<ShopcarVo>();

        for(Shopcar shopcar:shopcarsList){
            ShopcarVo shopcarVo = new ShopcarVo();
            shopcarVo.setId(shopcar.getId());
            shopcarVo.setNumber(shopcar.getNumber());
            for(Goods goods:goodsList) {
                if(shopcar.getGoodId().equals(goods.getId())){
                    shopcarVo.setGoodName(goods.getName());
                    shopcarVo.setGoodPic(goods.getPic());
                    shopcarVo.setPrice(goods.getPrice());
                }
            }
            result.add(shopcarVo);
        }
        CommonResponse cr = new CommonResponse();
        cr.setCode("200");
        cr.setMessage("获取购物车商品数量成功");
        cr.setSuccess(true);
        cr.setContent(result);
        long  end1 = System.currentTimeMillis();
        log.info("整个请求用时："+(end1-start));
        return cr;
    }
    @RequestMapping(value = "/deleteShopcarMenu/{shopCarId}",method = {RequestMethod.POST})
    public CommonResponse deleteShopCar(@PathVariable String shopCarId) {
        shopcarMapper.deleteById(shopCarId);
        CommonResponse cr = new CommonResponse();
        cr.setCode("200");
        cr.setMessage("删除成功");
        cr.setSuccess(true);
        cr.setContent(null);
        return cr;
    }
}
