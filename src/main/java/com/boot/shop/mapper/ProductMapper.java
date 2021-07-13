package com.boot.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.shop.bean.ProductBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper extends BaseMapper<ProductBean> {
    // 根据类别ID查看对应类别下的商品列表
    @Select("select tbl_product.*, tbl_category.category " +  // 断开的话，末尾记得有空格，否则sql语句会连在一起
            "from tbl_product left join tbl_category " +
            "on tbl_product.cid = tbl_category.id " +
            "where cid = #{cid}")
    List<ProductBean> getProduct(@Param("cid")int cid);

    // 获取热卖产品
    @Select("select * from tbl_product where hot=1")
    List<ProductBean> getHot();

}
