package com.zhouzhongping.cn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhouzhongping.cn.entity.Banner;
import com.zhouzhongping.cn.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BannerMapper  extends BaseMapper<Banner> {
}
