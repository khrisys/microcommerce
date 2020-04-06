package com.mproduits.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Classe de configuration permettant de gerer l'ensemble des fihciers "application.properties" de tous les microservices.
 * Ici le but de cette classe est de recuperer depuis tous les fihciers "application.properties" toutes les proprietes qui
 * comment par "mes-configs" + leur propriétés, avec leurs valeurs. Ces valeurs seront stockés dans les var definies dans la
 * classe (ayant exactement le meme nom)
 *
 * @Component indique à Spring qu'il doit prendre cette classe en compte parmi  les autres beans
 * @ConfigurationProperties va permettre de recuprer les données qu ise trouvent dans le fichier de configuration. Il prend ne
 * param le prefix des configs indiqué dans chaque fichier application.properties.
 * application.properties  peut être utilisé pour stocker des constantes auxquelles on peut accéder grâce à un bean annoté avec
 * @ConfigurationProperties  .
 * @RefreshScope permet de rafraîchir  les MS à chaque fois qu'un événement Refresh est lancé par la dependance Actuator. Si on
 * fait un changement dans un fichier de conf dans github, il n'y a pas besoin de refraichir quoi que ce soit, de redemarrer un
 * MS , ou de redeployer quoi que ce soit, tout est automatique.
 */
@Component
@ConfigurationProperties("mes-configs")
@RefreshScope
public class AplicationPropertiesConfiguration {
    
    //attr qui doit correspondre à ce qu'on a decrit dans le fioichier application.properties et qu'on veut recuperer
    private int limiteDeProduits;
    
    public int getLimiteDeProduits() {
        return limiteDeProduits;
    }
    
    public void setLimiteDeProduits(int pLimiteDeProduits) {
        limiteDeProduits = pLimiteDeProduits;
    }
}
