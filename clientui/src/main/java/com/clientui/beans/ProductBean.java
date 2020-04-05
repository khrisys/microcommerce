package com.clientui.beans;

/**
 * Lorsque Feign va vouloir recuprer les produits du microservice "Produits", on va devoir stocker ls produits recupérés dans
 * un objet.  Donc, il faut reproduire exactement les memes beans qu'il y a dans le microservice "Produits"
 *
 * Dans ce microservice, l'id ne doit plus etre unique, ni autoincrémenté car c'est un id recupéré du microservice voulu
 */

public class ProductBean {
    
    // Ici, pas de clef unique ni autoincrementée
    private int id;
    private String titre;
    private String description;
    private String image;
    private Double prix;
    
    /**
     * Constructeur vide par defaut, obligatoire n'existe que dans l'intérêt de JPA. L'autre constructeur est celui que vous
     * utilisez pour créer des instances de Product à enregistrer dans la base de données.
     */
    public ProductBean() {
    }
    
    
    //    GETTER ET SETTER
    public int getId() {
        return id;
    }
    
    public void setId(int pId) {
        id = pId;
    }
    
    public String getTitre() {
        return titre;
    }
    
    public void setTitre(String pTitre) {
        titre = pTitre;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String pDescription) {
        description = pDescription;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String pImage) {
        image = pImage;
    }
    
    public Double getPrix() {
        return prix;
    }
    
    public void setPrix(Double pPrix) {
        prix = pPrix;
    }
    
    @Override
    public String toString() {
        return "ProductBean{" + "id=" + id + ", titre='" + titre + '\'' + ", description='" + description + '\'' + ", image='" + image + '\'' + ", prix=" + prix + '}';
    }
}
