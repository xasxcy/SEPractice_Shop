package com.boot.shop.controller;

import com.boot.shop.bean.WxResp;
import com.boot.shop.mapper.CategoryMapper;
import com.boot.shop.mapper.ProductMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/wx") // 这个地址浏览器和小程序都可以访问
public class WxController extends BaseController {
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private ProductMapper productMapper;
    @GetMapping("/index") // 微信的索引页，展示所有商品
    public void index(HttpServletResponse resp) {
        WxResp r = new WxResp(); // r只是一个普通对象
        r.category = categoryMapper.selectList(null);
        if (!r.category.isEmpty()) { // 获取第一个类别的所有商品
            int cid = r.category.get(0).getId();
            r.product = productMapper.getProduct(cid);
        }
        // 获取热卖商品列表
        r.hot = productMapper.getHot();
        outRespJson(r, resp); // 把一个对象转换成JSON字符串输出到浏览器或小程序中
    }


}
