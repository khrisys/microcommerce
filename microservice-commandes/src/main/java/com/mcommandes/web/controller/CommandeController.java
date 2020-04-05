package com.mcommandes.web.controller;


import com.mcommandes.dao.CommandesDao;
import com.mcommandes.model.Commande;
import com.mcommandes.web.exceptions.CommandeNotFoundException;
import com.mcommandes.web.exceptions.ImpossibleAjouterCommandeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Microservice s'occupant de la gestion des commandes
 * Il expose 2 endpoints : recuperer la liste des commandes, recuperer une commande par son id
 */
@RestController
public class CommandeController {
    
    @Autowired
    CommandesDao commandesDao;
    
    /**
     * ajout d'une commande via un POST
     *
     * @param commande
     * @return
     */
    @PostMapping(value = "/commandes")
    public ResponseEntity<Commande> ajouterCommande(@RequestBody Commande commande) {
        
        Commande nouvelleCommande = commandesDao.save(commande);
        
        if (nouvelleCommande == null)
            throw new ImpossibleAjouterCommandeException("Impossible d'ajouter cette commande"); //déclenchée en dernier
        // recours quand on n'arrive pas à enregistrer la commande pour cause d'erreur interne. Le code renvoyé est alors 500
        
        return new ResponseEntity<Commande>(commande, HttpStatus.CREATED);
    }
    
    /**
     * récupère une commande via son id.  Optional  permet de ne plus vérifier si l'objet est null à chaque fois et évite les
     * NullPointerExceptions
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/commandes/{id}")
    public Optional<Commande> recupererUneCommande(@PathVariable int id) {
        
        Optional<Commande> commande = commandesDao.findById(id);
        
        if (!commande.isPresent())
            throw new CommandeNotFoundException("Cette commande n'existe pas"); //renvoie le code 404 lorsqu'une commande n'est
        // pas trouvée.
        
        return commande;
    }
}
