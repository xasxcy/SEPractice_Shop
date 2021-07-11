package com.boot.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.shop.bean.UserBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

// 接口继承接口，继承BaseMapper之后，就可以实现简单的增删改查
public interface UserMapper extends BaseMapper<UserBean> {
    // MyBatis注解：
    // @Select 装select语句
    // @Insert
    //	@Delete
    //	@Update

    // 通过用户名或密码去tbl_user表里找人，如果找到了则登陆成功，否则登录失败。
    // 调用这个抽象函数相当于执行了这条sql语句
    // 绑定参数，方法形参要绑定到占位符?的地方。通过@Param("")为参数命名，然后在SQL注解里用#{}取出参数
    @Select("select * from tbl_user where username=#{username} and password=#{password}") // 查询注解
    UserBean getUser(@Param("username")String username, @Param("password")String password);

}
