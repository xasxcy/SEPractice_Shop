package com.boot.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tbl_shopping")
public class ShoppingBean {
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private Integer oid;
    private Integer pid;
    private Integer count;

    public ShoppingBean(Integer oid, Integer pid, Integer count) {
        this.oid = oid;
        this.pid = pid;
        this.count = count;
    }

    public ShoppingBean() {
    }
}
