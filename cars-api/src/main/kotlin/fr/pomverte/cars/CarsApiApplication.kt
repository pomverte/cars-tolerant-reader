package fr.pomverte.cars

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class CarsApiApplication

fun main(args: Array<String>) {
    runApplication<CarsApiApplication>(*args)
}

data class Car(val id: String, val vendor: String, val revendeur: String, val modele: String, val couleur: String)

@RestController
@RequestMapping("/v1/cars")
class CarsEndpoint {
    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: String): ResponseEntity<Car> {
        return ResponseEntity.ok(Car(id, "Renault", "Renault", "Clio", "Bleue"));
    }
}
