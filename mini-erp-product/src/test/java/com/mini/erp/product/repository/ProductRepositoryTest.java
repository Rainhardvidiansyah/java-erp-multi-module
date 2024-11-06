package com.mini.erp.product.repository;

import com.mini.erp.product.dto.request.InsertProductRequestDto;
import com.mini.erp.product.dto.request.UpdateProductDtoRequest;
import com.mini.erp.product.entity.Product;
import com.mini.erp.product.exception.ProductNotFoundException;
import com.mini.erp.product.mapper.ProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;



import static org.mockito.ArgumentMatchers.*;


class ProductRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ProductRepository productRepository;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void insertProduct() {
        var productRequestDto = new InsertProductRequestDto();
        String sql = "INSERT INTO products (product_name, price, stocks, description) VALUES (?, ?, ?, ?)";

        Mockito.when(jdbcTemplate.update(sql, productRequestDto.getProductName(), productRequestDto.getPrice(),
                productRequestDto.getStocks(), productRequestDto.getDescription())).thenReturn(1);


        var repository = productRepository.insertProduct(productRequestDto);

        Assertions.assertNotNull(repository);

        System.out.println(repository);

        Mockito.verify(jdbcTemplate, Mockito.times(1)).update(sql,
                productRequestDto.getProductName(), productRequestDto.getPrice(),
                productRequestDto.getStocks(), productRequestDto.getDescription());
    }

    @Test
    void updateProduct() {

        var updateProduct = new UpdateProductDtoRequest();
        String sql = "UPDATE products SET product_name = ? WHERE id = ?";
        Mockito.when(jdbcTemplate.update(sql, updateProduct.getProductName(), 1L)).thenReturn(1);

        var repository = productRepository.updateProduct(1L, updateProduct);

        Assertions.assertNotNull(repository);

        Mockito.verify(jdbcTemplate, Mockito.times(1)).update(sql, updateProduct.getProductName(), 1L);

    }


    @Test
    void whenIdIsLessThanOneInUpdateMethod_thenThrowProductNotFoundException(){

        var updateProduct = new UpdateProductDtoRequest();
        String sql = "UPDATE products SET product_name = ? WHERE id = ?";

        Mockito.when(jdbcTemplate.update(sql, updateProduct.getProductName(), 0)).thenReturn(0);

        ProductNotFoundException ex = Assertions.assertThrows(ProductNotFoundException.class,
                ()-> productRepository.updateProduct(0L, updateProduct));

        Assertions.assertEquals("Product not found in the database.", ex.getMessage());
    }


    @Test
    void findProductName() {
        var product = new Product();

        List<Product> products = List.of(product);

        String sql = "SELECT id, product_name, price FROM products";

        Mockito.when(jdbcTemplate.query(Mockito.eq(sql), Mockito.any(ProductMapper.class))).thenReturn(products);

        var repository = productRepository.findProductName();

        Assertions.assertNotNull(repository);
        Assertions.assertEquals(1, repository.size());

        Mockito.verify(jdbcTemplate, Mockito.times(1)).query(Mockito.eq(sql), Mockito.any(ProductMapper.class));
    }

    @Test
    void findProductById() {
        Long productId = 1L;
        String sql = "SELECT * FROM products WHERE id = ?";

        Mockito.when(jdbcTemplate.queryForObject(eq(sql), any(BeanPropertyRowMapper.class), eq(productId))).thenReturn(new Product());

        var repository = productRepository.findProductById(1L);

        Assertions.assertNotNull(repository);
        Assertions.assertTrue(repository.isPresent());

        Mockito.verify(jdbcTemplate, Mockito.times(1))
                .queryForObject(eq(sql),  any(BeanPropertyRowMapper.class), eq(productId));
    }

    @Test
    void findProductByName() {
        String product_name = "Product Name";

        String sql = "SELECT * FROM products WHERE product_name = ?";
        Mockito.when(jdbcTemplate.queryForObject(eq(sql), any(BeanPropertyRowMapper.class), eq(product_name))).thenReturn(new Product());

        var repo = productRepository.findProductByName("Product Name");

        Assertions.assertNotNull(repo);
        Assertions.assertTrue(repo.isPresent());

        Mockito.verify(jdbcTemplate, Mockito.times(1)).queryForObject(eq(sql),
                any(BeanPropertyRowMapper.class), eq(product_name));
    }

    @Test
    void deleteProductById() {
        Long productId = 1L;
        String sql = "DELETE FROM product WHERE id = ?";
        productRepository.deleteProductById(productId);
        Mockito.verify(jdbcTemplate, Mockito.times(1)).update(sql, productId);
    }

    @Test
    void findAllProducts() {

        List<Product> products = List.of(new Product(), new Product());
        String sql = "SELECT * FROM products";

        Mockito.when(jdbcTemplate.query(Mockito.eq(sql), any(BeanPropertyRowMapper.class))).thenReturn(products);

        var repo = productRepository.findAllProducts();
        Assertions.assertNotNull(repo);
        Assertions.assertEquals(2, repo.size());

        //        verify(jdbcTemplate, times(1)).query(anyString(), any(BeanPropertyRowMapper.class));

        Mockito.verify(jdbcTemplate,
                Mockito.times(1)).query(Mockito.eq(sql),
                any(BeanPropertyRowMapper.class));
    }
}