package com.mini.erp.controllers.product;


import com.mini.erp.product.dto.request.InsertProductRequestDto;
import com.mini.erp.product.dto.request.UpdateProductDtoRequest;
import com.mini.erp.product.dto.response.ProductPriceResponseDto;
import com.mini.erp.product.entity.Product;
import com.mini.erp.product.exception.ErrorMessage;
import com.mini.erp.product.exception.ProductNotFoundException;
import com.mini.erp.product.service.CategoryProductsService;
import com.mini.erp.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    
    private final ProductService productService;

    private final CategoryProductsService categoryProductsService;

    @GetMapping("/hello")
    public String hello(){
        log.info("This method get hit");
        return "hello";
    }


    @GetMapping("/")
    public ResponseEntity<?> getAllProducts() {
        log.info("Request received to get all products");

        var products = this.productService.getAllProductsByName();

        List<ProductPriceResponseDto> productPriceResponseDtos = new ArrayList<>();

        for (Product product: products){
            productPriceResponseDtos.add(new ProductPriceResponseDto(product.getId(),
                    product.getProductName(), product.format()));
        }
        return ResponseEntity.ok(productPriceResponseDtos);
    }

    @PostMapping("/add")
    public ResponseEntity<?> insertDataProduct(@Valid @RequestBody InsertProductRequestDto productRequestDto,
                                               BindingResult result, HttpServletRequest request){

        if(result.hasErrors()){
            for(ObjectError objectError : result.getAllErrors()){
                log.error("Validation error: {}", objectError.getDefaultMessage());
                log.error("Object which has an error: {}", objectError.getObjectName());

                var errorMessage = new ErrorMessage(objectError.getObjectName(), objectError.getDefaultMessage(),
                        HttpStatus.BAD_REQUEST, request.getRequestURI());

                return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
            }
        }
        var product = productService.insertProductRequest(productRequestDto);

        return new ResponseEntity<>(product.getProductName() + " added", HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestParam(name = "id") Long id,
                                           @Valid @RequestBody UpdateProductDtoRequest updateRequestDto,
                                           BindingResult result, HttpServletRequest request){
        if(result.hasErrors()){
            for(ObjectError objectError : result.getAllErrors()){
                log.error("Validation error: {}", objectError.getDefaultMessage());
                log.error("Object which has an error: {}", objectError.getObjectName());

                var errorMessage = new ErrorMessage(objectError.getObjectName(), objectError.getDefaultMessage(),
                        HttpStatus.BAD_REQUEST, request.getRequestURI());

                return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
            }
        }
        var product = this.productService.updateProduct(id, updateRequestDto);
        return ResponseEntity.ok(product);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        Product product = this.productService.getProductById(id);
        if(product == null){
            throw new ProductNotFoundException();
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getProductByName(@RequestParam String productName) throws ProductNotFoundException {
        var product = this.productService.getProductOptional(productName);
        if(product == null){
            throw new ProductNotFoundException();
        }
        return ResponseEntity.ok(product);
    }


    @GetMapping("/category-product")
    public ResponseEntity<?> getCategoryProducts(){
        var categoryProductsList = categoryProductsService.getCategoryProduct();
        return new ResponseEntity<>(categoryProductsList, HttpStatus.OK);
    }

    @GetMapping("/category-product/{id}")
    public ResponseEntity<?> getOneCategoryProducts(@PathVariable("id") Long id){
        if(id == null){
            throw new RuntimeException("Invalid category");
        }
        var categoryProduct = categoryProductsService.getProductCategoryById(id);
        return new ResponseEntity<>(categoryProduct, HttpStatus.OK);
    }

}
