package com.mini.erp.product.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException() {
        super("Product not found in the database.");
    }
}
