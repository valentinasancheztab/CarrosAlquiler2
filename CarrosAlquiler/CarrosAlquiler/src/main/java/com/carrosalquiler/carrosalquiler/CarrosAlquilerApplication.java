package com.carrosalquiler.carrosalquiler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.carrosalquiler.carrosalquiler", "com.carrosalquiler.carrosalquiler.controller", "com.carrosalquiler.carrosalquiler.service"})
public class CarrosAlquilerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarrosAlquilerApplication.class, args);
    }
}
