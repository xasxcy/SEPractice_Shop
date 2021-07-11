package com.boot.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName; // 让数据库和java来传参

// 一个bean对应一张表，表里面有什么Bean里就有什么
// mybatisplus要求我们这么做，会得到，简单地增删改查不需要写SQL语句
// UserBean对应tbl_user表，
@TableName("tbl_user")
public class UserBean {
    @TableId(value="id",type=IdType.AUTO) // value="id" 让表里的id和bean里的id对应 // IdType.AUTO 表示auto_increment自增
    private Integer id; // bean里面的int尽量使用Integer来替代。区别：int不能接受null类型
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
