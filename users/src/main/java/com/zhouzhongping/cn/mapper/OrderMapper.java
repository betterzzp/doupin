package com.zhouzhongping.cn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhouzhongping.cn.entity.Order;

@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {
}
