package fr.pomverte.cars.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@EnableFeignClients
@SpringBootApplication
public class CarsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarsClientApplication.class, args);
    }
}

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class Car {
    private String id;
    private String vendor;
    private String modele;
}

@FeignClient(name = "cars", url = "http://localhost:8080")
interface CarsApiClient {
    @GetMapping("/v1/cars/{id}")
    ResponseEntity<Car> getById(@PathVariable("id") String id);
}

@RestController
@AllArgsConstructor
class CallMeController {

    CarsApiClient carsApiClient;

    @GetMapping("/")
    ResponseEntity<Car> getCar() {
        return carsApiClient.getById(UUID.randomUUID().toString());
    }
}