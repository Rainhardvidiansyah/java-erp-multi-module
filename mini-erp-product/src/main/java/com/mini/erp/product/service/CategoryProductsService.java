package com.mini.erp.product.service;


import com.mini.erp.product.dto.response.ProductCategoryResponseDto;
import com.mini.erp.product.exception.ProductNotFoundException;
import com.mini.erp.product.repository.CategoryProductsRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryProductsService {

    private final CategoryProductsRepository categoryProductsRepository;

    public CategoryProductsService(CategoryProductsRepository categoryProductsRepository) {
        this.categoryProductsRepository = categoryProductsRepository;
    }

    public List<ProductCategoryResponseDto> getCategoryProduct(){
        return categoryProductsRepository.getCategoryProducts();
    }

    public Optional<ProductCategoryResponseDto> getProductCategoryById(Long id){
        var category_product = this.categoryProductsRepository.getOneCategoryProductById(id);
        if(category_product.isEmpty()){
            throw new ProductNotFoundException();
        }
        return category_product;
    }
}
