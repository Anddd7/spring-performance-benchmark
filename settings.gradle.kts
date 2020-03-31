pluginManagement {
  repositories {
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
    gradlePluginPortal()
  }
  resolutionStrategy {
    eachPlugin {
      if (requested.id.id == "org.springframework.boot") {
        useModule("org.springframework.boot:spring-boot-gradle-plugin:${requested.version}")
      }
    }
  }
}

rootProject.name = "spring-performance-benchmark"

include("kotlin-webmvc-jpa")
include("kotlin-webmvc-netty-jpa")
include("kotlin-webflux-r2dbc")
include("java-webmvc-jpa")
include("java-webmvc-netty-jpa")
include("java-webflux-r2dbc")
