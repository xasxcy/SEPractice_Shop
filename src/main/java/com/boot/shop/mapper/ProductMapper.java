package com.boot.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.shop.bean.ProductBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper extends BaseMapper<ProductBean> {
    // 根据类别ID查看对应类别下的商品列表
    @Select("select * from tbl_product where cid = #{cid}")
    List<ProductBean> getProduct(@Param("cid")int cid);
}
