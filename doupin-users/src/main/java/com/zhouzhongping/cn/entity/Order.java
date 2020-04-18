package com.zhouzhongping.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@TableName("orders")
public class Order {
    @TableId(value = "id",type = IdType.UUID)
    private String id;
    @TableField(value = "user_id")
    private String userId;
    @TableField(value = "payamount")
    private BigDecimal payAmount;
    @TableField(value = "paystatus")
    private int payStatus;
    @TableField(value = "createtime")
    private Date createtime;
    @TableField(value = "lastupdate")
    private Date lastupdate;
    @TableField(value = "number")
    private String number;
    @TableField(value = "address_Id")
    private String addressId;
}
