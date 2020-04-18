package com.zhouzhongping.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("address")
public class Address {
    @TableId(value = "id",type = IdType.UUID)
    private String id;
    @TableField(value = "user_id")
    private String userId;
    @TableField(value = "receiveman")
    private String receiveman;
    @TableField(value = "receivePhone")
    private String receivePhone;
    @TableField(value = "province_code")
    private String provinceCode;
    @TableField(value = "municipal_code")
    private String municipalCode;
    @TableField(value = "district_code")
    private String districtCode;
    @TableField(value = "detail")
    private String detail;
    @TableField(value = "isdefault")
    private String isdefault;
    @TableField(value = "province_name")
    private String provinceName;
    @TableField(value = "municipal_name")
    private String municipalName;
    @TableField(value = "district_name")
    private String districtName;
}
