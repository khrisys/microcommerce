package com.clientui.proxies;

import com.clientui.beans.ProductBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
//@FeignClient(name = "microservice-produits", url = "localhost:9001") // Declaration du proxy comme etant un client Feign avce
// le nom du microservice à appeller + l'URL du microservice

// C'est desormais Ribbon (load balancer) qui gere les url à des differentes instances des MS produits'
@FeignClient("zuul-server") //Desomrais, le client ne fait plus appel aux MS directement, mais passe par l'intermediaire de
// Zuul. Il faut alors obligatiorement ajouter "/microservice-produits" devant toutes lkes URI des @getMapping
@RibbonClient("microservice-produits")
//@FeignClient(name = "microservice-produits", url = "localhost:9001")
public interface MicroservceProduitsProxy {
    
    // LES MTHODES A APPELLER ICI SONT LES METHODES A APPELLER DANS LE CONTROLLER DU SERVICE DISTANT (SA SIGNATURE)
    
    // Grace  à l'interface vert les produits, on peut lier l'url de l'interface avec le mapping de la methode. Ainsi, on
    // obtient "localhost:9001/Produits"! feign sait exactement où se trouve la ressource demandée.
    // Recupere tous les produits
    @GetMapping(value = "/microservice-produits/Produits")
    List<ProductBean> listeDesProduits(); //L'equivalent de "Bean" dans Produit est ici "ProductBean"
    
    
    //Récuperer un produit par son id
    @GetMapping(value = "/microservice-produits/Produits/{id}")
    ProductBean recupererUnProduit(@PathVariable("id") int id); //SUbtilité! Apres @PathVariable, il faut rajouter à
    // @PathVariable entre paretheses le nom du param "id". Celui ci correspond à l'id à passer dans l'url "/Produits/{id}"
}
