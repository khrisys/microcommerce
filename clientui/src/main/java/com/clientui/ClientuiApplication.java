package com.clientui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Entree du microservice
 *
 * @EnableFeignClients L'annotation  @EnableFeignClients  demande à Feign de scanner le package "com.clientui"  pour rechercher
 * des classes qui se déclarent clients Feign. Nous allons justement en créer une plus tard.
 * @EnableDiscoveryClient permettant l'mplementation de la decouverte de clients, les clients etant eureka. Votre Microservice
 * bénéficie à présent du client Eureka, qui ira enregistrer votre instance à chaque démarrage.
 */
@SpringBootApplication
@EnableFeignClients("com.clientui")
@EnableDiscoveryClient
public class ClientuiApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ClientuiApplication.class, args);
    }
    
}
