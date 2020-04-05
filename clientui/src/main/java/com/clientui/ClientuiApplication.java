package com.clientui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Entree du microservice
 *
 * @EnableFeignClients L'annotation  @EnableFeignClients  demande à Feign de scanner le package "com.clientui"  pour rechercher
 * des classes qui se déclarent clients Feign. Nous allons justement en créer une plus tard.
 */
@SpringBootApplication
@EnableFeignClients("com.clientui")
public class ClientuiApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ClientuiApplication.class, args);
    }
    
}
