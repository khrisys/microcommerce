package com.clientui.auth;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe permettant d'injecter les login et mdp depuis le client vers le point d'entrée zuul de l'appli afin que chaque
 * requete soit securisée.
 *
 * @Configuration indique qu'une classe déclare une ou plusieurs méthodes {@link Bean @Bean} et
 * - peut être traité par le conteneur Spring pour générer des définitions de haricots et
 * - les demandes de service pour ces objets (beans) au moment de l'exécution, par exemple :
 */
@Configuration
public class FeignConfig {
    
    // Ajout des informations d'authentification. Spring va automatiquement reucperer ce bean et à chaque fois qu'il y a une
    // requete Feign, Spring va injecter ce ogin et mdp dans zuul
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("utilisateur", "mdp");
    }
}
