package com.a2r.immobilierdz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
public class ImmobilierDzApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImmobilierDzApplication.class, args);
    }

}
