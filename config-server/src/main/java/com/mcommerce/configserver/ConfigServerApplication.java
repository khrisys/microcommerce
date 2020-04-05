package com.mcommerce.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * LIAISON ENTRE GITHUB ET CONFIG-SERVER DES MICROSERVICES
 *
 * Classe de configuration pour aller chercher les fichiers de config de sping cloud config se trouvant sur le repo distant de
 * github.
 *
 * En demarrant le serveur, il va donc aller chercher le fichier de configuration dans le GIT et exposer une API qui répond à
 * l'URL "/nom-du-microservice:port_du_microservice/default". Il fournit ensuite sous format JSON toutes les configurations
 * présentes dans le fichier.
 *
 * @EnableConfigServer permet de déclarer ce Microservice comme étant un serveur de configuration. Il acctive Spring Cloud Config
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
    
}
