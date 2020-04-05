package com.clientui.beans;

import java.util.Date;

public class CommandeBean {
    
    private int id;
    private Integer productId;
    private Date dateCommande;
    private Integer quantite;
    private Boolean commandePayee;
    
    /**
     * Constructeur vide par defaut, obligatoire n'existe que dans l'intérêt de JPA. L'autre constructeur est celui que vous
     * utilisez pour créer des instances de Product à enregistrer dans la base de données.
     */
    public CommandeBean() {
    }

    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Integer getProductId() {
        return productId;
    }
    
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    
    public Date getDateCommande() {
        return dateCommande;
    }
    
    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }
    
    public Integer getQuantite() {
        return quantite;
    }
    
    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
    
    public Boolean getCommandePayee() {
        return commandePayee;
    }
    
    public void setCommandePayee(Boolean commandePayee) {
        this.commandePayee = commandePayee;
    }
    
    @Override
    public String toString() {
        return "commande{" +
                "id=" + id +
                ", productId=" + productId +
                ", dateCommande=" + dateCommande +
                ", quantite=" + quantite +
                ", commandePayee=" + commandePayee +
                '}';
    }
}
