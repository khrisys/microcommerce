package com.mproduits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Point d'entrée des MS Produits
 *
 * @EnableConfigurationProperies active la prise en charge des beans annotés {@link ConfigurationProperties}.
 *  * Les haricots {@link ConfigurationProperties} peuvent être enregistrés de la manière standard (pour
 *  * exemple utilisant les méthodes {@link org.springframework.context.annotation.Bean @Bean}) ou, par commodité, peut être spécifié
 *  * directement sur cette annotation.
 * @ConfigurationProperties va permettre de recuprer les données qu ise trouvent dans le fichier de configuration. Il prend ne
 * param le prefix des configs indiqué dans chaque fichier application.properties.
 * application.properties  peut être utilisé pour stocker des constantes auxquelles on peut accéder grâce à un bean annoté avec
 * @ConfigurationProperties .
 * @EnableDiscoveryClient permettant l'mplementation de la decouverte de clients, les clients etant eureka. Votre Microservice
 * bénéficie à présent du client Eureka, qui ira enregistrer votre instance à chaque démarrage.
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableDiscoveryClient
public class MproduitsApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MproduitsApplication.class, args);
    }
}
