package com.mproduits.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe d'exception personnalisée renvoyant le code d'erreur 404 not found
 *
 * @ResponseStatus(HttpStatus.NOT_FOUND) permet de renvoyer le bon code d'erreur
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {


    public ProductNotFoundException(String message) {
        super(message);
    }
}
