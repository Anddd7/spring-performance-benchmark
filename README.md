- Java 11
- Kotlin 1.3.70
- Spring Boot 2.3.0.BUILD-SNAPSHOT


# Case

## Description

fetch product and it's stock
- product is stored in db
- stock is from 3rd service (use immediately local api)

## Method

- by id
- list of all

# Tech Stacks

- java-webflux-r2dbc
    - java
    - webflux: Mono + Flux + WebClient
    - r2dbc: + postgres, use jpa reactive repository


- java-webmvc-jpa
    - java
    - webmvc: use parallel stream to handle list of products
    - jpa
    
    
- kotlin-webflux-r2dbc
    - kotlin: an extension testing of kotlin coroutine (`/coroutine/*`), async + flow
    - webflux: Mono + Flux + WebClient
    - r2dbc: + postgres, use jpa reactive repository


- kotlin-webmvc-jpa
    - kotlin: an extension testing of kotlin coroutine (`/coroutine/*`), async
    - webmvc: use kotlin sequence to handle list of products
    - jpa
