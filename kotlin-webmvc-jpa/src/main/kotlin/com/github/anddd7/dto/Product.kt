package com.github.anddd7.dto

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Table

@Entity
@Table(name = "products")
data class Product(
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
