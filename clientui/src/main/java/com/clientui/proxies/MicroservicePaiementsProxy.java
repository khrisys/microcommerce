package com.clientui.proxies;

import com.clientui.beans.PaiementBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Ce proxy Feign s'occupe de générer les clients adequats et fait communiuqer les mdules entre eux.
 *
 * C'est une interface qui va fournir à Feign toutes les informations (methodes GET, POst, etc, eventuellement param s'il y en
 * a, etc) dont il a besoin pour générer les requetes http.
 *
 * @FeignClient sert à declarer cette classe comme etant un client Feign, avec en param le nom du microservice  à appeller. Feign
 * utilisera les informations fournies ici pour construire les requêtes HTTP appropriées afin d'appeler le Microservice-Produits.
 *
 * !!!ATTENTION !!!
 * 1 - il faut renseigner "microservice-produits" dans application.properties du microservice produit!!!!
 * 2 - Il faut aussi renseigner l'URL d'entrée des produits (chaque module possede un port different)
 */
@FeignClient(name = "microservice-paiements", url = "localhost:9003") // Declaration du proxy comme etant un client Feign avce
// le nom du microservice à appeller + l'URL
public interface MicroservicePaiementsProxy {
    
    // LES MTHODES A APPELLER ICI SONT LES METHODES A APPELLER DANS LE CONTROLLER DU SERVICE DISTANT (SA SIGNATURE)
    
    @PostMapping(value = "/paiement")
    public ResponseEntity<PaiementBean> payerUneCommande(@RequestBody PaiementBean paiement);
    
}
