package com.github.anddd7.api

import com.github.anddd7.dto.Product
import com.github.anddd7.dto.ProductStockDTO
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.http.MediaType
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.time.Duration
import kotlin.math.abs
import kotlin.random.Random

@RestController
@RequestMapping("/reactor")
class ReactorController(
    private val productRepository: ProductRepository
) {
  private val webClient = WebClient.builder().baseUrl("http://localhost:18084").build()

  @RequestMapping("/product/{id}")
  fun findOne(@PathVariable id: Int) =
      productRepository.findById(id).zipWith(fetchStock(id))
          .map { ProductStockDTO(it.t1, it.t2) }

  @RequestMapping("/product")
  fun findAll() =
      productRepository.findAll().flatMap { product ->
        fetchStock(product.id).map { stock -> ProductStockDTO(product, stock) }
      }

  private fun fetchStock(id: Int) =
      webClient
          .get()
          .uri("/reactor/$id/stock")
          .accept(MediaType.APPLICATION_JSON)
          .retrieve()
          .bodyToMono<BigDecimal>()

  @GetMapping("/{id}/stock")
  fun stock(@PathVariable id: Int) =
      Mono.fromCallable { Random(id).nextFloat().let(::abs).toBigDecimal() }
          .delayElement(Duration.ofMillis(Random(id).nextLong(1, 1000)))
}

@Repository
interface ProductRepository : ReactiveCrudRepository<Product, Int>
