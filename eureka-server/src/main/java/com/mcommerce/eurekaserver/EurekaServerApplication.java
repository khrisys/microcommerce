package com.mcommerce.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Serveur permettant de recuperer toutes les instances de tous les MS de l'appli et de les faire fonctionner au mieux selon
 * les requetes, meme si bcp d'instances de MS sont lancées.
 *
 * Fonctionne parfaitement avec Ribbon comme load-balancer
 *
 * @EnableServerEureka lance le server Eureka
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
