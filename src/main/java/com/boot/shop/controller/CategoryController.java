package com.boot.shop.controller;

import com.boot.shop.bean.CategoryBean;
import com.boot.shop.mapper.CategoryMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryMapper categoryMapper;

    // 查询操作
    @RequestMapping("/list")
    public String list(HttpServletRequest req){
        req.setAttribute("retList", categoryMapper.selectList(null));
        return "/category/list";
    }

    // 添加操作
    @RequestMapping("/add")
    public String add(){
        return "";
    }

    // 删除操作
    @RequestMapping("/del")
    public String delete(){
        return "";
    }
}
