package com.aquamonitor.platform.u202515871;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Pc2u202515871Application {
    public static void main(String[] args) {
        SpringApplication.run(Pc2u202515871Application.class, args);
    }
}
