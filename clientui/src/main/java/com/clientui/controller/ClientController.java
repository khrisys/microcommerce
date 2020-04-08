package com.clientui.controller;

import com.clientui.beans.CommandeBean;
import com.clientui.beans.PaiementBean;
import com.clientui.beans.ProductBean;
import com.clientui.proxies.MicroservceProduitsProxy;
import com.clientui.proxies.MicroserviceCommandesProxy;
import com.clientui.proxies.MicroservicePaiementsProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe permettant de gerer le mapping entre les methodes du controller et les URL.
 *
 * Le retour de toutes les methodes utilisées ici retournent le type String car le moteur de template utilisé ici (Thymleaf)
 * utilise String pour renvoyer le NOM du template  à utiliser.
 *
 * @Controller est une annotation de Spring MVC qui dit au DispatcherServlet, qui reçoit toutes les requêtes pour le dispatcher,
 * de chercher dans cette classe s'il y a une opération qui correspond à l'URI appelé. A la difference de @RestController qui
 * permet d'envoyer des requete via POST, ici, on ne fait que recevoir des requetes
 */
@Controller
public class ClientController {
    
    
    @Autowired // CReation d'uns instance de MicroserviceProxy avec @Autowired (evite de faire un "new")
    MicroservceProduitsProxy microservceProduitsProxy;
    @Autowired
    MicroserviceCommandesProxy microserviceCommandesProxy;
    @Autowired
    MicroservicePaiementsProxy microservicePaiementsProxy;
    
    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    /**
     * Recupere et affiche la liste de tous les produits sur la page d'accueil
     *
     * @param pModel
     * @return liste de tous les produits
     */
    @RequestMapping("/")
    public String accueil(Model pModel) {
        List<ProductBean> produits = microservceProduitsProxy.listeDesProduits(); //Requete pour recuperer la liste des
        // produits contenus dans le module Produits
        
        // Passer le liste de produits recupérés vers le template (Accueil.html) -> C'est le model qui se charge de
        // ca avec la mthode addAttribute()
        pModel.addAttribute("produits", produits);
    
        log.info("LOG - Récupération de la liste des produits dans la page d'accueil : ");
        // Desomrais dans Accueil, on a acces à la liste de tous les produits avec la var "produits". ATTENTION : il faut changer
        // un peu le code du template "Accueil.html" pour boucler sur les produits recupérés
        return "Accueil";
    }
    
    /**
     * Recuperer les detais d'un produit loesqu'on en selectionne un sur la page d'accueil
     * On passe l'objet "produit" récupéré et qui contient les détails en question à  FicheProduit.html
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/details-produit/{id}")
    public String ficheProduit(@PathVariable int id, Model model) {
        
        ProductBean produit = microservceProduitsProxy.recupererUnProduit(id);
        
        model.addAttribute("produit", produit);
        
        return "FicheProduit";
    }
    
    
    /*
     * Étape (3) et (4)
     * Opération qui fait appel au microservice de commande pour placer une commande et récupérer les détails de la commande créée
     * */
    @RequestMapping(value = "/commander-produit/{idProduit}/{montant}")
    public String passerCommande(@PathVariable int idProduit, @PathVariable Double montant,  Model model){
        
        
        CommandeBean commande = new CommandeBean();
        
        //On renseigne les propriétés de l'objet de type CommandeBean que nous avons crée
        commande.setProductId(idProduit);
        commande.setQuantite(1);
        commande.setDateCommande(new Date());
        commande.setCommandePayee(false);
        
        
        //appel du microservice commandes grâce à Feign et on récupère en retour les détails de la commande créée, notamment son ID (étape 4).
        List<CommandeBean> commandeAjoutee = microserviceCommandesProxy.ajouterCommande(commande); //*?
        
        //on passe à la vue l'objet commande et le montant de celle-ci afin d'avoir les informations nécessaire pour le paiement
        model.addAttribute("commande", commandeAjoutee);
        model.addAttribute("montant", montant);
        
        return "Paiement";
    }
    
    /*
     * Étape (5)
     * Opération qui fait appel au microservice de paiement pour traiter un paiement
     * */
    @RequestMapping(value = "/payer-commande/{idCommande}/{montantCommande}")
    public String payerCommande(@PathVariable int idCommande, @PathVariable Integer montantCommande, Model model){
        
        PaiementBean paiementAExcecuter = new PaiementBean();
        
        //on reseigne les détails du produit
        paiementAExcecuter.setIdCommande(idCommande);
        paiementAExcecuter.setMontant(montantCommande);
        paiementAExcecuter.setNumeroCarte(numcarte()); // on génère un numéro au hasard pour simuler une CB
        
        // On appel le microservice et (étape 7) on récupère le résultat qui est sous forme ResponseEntity<PaiementBean> ce qui va nous permettre de vérifier le code retour.
        ResponseEntity<PaiementBean> paiement = microservicePaiementsProxy.payerUneCommande(paiementAExcecuter);
        
        Boolean paiementAccepte = false;
        //si le code est autre que 201 CREATED, c'est que le paiement n'a pas pu aboutir.
        if(paiement.getStatusCode() == HttpStatus.CREATED)
            paiementAccepte = true;
        
        model.addAttribute("paiementOk", paiementAccepte); // on envoi un Boolean paiementOk à la vue
        
        return "confirmation";
    }
    
    //Génére une serie de 16 chiffres au hasard pour simuler vaguement une CB
    private Long numcarte() {
        
        return ThreadLocalRandom
                .current().nextLong(1000000000000000L,9000000000000000L);
    }
    
//    @RequestMapping(value = "/commandes")
//    public String ajouterCommande(@RequestBody CommandeBean commande, Model pModel) {
//
//
//        CommandeBean nouvelleCommande = microserviceCommandesProxy.save(commande);
//        pModel.addAttribute("commande", commande);
//
//        if (nouvelleCommande == null)
//            throw new ImpossibleAjouterCommandeException("Impossible d'ajouter cette commande"); //déclenchée en dernier
//    // recours quand on n'arrive pas à enregistrer la commande pour cause d'erreur interne. Le code renvoyé est alors 500
//
//            return "Commandes";
////        return new ResponseEntity<CommandeBean>(commande, HttpStatus.CREATED);
//}
//
//    @RequestMapping(value = "/commandes/{id}")
//    public String recupererUneCommande(@PathVariable int id, Model pModel) {
//
//        CommandeBean commande = microserviceCommandesProxy.findById(id);
//
////        if (commande == null)
//        ////            throw new CommandeNotFoundException("Cette commande n'existe pas"); //renvoie le code 404 lorsqu'une
//        // commande n'est
//        ////        // pas trouvée.
//        pModel.addAttribute("commande", commande);
//
//        return commande;
//    }
}
