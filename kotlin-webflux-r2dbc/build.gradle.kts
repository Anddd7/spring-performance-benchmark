import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  val kotlinVersion = "1.3.70"
  val springBootVersion = "2.2.4.RELEASE"
  val springDependencyVersion = "1.0.9.RELEASE"

  id("org.jetbrains.kotlin.jvm") version kotlinVersion
  id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
  id("org.springframework.boot") version springBootVersion
  id("io.spring.dependency-management") version springDependencyVersion
}

dependencies {
  /* kotlin */
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  /* kotlin coroutines*/
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")

  /* spring webflux*/
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  /* kotlin reactor*/
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
  implementation("io.projectreactor:reactor-kotlin-extensions:1.0.0.M2")

  /* r2bdc */
  // TODO remove this after upgrade spring boot to 2.3.x https://github.com/spring-projects/spring-boot/issues/19988
  implementation("org.springframework.boot.experimental:spring-boot-starter-data-r2dbc:0.1.0.M3")
  runtimeOnly("io.r2dbc:r2dbc-postgresql:0.8.2.RELEASE")
}

tasks.withType<KotlinCompile>().all {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "11"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
