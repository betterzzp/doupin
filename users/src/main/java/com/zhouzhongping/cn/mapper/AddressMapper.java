package com.zhouzhongping.cn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhouzhongping.cn.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AddressMapper extends BaseMapper<Address> {
}
