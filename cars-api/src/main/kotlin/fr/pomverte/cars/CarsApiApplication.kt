package fr.pomverte.cars

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@SpringBootApplication
class CarsApiApplication

fun main(args: Array<String>) {
    runApplication<CarsApiApplication>(*args)
}

data class Car(val id: String, val vendor: String, val revendeur: String, val modele: String, val couleur: String)

@RestController
@RequestMapping("/v1/cars")
@Api(description="Exposition de la ressource de voitures")
class CarsEndpoint {
    @GetMapping("/{id}")
    @ApiOperation(value = "Recherche d'une voiture par sa clé fonctionnelle")
    fun findById(@PathVariable("id") id: String): ResponseEntity<Car> {
        return ResponseEntity.ok(Car(id, "Renault", "Renault", "Clio", "Bleue"));
    }
}

@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
    }
}
