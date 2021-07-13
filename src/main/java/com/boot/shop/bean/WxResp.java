package com.boot.shop.bean;

import java.util.List;

// 给小程序的数据格式
public class WxResp {
    // 响应码，code=1代表成功，code=0代表失败
    public int code = 1;
    // 错误提示语，默认没有错误
    public String msg= "";
    // 失败函数
    public void fail(String msg){
        this.code = 0;
        this.msg = msg;
    }

    public List<CategoryBean> category; // 类别数组
    public List<ProductBean> product; // 商品数组
    public List<ProductBean> hot; // 热卖数组
}
