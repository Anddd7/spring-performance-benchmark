package com.github.anddd7.api

import com.github.anddd7.dto.Product
import com.github.anddd7.dto.ProductStockDTO
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import kotlin.math.abs
import kotlin.random.Random

@RestController
@RequestMapping("/webmvc")
class WebMvcController(
    private val productRepository: ProductRepository
) {
  private val webClient = RestTemplateBuilder().rootUri("http://localhost:18084").build()

  @RequestMapping("/product/{id}")
  fun findOne(@PathVariable id: Int): ProductStockDTO {
    val product = productRepository.getOne(id)
    val stock = fetchStock(id)
    return ProductStockDTO(product ,stock)
  }

  @RequestMapping("/product")
  fun findAll(): List<ProductStockDTO> =
      productRepository.findAll()
          .asSequence()
          .map { ProductStockDTO(it, fetchStock(it.id)) }
          .toList()

  private fun fetchStock(id: Int): BigDecimal {
    // simulate unstable network
    // one thread one request, just block current thread/request
    Thread.sleep(Random(id).nextLong(1, 1000))
    return webClient.getForEntity("/reactor/$id/stock",BigDecimal::class.java).body!!
  }

  @GetMapping("/{id}/stock")
  fun stock(@PathVariable id: Int) = Random(id).nextFloat().let(::abs).toBigDecimal()
}

@Repository
interface ProductRepository : JpaRepository<Product, Int>
