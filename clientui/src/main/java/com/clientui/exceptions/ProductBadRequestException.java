package com.clientui.exceptions;

public class ProductBadRequestException extends RuntimeException {
    public ProductBadRequestException(String pRequête_incorrecte_) {
        
        super(pRequête_incorrecte_);
    }
}
