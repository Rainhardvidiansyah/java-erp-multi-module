package com.mini.erp.product.service;

import com.mini.erp.product.dto.response.ProductCategoryResponseDto;
import com.mini.erp.product.repository.CategoryProductsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoryProductsServiceTest {

    @Mock
    private CategoryProductsRepository categoryProductsRepository;

    @InjectMocks
    private CategoryProductsService categoryProductsService;


    @BeforeEach
    void beforeEach(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCategoryProduct() {

        Mockito.when(categoryProductsRepository
                .getCategoryProducts()).thenReturn(List.of(new ProductCategoryResponseDto()));

        var service = categoryProductsService.getCategoryProduct();
        Assertions.assertNotNull(service);

        Mockito.verify(categoryProductsRepository, Mockito.times(1)).getCategoryProducts();
    }

    @Test
    void getProductCategoryById() {

        Mockito.when(categoryProductsRepository.getOneCategoryProductById(1L))
                .thenReturn(Optional.of(new ProductCategoryResponseDto()));

        var service = categoryProductsService.getProductCategoryById(1L);

        Assertions.assertNotNull(service);
        Assertions.assertTrue(service.isPresent());

        System.out.println(service);

        Mockito.verify(categoryProductsRepository, Mockito.times(1)).getOneCategoryProductById(1L);
    }



}