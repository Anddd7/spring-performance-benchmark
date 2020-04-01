package com.github.anddd7.api;

import com.github.anddd7.entity.Product;
import com.github.anddd7.entity.ProductStockDTO;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/webmvc")
public class WebMvcController {

  private final ProductRepository productRepository;

  private RestTemplate webClient = new RestTemplateBuilder().rootUri("http://localhost:18082")
      .build();

  @Autowired
  public WebMvcController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @GetMapping("/product/{id}")
  public ProductStockDTO findOne(@PathVariable Integer id) {
    Optional<Product> product = productRepository.findById(id);
    return new ProductStockDTO(product.get(), fetchStock(id));
  }

  private BigDecimal fetchStock(Integer id) {
    try {
      Thread.sleep(new Random(System.currentTimeMillis()).nextInt(1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return webClient.getForEntity("/webmvc/" + id + "/stock", BigDecimal.class).getBody();
  }

  @GetMapping("/product")
  public List<ProductStockDTO> findAll() {
    List<Product> products = productRepository.findAll();
    return products.stream()
        .parallel()
        .map(product ->
            new ProductStockDTO(product, fetchStock(product.getId()))
        )
        .collect(Collectors.toList());
  }

  @GetMapping("/{id}/stock")
  public BigDecimal stock(@PathVariable Integer id) {
    Random r = new Random(System.currentTimeMillis());
    double positiveDouble = Math.abs(r.nextDouble());
    return BigDecimal.valueOf(positiveDouble);
  }
}
