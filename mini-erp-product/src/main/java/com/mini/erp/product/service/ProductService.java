package com.mini.erp.product.service;

import com.mini.erp.product.dto.request.InsertProductRequestDto;
import com.mini.erp.product.dto.request.UpdateProductDtoRequest;
import com.mini.erp.product.entity.Product;
import com.mini.erp.product.exception.ProductNotFoundException;
import com.mini.erp.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public InsertProductRequestDto insertProductRequest(InsertProductRequestDto insertProductRequestDto){
        return this.productRepository.insertProduct(insertProductRequestDto);
    }

    public Optional<Product> retrieveProductById(Long id){
        var product = this.productRepository.findProductById(id);
        if(product.isEmpty()){
            log.info("Product is empty is returning");
            product.orElseThrow(() -> new ProductNotFoundException());
        }
        return product;
    }


    public UpdateProductDtoRequest updateProduct(Long id, UpdateProductDtoRequest request){
        var product = this.retrieveProductById(id);
        return this.productRepository.updateProduct(id, request);
    }


    public List<Product> getAllProductsByName(){
        return this.productRepository.findProductName();
    }

    public Product getProductById(Long id){
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        Optional<Product> product = this.productRepository.findProductById(id);
        if(product.isPresent()){
            return product.get();
        } else{
            throw new ProductNotFoundException();
        }
    }


    public Product getProductOptional(String name){
        Optional<Product> product = this.productRepository.findProductByName(name);
        if(product.isPresent()){
            return product.get();
        }else{
            throw new ProductNotFoundException();
        }
    }

    public void deleteProduct(Long id){
        this.productRepository.deleteProductById(id);
    }
}
