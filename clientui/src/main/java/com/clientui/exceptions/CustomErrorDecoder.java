package com.clientui.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;


/**
 * Cette classe sert à renvoyer les bons codes d'errurs lorsqu'il y a des exceptions. En effet, Feign renvoi par defaut
 * toujours un code d'erreur http 500 si la reponse n'est pas comprise entre 200 et 299 (bonne reponse)
 *
 * Ici, on dit qu'on decode l'errur
 *
 * ErrorDecoder est l'interface qui genere le code 500 par defaut dans toute l'appli
 */
public class CustomErrorDecoder implements ErrorDecoder {
    
    //Creation d'un decodeur Feign par defaut
    private final ErrorDecoder defaultErrorDecoder = new Default();
    
    
    /**
     * Le param qui nous interesse est le param "response", car c'est celui qu'on cherche à corriger (le code d'erreur est par
     * defaut: 500)
     *
     * @param methodKey
     * @param response
     * @return classe d'exception personnalisée qui contient l'error decoder permettant d'afficher ses excpetions perso au
     * lieu de celles de Feign par defaut
     */
    @Override
    public Exception decode(String methodKey, Response response) {
    
        System.out.println("LOG : " + methodKey.toString());
        // Si le code d'erreur est 400, on veut renvoyer notre propre exception
//        if(response.status() == 400 ) {
//            return new ProductBadRequestException(
//                    "Requête incorrecte "
//            );
//        }
//
//        // Si le code d'erreur est 404, on veut renvoyer notre propre exception
//        if(response.status() == 404){
//            return new ProductNotFoundException("Produit non trouvé");
//        }
        if(response.status() >= 400 && response.status()<= 499){
            return new Product4XXException("erreur au format 4XX : " + response.status() + " Corps du emessage d'erreur : " + response.body() );
        }
        
        // renvoi l'erreur par defaut (code 500) de Feign avec les 2 param recus dans la methode decode()
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
