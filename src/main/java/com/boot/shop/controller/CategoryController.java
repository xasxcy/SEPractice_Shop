package com.boot.shop.controller;

import com.boot.shop.bean.CategoryBean;
import com.boot.shop.mapper.CategoryMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController{
    @Resource
    private CategoryMapper categoryMapper;

    // 查询操作
    @RequestMapping("/list")
    public String list(String category, HttpServletRequest req) {
        req.setAttribute("category", category);
        // 如果没有搜索条件，就查询所有内容；否则，模糊查询
        req.setAttribute("retList", StringUtils.isBlank(category) ?
                categoryMapper.selectList(null) :
                categoryMapper.getLike("%" + category + "%"));
        return "/category/list";
    }

    // <a>是超链接，可以打开一个页面，还可以执行一段js代码（如上所示）。
    // 因为可以打开页面，所以是一个get请求
    // 因为超链接会改变浏览器地址，所以它是重定向。
    // form表单的提交是post请求。
    // RequestMapping = GetMapping + PostMapping

    // 添加和修改放在一起
    @GetMapping("/add") // 打开页面
    public String add(Integer id, HttpServletRequest req) {
        req.setAttribute("bean", id != null ? categoryMapper.selectById(id) : null);
        return "/category/add"; // 将bean转发到add中，bean可能为空
    }

    @PostMapping("/add") // 表单提交
    public String add(CategoryBean bean, HttpServletResponse resp) {
//        if(bean.getCategory() == null ||
//                bean.getCategory().trim().equals(""))
        if(StringUtils.isBlank(bean.getCategory())){ // isBlank()是封装好的方法，代替的是上面的逻辑
            return jsAlert("请输入类别名称！",
                    ("/category/add" + (bean.getId() != null ? "?id="+bean.getId() : "")),  // 解决了判空后重定向，再输入变为新增的问题
                    resp);
        }
        try {  // 解决了
            if (bean.getId() != null) { // id不空，是修改操作
                categoryMapper.updateById(bean);
            } else {  // id为空，是添加操作
                categoryMapper.insert(bean);
            }
        } catch (Exception e){
            return jsAlert(bean.getCategory() + "已经存在！",
                    ("/category/add" + (bean.getId() != null ? "?id="+bean.getId() : "")),
                    resp); // resp是把数据响应给页面
        }
        return "redirect:/category/list"; // 插入后重新查询列表数据
    }

    // 删除操作
    @RequestMapping("/del")
    public String delete(Integer id) {
        categoryMapper.deleteById(id);
        return "redirect:/category/list"; // 删除后重新查询列表数据
    }
}
