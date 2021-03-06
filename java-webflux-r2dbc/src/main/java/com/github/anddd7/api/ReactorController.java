package com.github.anddd7.api;

import com.github.anddd7.entity.Product;
import com.github.anddd7.entity.ProductStockDTO;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactor")
public class ReactorController {

  private final ProductRepository productRepository;

  private WebClient webClient = WebClient.builder().baseUrl("http://localhost:18081").build();

  @Autowired
  public ReactorController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @GetMapping("/product/{id}")
  public Mono<ProductStockDTO> findOne(@PathVariable Integer id) {
    Mono<Product> productMono = productRepository.findById(id);
    Mono<BigDecimal> stockMono = fetchStock(id);
    return productMono.zipWith(stockMono, ProductStockDTO::new);
  }

  private Mono<BigDecimal> fetchStock(Integer id) {
    // simulate unstable network
    int sleepTime = new Random(System.currentTimeMillis()).nextInt(1000);

    return webClient
        .get()
        .uri("/reactor/" + id + "/stock")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(BigDecimal.class)
        .delayElement(Duration.ofMillis(Integer.valueOf(sleepTime).longValue()));
  }

  @GetMapping("/product")
  public Flux<ProductStockDTO> findAll() {
    return productRepository.findAll().flatMap(this::fetchProductStock);
  }

  private Mono<ProductStockDTO> fetchProductStock(Product product) {
    Mono<BigDecimal> stockMono = fetchStock(product.getId());
    return stockMono.map(stock -> new ProductStockDTO(product, stock));
  }


  @GetMapping("/{id}/stock")
  public BigDecimal stock(@PathVariable Integer id) {
    // mock 3rd api, return immediately
    Random r = new Random(System.currentTimeMillis());
    double positiveDouble = Math.abs(r.nextDouble());
    return BigDecimal.valueOf(positiveDouble);
  }
}
