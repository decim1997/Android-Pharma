package com.example.thony.pharma;


public class LigneCommande
{

    private int id_lignecommande;
    private int id_commande;
    private  int id_medicament;
    private  double prix_unitaire;
    private  int quantite;
    private  double prix_totalligne;
    private  String nom_medicament;
    private String image_medicament;
    private int id_pharmacie;
    private double prix_total;
    private  String date_commande;
    private  int id_client;
    private String code;


    public LigneCommande()
    {

    }

    public LigneCommande(int id_lignecommande, int id_commande, int id_medicament, double prix_unitaire, int quantite, double prix_totalligne, String nom_medicament, String image_medicament, int id_pharmacie, double prix_total, String date_commande, int id_client, String code) {
        this.id_lignecommande = id_lignecommande;
        this.id_commande = id_commande;
        this.id_medicament = id_medicament;
        this.prix_unitaire = prix_unitaire;
        this.quantite = quantite;
        this.prix_totalligne = prix_totalligne;
        this.nom_medicament = nom_medicament;
        this.image_medicament = image_medicament;
        this.id_pharmacie = id_pharmacie;
        this.prix_total = prix_total;
        this.date_commande = date_commande;
        this.id_client = id_client;
        this.code = code;
    }

    public int getId_lignecommande() {
        return id_lignecommande;
    }

    public void setId_lignecommande(int id_lignecommande) {
        this.id_lignecommande = id_lignecommande;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_medicament() {
        return id_medicament;
    }

    public void setId_medicament(int id_medicament) {
        this.id_medicament = id_medicament;
    }

    public double getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(double prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix_totalligne() {
        return prix_totalligne;
    }

    public void setPrix_totalligne(double prix_totalligne) {
        this.prix_totalligne = prix_totalligne;
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

    public int getId_pharmacie() {
        return id_pharmacie;
    }

    public void setId_pharmacie(int id_pharmacie) {
        this.id_pharmacie = id_pharmacie;
    }

    public double getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(double prix_total) {
        this.prix_total = prix_total;
    }

    public String getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(String date_commande) {
        this.date_commande = date_commande;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
