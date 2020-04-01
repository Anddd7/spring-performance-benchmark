plugins {
  val springBootVersion = "2.2.4.RELEASE"
  val springDependencyVersion = "1.0.9.RELEASE"

  java

  id("org.springframework.boot") version springBootVersion
  id("io.spring.dependency-management") version springDependencyVersion
}

dependencies {
  /* spring mvc */
  implementation("org.springframework.boot:spring-boot-starter-web")

  /* db */
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  runtimeOnly("org.postgresql:postgresql")
}

java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11
