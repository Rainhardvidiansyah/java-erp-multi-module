package com.mini.erp.product.dto.response;


import lombok.ToString;

@ToString
public class ProductCategoryResponseDto {

  private String  category_id;
  private String product_id;

  private  String category_name;
  private String product_name;

    public ProductCategoryResponseDto() {
    }

  public ProductCategoryResponseDto(String category_id, String product_id, String category_name, String product_name) {
    this.category_id = category_id;
    this.product_id = product_id;
    this.category_name = category_name;
    this.product_name = product_name;
  }


  public String getCategory_id() {
    return category_id;
  }

  public void setCategory_id(String category_id) {
    this.category_id = category_id;
  }

  public String getProduct_id() {
    return product_id;
  }

  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }

  public String getCategory_name() {
    return category_name;
  }

  public void setCategory_name(String category_name) {
    this.category_name = category_name;
  }

  public String getProduct_name() {
    return product_name;
  }

  public void setProduct_name(String product_name) {
    this.product_name = product_name;
  }
}
