package com.boot.shop.controller;

import com.boot.shop.bean.OrderBean;
import com.boot.shop.bean.ProductBean;
import com.boot.shop.bean.ShoppingBean;
import com.boot.shop.bean.WxResp;
import com.boot.shop.mapper.CategoryMapper;
import com.boot.shop.mapper.OrderMapper;
import com.boot.shop.mapper.ProductMapper;
import com.boot.shop.mapper.ShoppingMapper;
import com.boot.shop.util.NotNullUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/wx") // 这个地址浏览器和小程序都可以访问
public class WxController extends BaseController {
    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ShoppingMapper shoppingMapper;

    @GetMapping("/index") // 微信的索引页，展示所有商品
    public void index(Integer cid, HttpServletResponse resp) {
        WxResp r = new WxResp(); // r只是一个普通对象
        r.category = categoryMapper.selectList(null);
        if (!r.category.isEmpty()) { // 获取第一个类别的所有商品
            r.product = cid != null ? productMapper.getProduct(cid) :
                    productMapper.getProduct(r.category.get(0).getId());
        }
        // 获取热卖商品列表
        r.hot = productMapper.getHot();
        outRespJson(r, resp); // 把一个对象转换成JSON字符串输出到浏览器或小程序中
    }

    @PostMapping("/order") // 小程序提交订单```
    public void order(OrderBean bean, HttpServletResponse resp) {
        // json字符串转成对象或数组：使用谷歌的gson jar包。固定写法。
        List<ProductBean> productBeanList = new Gson().fromJson(bean.getJson(),
                new TypeToken<List<ProductBean>>() {
                }.getType()); // TypeToken是抽象类，直接实例化然后调用方法
        WxResp r = new WxResp();
        String alert = NotNullUtil.isBlankAlert(bean);
        if (alert != null) {  // 说明有错误
            r.fail(alert); // 失败了
        } else if (productBeanList.isEmpty()) { // 是空数组
            r.fail("购物车是空的");
        } else {
            bean.setCtime(new Date());  // ctime字段是在java终生陈德高
            orderMapper.insert(bean);  // 新订单添加到订单表中
            for (ProductBean p : productBeanList) {  // 把此订单中的产品加入购物（关系）表中
                ShoppingBean shoppingBean = new ShoppingBean(bean.getId(), p.getId(), p.getCount());
                shoppingMapper.insert(shoppingBean);
            }
        }
        outRespJson(r, resp);
    }
}
