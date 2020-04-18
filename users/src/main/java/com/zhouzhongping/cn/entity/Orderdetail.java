package com.zhouzhongping.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@TableName("orderdetail")
@Getter
@Setter
public class Orderdetail {
    @TableId(value = "id",type = IdType.UUID)
    private String id;
    @TableField(value = "order_id")
    private String orderId;
    @TableField(value = "good_id")
    private String goodId;
    @TableField(value = "good_number")
    private int goodNumber;
    @TableField(value = "payamount")
    private BigDecimal payamount;
}
