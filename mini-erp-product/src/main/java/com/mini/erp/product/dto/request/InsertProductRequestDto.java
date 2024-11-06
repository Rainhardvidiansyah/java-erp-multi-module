package com.mini.erp.product.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.ToString;


@ToString
public class InsertProductRequestDto {

    @NotEmpty(message = "Nama product harus diisi")
    private String productName;

    private double price;

    @Min(1)
    private int stocks;

    @NotEmpty(message = "Deskripsi product harus diisi")
    private String description;


    public InsertProductRequestDto() {
    }

    public InsertProductRequestDto(String productName, double price, int stocks, String description) {
        this.productName = productName;
        this.price = price;
        this.stocks = stocks;
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStocks() {
        return stocks;
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

//    INSERT INTO products (product_name, price, stocks, description, created_date, updated_date)