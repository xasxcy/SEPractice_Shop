package com.boot.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.boot.shop.util.NotNull;

import java.util.Date;

@TableName("tbl_order")
public class OrderBean {
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    @NotNull(fieldName = "姓名")
    private String name;
    @NotNull(fieldName = "手机")
    private String mobile;
    @NotNull(fieldName = "地址")
    private String address;
    @NotNull(fieldName = "总价")
    private Integer total;
    private Date ctime;
    @TableField(exist = false)  // 数据库里没有
    private String json;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}
