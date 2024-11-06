package com.mini.erp.product.repository;

import com.mini.erp.product.dto.response.ProductCategoryResponseDto;
import com.mini.erp.product.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class CategoryProductsRepositoryTest {


    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CategoryProductsRepository categoryProduct;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCategoryProducts() {

        ProductCategoryResponseDto product = new ProductCategoryResponseDto();
        List<ProductCategoryResponseDto> categoryResponseDtos = new ArrayList<ProductCategoryResponseDto>();

        categoryResponseDtos.add(product);

        String sql = "SELECT category_id, product_id, categories.category_name, products.product_name\n" +
                "FROM product_categories\n" +
                "JOIN categories ON product_categories.category_id = categories.id\n" +
                "JOIN products ON product_categories.product_id = products.id;";

        Mockito.when(jdbcTemplate.query(Mockito.eq(sql), any(BeanPropertyRowMapper.class))).thenReturn(categoryResponseDtos);

        List<ProductCategoryResponseDto> list = categoryProduct.getCategoryProducts();

        Assertions.assertNotNull(list);
        Assertions.assertEquals(list.size(), 1);
        System.out.println(list.size() == 1);

        Mockito.verify(jdbcTemplate, Mockito.times(1)).query(Mockito.eq(sql), any(BeanPropertyRowMapper.class));
    }

    @Test
    void getOneCategoryProductById() {
        Long id = 1L;


        String sql = "SELECT category_id, product_id, categories.category_name, products.product_name\n" +
                "FROM product_categories\n" +
                "JOIN categories ON product_categories.category_id = categories.id\n" +
                "JOIN products ON product_categories.product_id = products.id\n" +
                "where products.id = ?";

        Mockito.when(jdbcTemplate.queryForObject(Mockito.eq(sql), Mockito.any(BeanPropertyRowMapper.class), Mockito.eq(id)))
                .thenReturn(new ProductCategoryResponseDto());

        Optional<ProductCategoryResponseDto> response = categoryProduct.getOneCategoryProductById(id);

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isPresent());

        Mockito.verify(jdbcTemplate, Mockito.times(1)).queryForObject(Mockito.eq(sql),
                Mockito.any(BeanPropertyRowMapper.class), Mockito.eq(id));
    }
}