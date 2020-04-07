package com.mcommerce.zuulserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Classe permettant de creer un seul poitn d'entree dans l'appli vers tous les servers. Cela va permettre de dispatcher, de
 * creer de sfiltres, des regles en fonction des requetes passées.
 *
 * @EnableZuulProxy Configure un point d'entrée de serveur Zuul et y installe des filtres de proxy inverse, afin qu'il puisse
 * transmettre les demandes aux serveurs backend. Les backends peuvent être enregistrés manuellement par le biais de
 * configuration, via DiscoveryClient.
 * @EnableDiscoryClient permet à Eureka de detecter tous les MS qui tournent dans l'appli et de les faire communiquer entre eux
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulServerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }
    
}
