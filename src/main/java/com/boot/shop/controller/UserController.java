package com.boot.shop.controller;

import com.boot.shop.bean.UserBean;
import com.boot.shop.mapper.UserMapper;
import org.apache.commons.collections.set.SynchronizedSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


// Controller就是SpringMVC
@Controller
public class UserController extends BaseController{
    // Controller层调用mapper层
    //- 重定向： redirect:/地址.html
    //	1. 重定向会改变浏览器地址
    //	2. 重定向可以访问到static文件夹里的页面
    //	3. 重定向不可以访问到templates文件夹里的页面
    //  4. 重定向可以找到static里面的文件，也可以找到controller里面的地址
    //  5. 重定向传参：xxx?a=avalue&b=bvalue
    //- 转发： /地址
    //	1. 浏览器地址栏不会改变
    //	2. 转发可以访问到templates文件夹里的页面
    //	3. 转发不可以访问到static文件夹里的页面
    // 	4. 转发不可以找到Controller里面的地址
    //  5. 转发传参：req.setAttribute("a", avalue);

    // 下面这句不要用new创建对象，而是使用注解@Autowired创建对象。
    @Resource
    private UserMapper userMapper;

    // 转发
    @RequestMapping("/login")
    public String login(String username, String password) {
        UserBean user = userMapper.getUser(username, password);
        return user != null ? ("redirect:/main?uid=" + user.getId()) : ("redirect:/index.html?msg=" + getUTF8Param("用户名或密码错误"));
    }
        //        if(user != null){
//            // 跳转到主页
//            // ps：所有的地址，拼前不拼后，前面有一个/，后面就没有
////            return "/main";
//             return "redirect:/main"; // 重定向到@RequestMapping("main");
//        } else {
//            System.out.println("用户名或密码错误，登录失败");
//            return "redirect:/index.html?msg=" + getUTF8Param("用户名或密码错误"); // 重定向到index.html页面
//        }
//    }

    @RequestMapping("/main")
    public String main(int uid, HttpServletRequest req){
        // selectById是BaseMapper封装好的方法，根据主键查询
        req.setAttribute("bean", userMapper.selectById(uid));
        return "/main";
    }
}
