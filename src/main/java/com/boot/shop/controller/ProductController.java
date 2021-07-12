package com.boot.shop.controller;

import com.boot.shop.bean.ProductBean;
import com.boot.shop.mapper.ProductMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController{
    @Resource
    private ProductMapper productMapper;

    @GetMapping("/list") // 打开页面
    public String list(Integer cid, HttpServletRequest req){
//        if(cid == null){  下面传过去的查询使得我们无法直接访问/product/list，而必须带上相应的cid参数
//            System.out.println("cid为空");
//        }
//        req.setAttribute("retList", productMapper.selectList(null)); // 展示所有商品
        req.setAttribute("retList", productMapper.getProduct(cid));
        req.setAttribute("cid", cid);
        return "/product/list";
    }

    // 添加商品
    @GetMapping("/add")
    public String add(Integer id, int cid, HttpServletRequest req){
        req.setAttribute("cid", cid);
        req.setAttribute("bean", id != null ? (productMapper.selectById(id)) : null);
        return "/product/add";
    }

    @PostMapping("/add")
    public String add(ProductBean bean){
        if(bean.getId() != null){
            productMapper.updateById(bean);
        } else {
            productMapper.insert(bean);
        }
        return "redirect:/product/list?cid=" + bean.getCid();
    }

    // 如果有外键的话，删除要同时给出id和cid
    @GetMapping("/del")
    public String del(int id, int cid){
        productMapper.deleteById(id);
        return "redirect:/product/list?cid=" + cid;
    }


    // @PostMapping("/list")

}
