package com.boot.shop.controller;

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
    public String list(HttpServletRequest req){
        req.setAttribute("retList", productMapper.selectList(null));
        return "/product/list";
    }

    // @PostMapping("/list")

}
