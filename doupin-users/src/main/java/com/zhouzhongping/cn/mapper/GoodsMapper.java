package com.zhouzhongping.cn.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhouzhongping.cn.entity.Goods;
import com.zhouzhongping.cn.entity.Order;
import com.zhouzhongping.cn.vo.GoodVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsMapper extends BaseMapper<Goods> {
    public List<GoodVo> selectGoodDetail(@Param("id") String id);
}
