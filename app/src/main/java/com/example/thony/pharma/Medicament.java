package com.example.thony.pharma;

public class Medicament
{
    private int id_medicament;
    private String nom_medicament;
    private String image_medicament;
    private double prix_medicament;
    private  int quantite;
    private  int ordonance;
    private int id_type;
    private int id_categorie;
    private int id_forme;
    private int  id_pharmacie;
    private int id_pharmacien;
    private String nom_pharmacy;
    private String categorie_medicament;
    private  String type_medicament;
    private String description_medicament;
    private String forme_medicament;


    public Medicament(int id_medicament, String nom_medicament, String image_medicament, double prix_medicament, int quantite, int ordonance, int id_type, int id_categorie, int id_forme, int id_pharmacie, int id_pharmacien, String nom_pharmacy, String categorie_medicament, String type_medicament, String description_medicament, String forme_medicament) {
        this.id_medicament = id_medicament;
        this.nom_medicament = nom_medicament;
        this.image_medicament = image_medicament;
        this.prix_medicament = prix_medicament;
        this.quantite = quantite;
        this.ordonance = ordonance;
        this.id_type = id_type;
        this.id_categorie = id_categorie;
        this.id_forme = id_forme;
        this.id_pharmacie = id_pharmacie;
        this.id_pharmacien = id_pharmacien;
        this.nom_pharmacy = nom_pharmacy;
        this.categorie_medicament = categorie_medicament;
        this.type_medicament = type_medicament;
        this.description_medicament = description_medicament;
        this.forme_medicament = forme_medicament;
    }

    public Medicament(int id_medicament, String nom_medicament, String image_medicament, double prix_medicament, int quantite, int ordonance, int id_type, int id_categorie, int id_forme, String categorie_medicament, String type_medicament, String description_medicament, String forme_medicament) {
        this.id_medicament = id_medicament;
        this.nom_medicament = nom_medicament;
        this.image_medicament = image_medicament;
        this.prix_medicament = prix_medicament;
        this.quantite = quantite;
        this.ordonance = ordonance;
        this.id_type = id_type;
        this.id_categorie = id_categorie;
        this.id_forme = id_forme;
        this.categorie_medicament = categorie_medicament;
        this.type_medicament = type_medicament;
        this.description_medicament = description_medicament;
        this.forme_medicament = forme_medicament;
    }

    public  Medicament()
    {

    }

    public int getId_medicament() {
        return id_medicament;
    }

    public void setId_medicament(int id_medicament) {
        this.id_medicament = id_medicament;
    }

    public String getNom_medicament() {
        return nom_medicament;
    }

    public void setNom_medicament(String nom_medicament) {
        this.nom_medicament = nom_medicament;
    }

    public String getImage_medicament() {
        return image_medicament;
    }

    public void setImage_medicament(String image_medicament) {
        this.image_medicament = image_medicament;
    }

    public double getPrix_medicament() {
        return prix_medicament;
    }

    public void setPrix_medicament(double prix_medicament) {
        this.prix_medicament = prix_medicament;
    }

    public int getOrdonance() {
        return ordonance;
    }

    public void setOrdonance(int ordonance) {
        this.ordonance = ordonance;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public int getId_forme() {
        return id_forme;
    }

    public void setId_forme(int id_forme) {
        this.id_forme = id_forme;
    }

    public String getCategorie_medicament() {
        return categorie_medicament;
    }

    public void setCategorie_medicament(String categorie_medicament) {
        this.categorie_medicament = categorie_medicament;
    }

    public String getType_medicament() {
        return type_medicament;
    }

    public void setType_medicament(String type_medicament) {
        this.type_medicament = type_medicament;
    }

    public String getDescription_medicament() {
        return description_medicament;
    }

    public void setDescription_medicament(String description_medicament) {
        this.description_medicament = description_medicament;
    }

    public String getForme_medicament() {
        return forme_medicament;
    }

    public void setForme_medicament(String forme_medicament) {
        this.forme_medicament = forme_medicament;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }


    @Override
    public String toString() {
        return "Medicament{" +
                "id_medicament=" + id_medicament +
                ", nom_medicament='" + nom_medicament + '\'' +
                ", image_medicament='" + image_medicament + '\'' +
                ", prix_medicament=" + prix_medicament +
                ", quantite=" + quantite +
                ", ordonance=" + ordonance +
                ", id_type=" + id_type +
                ", id_categorie=" + id_categorie +
                ", id_forme=" + id_forme +
                ", categorie_medicament='" + categorie_medicament + '\'' +
                ", type_medicament='" + type_medicament + '\'' +
                ", description_medicament='" + description_medicament + '\'' +
                ", forme_medicament='" + forme_medicament + '\'' +
                '}';
    }

    public int getId_pharmacie() {
        return id_pharmacie;
    }

    public void setId_pharmacie(int id_pharmacie) {
        this.id_pharmacie = id_pharmacie;
    }

    public int getId_pharmacien() {
        return id_pharmacien;
    }

    public void setId_pharmacien(int id_pharmacien) {
        this.id_pharmacien = id_pharmacien;
    }

    public String getNom_pharmacy() {
        return nom_pharmacy;
    }

    public void setNom_pharmacy(String nom_pharmacy) {
        this.nom_pharmacy = nom_pharmacy;
    }
}
