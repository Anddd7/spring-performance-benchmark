package com.github.anddd7.dto

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal

@Table("products")
data class Product(
    @Id
    val id: Int = 0,
    val name: String,
    val price: BigDecimal
)

data class ProductStockDTO(
    val id: Int = 0,
    val name: String,
    val price: BigDecimal,
    val stock: BigDecimal
) {
    constructor(product: Product, stock: BigDecimal) : this(product.id, product.name, product.price, stock)
}
