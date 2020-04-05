package com.clientui.configurations;

import com.clientui.exceptions.CustomErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuration permetant d'indiquer à Feign qu'il doit d'abord prendre ne compte les classes d'exception
 * personnalisée plutot que les exceptions par defaut
 */
@Configuration
public class FeignExceptionConfig {
    
    /**
     * Créé un decoder d'errur
     * @return une instance de ce decoder personnalisé
     *
     * @Bean permet de creer l'objet CustomErrorDecoder qui sera donc utilisé par @Configuration en haut de cette classe
     */
    @Bean
    public CustomErrorDecoder customErrorDecoder(){
        return new CustomErrorDecoder();
    }
}
