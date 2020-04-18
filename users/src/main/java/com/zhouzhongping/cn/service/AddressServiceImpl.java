package com.zhouzhongping.cn.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhouzhongping.cn.entity.Address;
import com.zhouzhongping.cn.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@Repository
public class AddressServiceImpl implements  AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public void insert(Address address) {
        addressMapper.insert(address);
    }
    @Override
    public void update(Address address) {
        addressMapper.updateById(address);
    }

    @Override
    public void delete(String id) {
        addressMapper.deleteById(id);
    }

    @Override
    public List<Address> selectByUserId(String userId) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Address::getUserId,userId);
        List<Address> addressList = addressMapper.selectList(queryWrapper);
        return addressList;
    }

    @Override
    public List<Address> selectById(String addressId) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Address::getId,addressId);
        List<Address> addressList = addressMapper.selectList(queryWrapper);
        return addressList;
    }
}
