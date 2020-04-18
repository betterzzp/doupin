package com.zhouzhongping.cn.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("shopcar")
public class Shopcar {
    @TableId("id")
    private String id;
    @TableField("user_id")
    private String userId;
    @TableField("goods_id")
    private String goodId;
    @TableField("number")
    private int number;
}
