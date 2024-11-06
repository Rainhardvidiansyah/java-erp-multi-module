package com.mini.erp.product.mapper;

import com.mini.erp.product.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setProductName(rs.getString("product_name"));
        product.setPrice(BigDecimal.valueOf(rs.getDouble("price")));
        return product;
    }




//USE LAMBDA
//    RowMapper<Product> mapper = (a, b) -> {
//        Product product = new Product();
//        System.out.println(a.getString("product_name"));
//
//        return product;
//    };
}
