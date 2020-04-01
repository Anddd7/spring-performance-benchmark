plugins {
  val springBootVersion = "2.2.4.RELEASE"
  val springDependencyVersion = "1.0.9.RELEASE"

  java

  id("org.springframework.boot") version springBootVersion
  id("io.spring.dependency-management") version springDependencyVersion
}

dependencies {
  /* spring webflux*/
  implementation("org.springframework.boot:spring-boot-starter-webflux")

  /* r2bdc */
  // TODO remove this after upgrade spring boot to 2.3.x https://github.com/spring-projects/spring-boot/issues/19988
  implementation("org.springframework.boot.experimental:spring-boot-starter-data-r2dbc:0.1.0.M3")
  runtimeOnly("io.r2dbc:r2dbc-postgresql:0.8.2.RELEASE")
}

java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11
