package com.clientui.configurations;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe permettant d'identifier chaquer requete ecffectuée dans l'appli, de lui donner un ID unique et de suivre cette
 * requete à chaque etape des MS passés
 *
 * @Configuration ; on rajoute cette annotations parce que cette classe java est une classe de configuration
 */
@Configuration
public class SleuthConfig {
    
    // La constante ALWAYS_SAMPLE indique à Sleuth que toutes les requetes doivent etre exportées. Ainsi, Zipkin pourra les
    // analyser
    @Bean
    public Sampler defaultSampler(){
        return Sampler.ALWAYS_SAMPLE;
    }
}
