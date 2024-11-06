package com.mini.erp.product.repository;


import com.mini.erp.product.dto.response.ProductCategoryResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository

public class CategoryProductsRepository {

    private final Logger log = LoggerFactory.getLogger(CategoryProductsRepository.class);

    private final JdbcTemplate jdbcTemplate;

    public CategoryProductsRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ProductCategoryResponseDto> getCategoryProducts(){

        log.info("Method getCategoryProducts in CategoryProductsRepository Class is being invoked");

        String sql = "SELECT category_id, product_id, categories.category_name, products.product_name\n" +
                "FROM product_categories\n" +
                "JOIN categories ON product_categories.category_id = categories.id\n" +
                "JOIN products ON product_categories.product_id = products.id;";

        var mapper = BeanPropertyRowMapper.newInstance(ProductCategoryResponseDto.class);

        List<ProductCategoryResponseDto> result =
                jdbcTemplate.query(sql, mapper);

        return result;
    }

    public Optional<ProductCategoryResponseDto> getOneCategoryProductById(Long id){
        log.info("Method getOneCategoryProductById in CategoryProductsRepository Class is being invoked");

        String sql = "SELECT category_id, product_id, categories.category_name, products.product_name\n" +
                "FROM product_categories\n" +
                "JOIN categories ON product_categories.category_id = categories.id\n" +
                "JOIN products ON product_categories.product_id = products.id\n" +
                "where products.id = ?";

        ProductCategoryResponseDto response = jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(ProductCategoryResponseDto.class), id);

        if(response != null){
            return Optional.of(response);
        }else{
            return Optional.empty();
        }
    }
}
