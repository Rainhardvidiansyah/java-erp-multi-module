package com.mini.erp.product.service;

import com.mini.erp.product.dto.request.InsertProductRequestDto;
import com.mini.erp.product.dto.request.UpdateProductDtoRequest;
import com.mini.erp.product.entity.Product;
import com.mini.erp.product.exception.ProductNotFoundException;
import com.mini.erp.product.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {


    @Mock
    private ProductRepository productRepository;


    @InjectMocks
    private ProductService productService;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void insertProductRequest() {

        InsertProductRequestDto insertProductRequestDto = new InsertProductRequestDto();

        Mockito.when(productRepository.insertProduct(insertProductRequestDto))
                .thenReturn(insertProductRequestDto);

        var service = productService.insertProductRequest(insertProductRequestDto);
        Assertions.assertNotNull(service);

        Mockito.verify(productRepository, Mockito.times(1)).insertProduct(insertProductRequestDto);
    }

    @Test
    void updateProduct() {

        var updateProductRequestDto = new UpdateProductDtoRequest();
        updateProductRequestDto.setProductName("Updated Product");

        Mockito.when(productRepository.findProductById(1L)).thenReturn(Optional.of(new Product()));
        Mockito.when(productRepository.updateProduct(1L, updateProductRequestDto))
                .thenReturn(updateProductRequestDto);

        var service = productService.updateProduct(1L, updateProductRequestDto);

        Assertions.assertNotNull(service);

        Mockito.verify(productRepository, Mockito.times(1))
                .updateProduct(1L, updateProductRequestDto);
    }


    @Test
    void retrieveProductByIdTest(){
        Long productId = 1L;

        Mockito.when(productRepository.findProductById(productId)).thenReturn(Optional.of(new Product()));

        var service = productService.retrieveProductById(productId);
        Assertions.assertNotNull(service);

        Mockito.verify(productRepository).findProductById(productId);
    }


    @Test
    void whenProductNotFound_thenThrowException(){
        var updateProductRequestDto = new UpdateProductDtoRequest();
        updateProductRequestDto.setProductName("Updated Product");


        Mockito.when(productRepository.updateProduct(null, updateProductRequestDto)).thenReturn(new UpdateProductDtoRequest());

        ProductNotFoundException p = Assertions.assertThrows(ProductNotFoundException.class, ()-> {
            productService.updateProduct(null, updateProductRequestDto);
        });

        Assertions.assertEquals("Product not found in the database.", p.getMessage());
    }

    @Test
    void whenProductIsEmpty_thenThrowException(){
        Mockito.when(productRepository.findProductById(null)).thenReturn(Optional.empty());
        ProductNotFoundException p = Assertions.assertThrows(ProductNotFoundException.class, ()-> {
            productService.retrieveProductById(null);
        });
    }


//    @Test
//    void whenProductIsEmpty_thenThrowProductNotFoundException(){
//
//        Mockito.when(productRepository.findProductById(0L)).thenReturn(Optional.empty());
//
//        ProductNotFoundException ex = Assertions.assertThrows(ProductNotFoundException.class, () -> productService.retrieveProductById(0L));
//
//        Mockito.verify(productRepository, Mockito.times(1)).findProductById(0L);
//    }
}