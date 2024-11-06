package com.mini.erp.product.repository;

import com.mini.erp.product.dto.request.InsertProductRequestDto;
import com.mini.erp.product.dto.request.UpdateProductDtoRequest;
import com.mini.erp.product.entity.Product;
import com.mini.erp.product.exception.ProductNotFoundException;
import com.mini.erp.product.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private static final Logger log = LoggerFactory.getLogger(ProductRepository.class);

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public InsertProductRequestDto insertProduct(InsertProductRequestDto productRequestDto){
        log.info("Method insertProduct in Product Repository Class is being invoked");
        String sql = "INSERT INTO products (product_name, price, stocks, description) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql, productRequestDto.getProductName(),
                productRequestDto.getPrice(), productRequestDto.getStocks(), productRequestDto.getDescription());


        return productRequestDto;
    }

    public UpdateProductDtoRequest updateProduct(Long id, UpdateProductDtoRequest updateRequest){

        if(id < 1){
            throw new ProductNotFoundException();
        }

        log.info("Method updateProduct in Product Repository Class is being invoked");

        String sql = "UPDATE products SET product_name = ? WHERE id = ?";

        int i =  jdbcTemplate.update(sql, updateRequest.getProductName(), id);

        if(i > 0){
            log.info("Number of updates:{}", i);
            return updateRequest;
        }else{
            log.error("Product not found");
            throw new RuntimeException("No product found with id: " + id);
        }
    }


    public List<Product> findProductName() {
        log.info("Method findProductName in Product Repository Class is being invoked");

        String sql = "SELECT id, product_name, price FROM products";

        return jdbcTemplate.query(sql, new ProductMapper());
    }


    public List<Product> findAllProducts(){
        log.info("Method getAllProducts in Product Repository Class is being invoked");

        String sql = "SELECT * FROM products";

        List<Product> products = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>());

        return products;
    }

    public Optional<Product> findProductById(Long id){
        log.info("Method findProductById in Product Repository Class is being invoked");

        String sql = "SELECT * FROM products WHERE id = ?";

        try {
            Product product = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), id);
            return Optional.of(product);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public Optional<Product> findProductByName(String name){
        log.info("Method findProductByName in Product Repository Class is being invoked");

        String sql = "SELECT * FROM products WHERE product_name = ?";
        try{
          Product product = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), name);
          return Optional.of(product);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public void deleteProductById(Long id){
        log.info("Method deleteProductById in Product Repository Class is being invoked");

        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }






}

/*
database operations in regard to product= {
    1. save product
    2. find product by id
    3. find product by name
    4. find product by id
    5. update product
    6. delete product by id
    7. delete product by name
    8.
}
 */