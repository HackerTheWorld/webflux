package com.web.flux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import reactor.core.Disposable;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

@SpringBootApplication(exclude={R2dbcAutoConfiguration.class})
public class InfrastructureTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfrastructureTaskApplication.class, args);

    }
}
