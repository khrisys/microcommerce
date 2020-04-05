package com.mpaiement.web.controller;

import com.mpaiement.dao.PaiementDao;
import com.mpaiement.model.Paiement;
import com.mpaiement.web.exceptions.PaiementExistantException;
import com.mpaiement.web.exceptions.PaiementImpossibleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Utilisé pour enregistrer un paiement dans la bdd
 * En utilisant la DAO, on va recuperer la liste des paiements et voir s'il n'y en a pas en double (sinon -> exception) + si le
 * paiement n'existe pas, on ajoute le nouveau paiement
 */
@RestController
public class PaiementController {
    
    @Autowired
    PaiementDao paiementDao;
    
    @PostMapping(value = "/paiement")
    public ResponseEntity<Paiement> payerUneCommande(@RequestBody Paiement paiement) {
        
        
        //Vérifions s'il y a déjà un paiement enregistré pour cette commande
        Paiement paiementExistant = paiementDao.findByidCommande(paiement.getIdCommande());
        if (paiementExistant != null)
            throw new PaiementExistantException("Cette commande est déjà payée"); //1 - Cette exception renvoie un code
        // particulier : 409 CONFLICT qui indique que les données reçues rentrent en conflit avec des données existantes.
        //2 - En cas d'impossibilité d'enregistrer le paiement, le code 500 est renvoyé.
        // 3 - En cas de succès, le code 201 Created est renvoyé, avec le contenu du paiement enregistré.
        
        //Enregistrer le paiement
        Paiement nouveauPaiement = paiementDao.save(paiement);
        
        
        if (nouveauPaiement == null)
            throw new PaiementImpossibleException("Erreur, impossible d'établir le paiement, réessayez plus tard");
        
        
        //TODO Nous allons appeler le Microservice Commandes ici pour lui signifier que le paiement est accepté
        
        return new ResponseEntity<Paiement>(nouveauPaiement, HttpStatus.CREATED);
        
    }
    
    
}
