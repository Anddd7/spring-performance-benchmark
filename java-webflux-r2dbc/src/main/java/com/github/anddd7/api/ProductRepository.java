package com.github.anddd7.api;

import com.github.anddd7.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {

}
