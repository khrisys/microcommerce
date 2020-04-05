package com.clientui.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Erreur personnalisée pour un produit non trouvé depuis un client utilisant Feign pour appeler ce produit dans le module Produit
 *
 * @ResponseStatus(HttpStatus.NOT_FOUND) permet de reconnaitre que le produit n'est pas reconnu
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String pProduit_non_trouvé) {
        super(pProduit_non_trouvé);

    }
}
