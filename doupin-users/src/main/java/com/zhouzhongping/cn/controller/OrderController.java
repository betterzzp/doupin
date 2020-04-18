package com.zhouzhongping.cn.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhouzhongping.cn.entity.Address;
import com.zhouzhongping.cn.entity.Goods;
import com.zhouzhongping.cn.entity.Order;
import com.zhouzhongping.cn.entity.Orderdetail;
import com.zhouzhongping.cn.entity.RightNowPay;
import com.zhouzhongping.cn.mapper.AddressMapper;
import com.zhouzhongping.cn.mapper.GoodsMapper;
import com.zhouzhongping.cn.mapper.OrderMapper;
import com.zhouzhongping.cn.mapper.OrderdetailMapper;
import com.zhouzhongping.cn.util.AlipayUtil;
import com.zhouzhongping.cn.util.UserInfoUtil;
import com.zhouzhongping.cn.vo.OrderVo;

import cn.hutool.core.lang.UUID;
import cn.zhouzhongping.CommonResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("pay")
@Slf4j
public class OrderController {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private OrderdetailMapper orderdetailMapper;
	@Autowired
	private GoodsMapper  goodsMapper;
	@RequestMapping(value = "pay",method = {RequestMethod.POST})
    public void GetGoods(@RequestBody RightNowPay rigthNowPay,HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		try {
			Order order = new Order();
			order.setId(UUID.randomUUID().toString().replace("-", ""));
			order.setCreatetime(new Date());
			order.setLastupdate(new Date());
			order.setNumber("superme"+new Date().getTime());
			order.setPayAmount(rigthNowPay.getPayamount());
			order.setPayStatus(0);
			order.setUserId(UserInfoUtil.getLoginUser().getId());
			//查询用户默认地址
			QueryWrapper<Address> queryWrapper = new QueryWrapper<Address>();
			queryWrapper.eq("user_id", UserInfoUtil.getLoginUser().getId());
			List<Address> addressList = addressMapper.selectList(queryWrapper);
			//设置订单商品地址
			order.setAddressId(addressList.get(0).getId());
			orderMapper.insert(order);
			Orderdetail orderdetail = new Orderdetail();
			orderdetail.setId(UUID.randomUUID().toString().replace("-", ""));
			orderdetail.setGoodId(rigthNowPay.getMenuId());
			orderdetail.setOrderId(order.getId());
			orderdetail.setGoodNumber(rigthNowPay.getNumber());
			orderdetail.setPayamount(rigthNowPay.getPayamount());
			orderdetailMapper.insert(orderdetail);
			AlipayUtil.doPay(httpRequest, httpResponse,order);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@RequestMapping(value = "payStatusNotify",method = {RequestMethod.POST})
    public void payStatusNotify(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		//out_trade_no
		//trade_status   TRADE_SUCCESS
		log.info("报告老板，收到支付宝回调");
		log.debug(httpRequest.toString());
		log.debug("订单号是"+httpRequest.getParameter("out_trade_no"));
		log.debug("订单状态是"+httpRequest.getParameter("trade_status"));
		log.info("报告老板，收到支付宝回调");
		if("TRADE_SUCCESS".equals(httpRequest.getParameter("trade_status"))) {
			//根据orderNumber从Redis中查询订单，减少数据库的访问
			String orderNumber = httpRequest.getParameter("out_trade_no");
		}
	}
	@RequestMapping(value = "get/user/order",method = {RequestMethod.POST})
    public CommonResponse GetUserOrderGoods(){
		CommonResponse cr = new CommonResponse();
		try {
			String userId = UserInfoUtil.getLoginUser().getId();
			QueryWrapper<Order> queryWrapper = new QueryWrapper<Order>();
			queryWrapper.eq("user_id", userId);
			List<Order> orderList = orderMapper.selectList(queryWrapper);
			List<String> orderIds = new ArrayList<String>();
			for(Order order:orderList) {
				orderIds.add(order.getId());
			}
			//查询订单
			QueryWrapper<Orderdetail> orderWrapper = new QueryWrapper<Orderdetail>();
			orderWrapper.in("order_id", orderIds);
			List<Orderdetail> orderDetailList = orderdetailMapper.selectList(orderWrapper);
			//商品ID查询
			List<String> menuId = new ArrayList<String>();
			for(Orderdetail orderDetail:orderDetailList) {
				menuId.add(orderDetail.getGoodId());
			}
			//商品查询
			QueryWrapper<Goods> queryWrapperA = new QueryWrapper<Goods>();
			queryWrapperA.in("id", menuId);
			List<Goods> goodsList =  goodsMapper.selectList(queryWrapperA);
			Map<String,Object> goodMap = new HashMap<String,Object>();
			for(Goods good:goodsList) {
				goodMap.put(good.getId(), good);
			}
			List<OrderVo> response = new ArrayList<OrderVo>();
			for(Orderdetail orderDetail:orderDetailList ) {
				OrderVo vo = new OrderVo();
				Goods good = (Goods) goodMap.get(orderDetail.getGoodId());
				//商品名称
				vo.setName(good.getName());
				vo.setPic(good.getPic());
				vo.setPrice(good.getPrice());
				vo.setGoodsNumber(orderDetail.getGoodNumber());
				vo.setTotal(orderDetail.getPayamount());
				response.add(vo);
			}
			cr.setCode("200");
			cr.setContent(response);
			cr.setMessage("查询成功");
			cr.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  cr;
    }
	
}
