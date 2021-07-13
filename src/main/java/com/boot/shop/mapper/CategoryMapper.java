package com.boot.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.shop.bean.CategoryBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper extends BaseMapper<CategoryBean> {
    // 增删改查不写SQL语句，但Mapper层与继承关系必须要有

    // 模糊查询
    @Select("select * from tbl_category where category like #{category}") // 注意like
    List<CategoryBean> getLike(@Param("category") String category);
}
