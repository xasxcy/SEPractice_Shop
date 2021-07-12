package com.boot.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.shop.bean.CategoryBean;

public interface CategoryMapper extends BaseMapper<CategoryBean> {
    // 增删改查不写SQL语句，但Mapper层与继承关系必须要有

}
