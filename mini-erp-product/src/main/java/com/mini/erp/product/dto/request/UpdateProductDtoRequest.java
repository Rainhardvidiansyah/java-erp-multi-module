package com.mini.erp.product.dto.request;

import jakarta.validation.constraints.NotEmpty;

public class UpdateProductDtoRequest {

    @NotEmpty(message = "Nama produk harus diisi")
    private String productName;


    public UpdateProductDtoRequest() {
    }

    public UpdateProductDtoRequest(String productName) {
        this.productName = productName;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
