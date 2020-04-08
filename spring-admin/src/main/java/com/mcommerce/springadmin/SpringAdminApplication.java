package com.mcommerce.springadmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Classe permettant de surveiller les differents MS de l'appli, leur etat de fonctionnement, où est qu'ils pourraient avoir un
 * probleme
 *
 * @EnableAdminServer
 * @EnableDiscoveryClient permet la decouverte dans l'appli de l'implementation des clients
 */
@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class SpringAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAdminApplication.class, args);
    }
    
    //Apres avoir demarré ce module, aller sur un browser et taper "localhost:9105/#". Se trouvent alors la liste de tous les MS
    // que Sprong Boot est allé recuperer dans Eureka

}
