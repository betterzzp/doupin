package com.zhouzhongping.cn.controller;

import cn.zhouzhongping.CommonResponse;
import com.zhouzhongping.cn.entity.Address;
import com.zhouzhongping.cn.entity.Users;
import com.zhouzhongping.cn.service.AddressService;
import com.zhouzhongping.cn.util.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @RequestMapping(value = "getUserAddress",method = {RequestMethod.POST})
    public CommonResponse getUserAddress(){
        CommonResponse cr = new CommonResponse();
        //获取用户ID
        Users user = UserInfoUtil.getLoginUser();
        List<Address>  addressList = addressService.selectByUserId(user.getId());
        cr.setCode("200");
        cr.setMessage("获取用户收货地址成功");
        cr.setContent(addressList);
        cr.setSuccess(true);
        return cr;
    }

    @RequestMapping(value = "addAddress",method = {RequestMethod.POST})
    public CommonResponse addAddress(@RequestBody Address address){
        CommonResponse cr = new CommonResponse();
        //获取用户ID
        Users user = UserInfoUtil.getLoginUser();
        address.setId(UUID.randomUUID().toString().replace("-",""));
        address.setUserId(user.getId());
        address.setIsdefault("0");
        addressService.insert(address);
        cr.setCode("200");
        cr.setMessage("添加用户地址成功");
        cr.setContent(null);
        cr.setSuccess(true);
        return cr;
    }

    @RequestMapping(value = "setDefaultAddress/{addressId}",method = {RequestMethod.POST})
    public CommonResponse addAddress(@PathVariable String addressId){
        CommonResponse cr = new CommonResponse();
        //获取用户ID
        //Users user = UserInfoUtil.getLoginUser();
        List<Address> addressList = addressService.selectById(addressId);
        addressList.get(0).setIsdefault("1");
        addressService.update(addressList.get(0));
        cr.setContent("设置成功");
        return cr;
    }
}
