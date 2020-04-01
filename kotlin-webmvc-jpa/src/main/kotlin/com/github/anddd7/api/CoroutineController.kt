package com.github.anddd7.api

import com.github.anddd7.dto.ProductStockDTO
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import kotlin.math.abs
import kotlin.random.Random

@RestController
@RequestMapping("/coroutine")
class CoroutineController(
    private val productRepository: ProductRepository
) {
  private val webClient = RestTemplateBuilder().rootUri("http://localhost:18084").build()

  @RequestMapping("/product/{id}")
  fun findOne(@PathVariable id: Int) = runBlocking{
    val product = async { productRepository.getOne(id) }
    val stock = async {fetchStock(id)}
     ProductStockDTO(product.await() ,stock.await())
  }

  @RequestMapping("/product")
  fun findAll(): List<ProductStockDTO> = runBlocking {
    productRepository.findAll()
        .map { async { ProductStockDTO(it, fetchStock(it.id)) } }
        .map { it.await() }
  }

  private fun fetchStock(id: Int): BigDecimal {
    Thread.sleep(Random(id).nextLong(1, 1000))
    return webClient.getForEntity("/reactor/$id/stock",BigDecimal::class.java).body!!
  }

  @GetMapping("/{id}/stock")
  fun stock(@PathVariable id: Int) = Random(id).nextFloat().let(::abs).toBigDecimal()
}
