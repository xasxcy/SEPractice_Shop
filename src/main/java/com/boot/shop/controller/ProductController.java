package com.boot.shop.controller;

import com.boot.shop.bean.ProductBean;
import com.boot.shop.mapper.ProductMapper;
import com.boot.shop.util.NotNullUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Resource
    private ProductMapper productMapper;

    @GetMapping("/list") // 打开页面
    public String list(Integer cid, HttpServletRequest req) {
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
    public String add(Integer id, Integer cid, HttpServletRequest req) {
        req.setAttribute("cid", cid);
        req.setAttribute("bean", id != null ? (productMapper.selectById(id)) : null);
        return "/product/add";
    }

    @PostMapping("/add")
    public String add(ProductBean bean, HttpServletResponse resp) {
        // 逻辑：关键属性不能为空
        if (NotNullUtil.isBlank(bean)) {
            // 如果有@NotNull注解的属性，只要发现值是空的，就返回true
            return jsAlert("请完善商品信息！",
                    ("/product/add?cid=" + bean.getCid() +
                            (bean.getId() != null ? "&id=" + bean.getId() : "")),
                    resp);
        }
        // 逻辑：处理整型数据：不允许为负值
        bean.setPrice(Math.abs(bean.getPrice()));
        bean.setNum(Math.abs(bean.getNum()));
        // 逻辑：根据有无id判断是插入新数据还是修改
        if (bean.getId() != null) {
            productMapper.updateById(bean);
        } else {
            productMapper.insert(bean);
        }
        return "redirect:/product/list?cid=" + bean.getCid();
    }

    // 如果有外键的话，删除要同时给出id和cid
    @GetMapping("/del")
    public String del(int id, int cid) {
        productMapper.deleteById(id);
        return "redirect:/product/list?cid=" + cid;
    }

    // 上传logo
    @RequestMapping("/logo")
    public void logo(MultipartFile file, HttpServletResponse resp) {
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        try {
            file.transferTo(new File("/Users/xasxcy/OneDrive/create/shop/file/" + fileName)); // 注意最后一个斜杠要写，不写的话会放到上一级目录里
        } catch (Exception e) {
            System.out.println("检查有没有创建好这个路径");
        }
        // 输出图片路径给页面，没有前缀，为什么？
        outRespJson("/shop/file/" + fileName, resp);
    }

}
