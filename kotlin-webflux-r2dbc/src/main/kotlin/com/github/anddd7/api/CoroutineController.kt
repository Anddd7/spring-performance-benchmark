package com.github.anddd7.api

import com.github.anddd7.dto.Product
import com.github.anddd7.dto.ProductStockDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.withContext
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitOne
import org.springframework.data.r2dbc.core.from
import org.springframework.data.r2dbc.query.Criteria
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import java.math.BigDecimal
import kotlin.math.abs
import kotlin.random.Random

@RestController
@RequestMapping("/coroutine")
class CoroutineController(
    private val databaseClient: DatabaseClient
) {
  private val webClient = WebClient.builder().baseUrl("http://localhost:18084").build()

  @RequestMapping("/product/{id}")
  suspend fun findOne(@PathVariable id: Int) = withContext(Dispatchers.Default) {
    val product = async {
      databaseClient.select()
          .from<Product>()
          .matching(Criteria.where("id").`is`(id))
          .fetch()
          .awaitOne()
    }
    val stock = async { fetchStock(id) }

    ProductStockDTO(product.await(), stock.await())
  }

  @ExperimentalCoroutinesApi
  @RequestMapping("/product")
  @FlowPreview
  suspend fun findAll() =
      databaseClient.select().from<Product>().fetch().all().asFlow()
          .flatMapMerge {
            flow {
              emit(ProductStockDTO(it, fetchStock(it.id)))
            }
          }

  private suspend fun fetchStock(id: Int) =
      webClient
          .get()
          .uri("/coroutine/$id/stock")
          .accept(MediaType.APPLICATION_JSON)
          .retrieve()
          .awaitBody<BigDecimal>()

  @GetMapping("/{id}/stock")
  suspend fun stock(@PathVariable id: Int) = withContext(Dispatchers.Default) {
    // mock 3rd api, return immediately
    delay(Random(id).nextLong(1, 1000))
    Random(id).nextFloat().let(::abs).toBigDecimal()
  }
}
