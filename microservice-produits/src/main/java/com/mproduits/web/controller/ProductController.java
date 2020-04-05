package com.mproduits.web.controller;

import com.mproduits.configurations.AplicationPropertiesConfiguration;
import com.mproduits.dao.ProductDao;
import com.mproduits.model.Product;
import com.mproduits.web.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductDao productDao;
    
    //Fichier de configuration general pour tous les MS
    @Autowired
    AplicationPropertiesConfiguration aplicationPropertiesConfiguration;
    
    /**
     * Affiche la liste de tous les produits disponibles
     *
     * "ProductNotFoundException" prend en charge les exceptions si Feign depuis l'ihm client ne parvient à recuperer tous les
     * produits
     */
    @GetMapping(value = "/Produits")
    public List<Product> listeDesProduits(){

        List<Product> products = productDao.findAll();

        if(products.isEmpty()) throw new ProductNotFoundException("Aucun produit n'est disponible à la vente");
//        Ici, en utilisant le fichier de propriété, on apeut limiter le nb d'items affichés  à l'ecran de l'user'
        List<Product> listeLimites = products.subList(0, aplicationPropertiesConfiguration.getLimiteDeProduits());

        return listeLimites;

    }
    
    /**
     * Récuperer un produit par son id
     *
     * "ProductNotFoundException" prend en charge les exceptions si Feign depuis l'ihm client ne parvient à recuperer le produit
     * via son id
     */
    @GetMapping( value = "/Produits/{id}")
    public Optional<Product> recupererUnProduit(@PathVariable int id) {

        Optional<Product> product = productDao.findById(id);
        // Si le produit n'exite pas, renvoi le code http 404
        if(!product.isPresent())  throw new ProductNotFoundException("Le produit correspondant à l'id " + id + " n'existe pas");

        return product;
    }
}

