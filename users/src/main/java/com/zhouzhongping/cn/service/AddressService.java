package com.zhouzhongping.cn.service;

import com.zhouzhongping.cn.entity.Address;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public interface AddressService {
    public void insert(Address address);
    public void update(Address address);
    public void delete(String id);
    public List<Address> selectByUserId(String userId);
    public List<Address> selectById(String userId);
}
