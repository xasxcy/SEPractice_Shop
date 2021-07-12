package com.boot.shop.controller;

import com.boot.shop.bean.CategoryBean;
import com.boot.shop.mapper.CategoryMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    // <a>是超链接，可以打开一个页面，还可以执行一段js代码（如上所示）。
    // 因为可以打开页面，所以是一个get请求
    // 因为超链接会改变浏览器地址，所以它是重定向。
    // form表单的提交是post请求。
    // RequestMapping = GetMapping + PostMapping

    // 添加操作
    @GetMapping("/add") // 打开页面
    public String add(){
        return "/category/add";
    }

    @PostMapping("/add") // 表单提交
    public String add(CategoryBean bean){
        categoryMapper.insert(bean);
        return "redirect:/category/list"; // 插入后重新查询列表数据
    }
    // 删除操作
    @RequestMapping("/del")
    public String delete(Integer id){
        categoryMapper.deleteById(id);
        return "redirect:/category/list"; // 删除后重新查询列表数据
    }
}
