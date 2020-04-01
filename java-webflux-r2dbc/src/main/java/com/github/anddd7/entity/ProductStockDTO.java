package com.github.anddd7.entity;

import java.math.BigDecimal;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

public class ProductStockDTO {
  private Integer id;
  private String name;
  private BigDecimal price;
  private BigDecimal stock;

  public ProductStockDTO(Product product, BigDecimal stock) {
    this.id = product.getId();
    this.name = product.getName();
    this.price = product.getPrice();
    this.stock = stock;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getStock() {
    return stock;
  }

  public void setStock(BigDecimal stock) {
    this.stock = stock;
  }
}
