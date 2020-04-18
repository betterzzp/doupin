package com.zhouzhongping.cn.mapper;

import com.zhouzhongping.cn.entity.Users;
import com.zhouzhongping.cn.vo.GoodVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GoodsMapperT {
    @Autowired
    private GoodsMapper goodMapper;
    @Test
    public void insert(){
        String id = "1795cfad5eb04c6f985332b7efb7319e";
        List<GoodVo> goodVo = goodMapper.selectGoodDetail(id);
        System.out.println(goodVo);
    }
}
