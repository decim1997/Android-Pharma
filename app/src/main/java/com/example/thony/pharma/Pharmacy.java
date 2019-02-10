package com.example.thony.pharma;

public class Pharmacy
{

    private  int id_pharmacie;
    private  int id_pharmacien;
    private String nom_pharmacie;
    private String description;
    private String numeros_pharmacie;
    private String ville;
    private String pay;
    private String addresse;
    private String pharmapicture;
    private  int garde;
    private double longitude;
    private  double latitude;

    public Pharmacy(int id_pharmacie, int id_pharmacien, String nom_pharmacie, String description, String numeros_pharmacie, String ville, String pay, String addresse, String pharmapicture, int garde, double longitude, double latitude) {
        this.id_pharmacie = id_pharmacie;
        this.id_pharmacien = id_pharmacien;
        this.nom_pharmacie = nom_pharmacie;
        this.description = description;
        this.numeros_pharmacie = numeros_pharmacie;
        this.ville = ville;
        this.pay = pay;
        this.addresse = addresse;
        this.pharmapicture = pharmapicture;
        this.garde = garde;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Pharmacy()
    {

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

    public String getNom_pharmacie() {
        return nom_pharmacie;
    }

    public void setNom_pharmacie(String nom_pharmacie) {
        this.nom_pharmacie = nom_pharmacie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumeros_pharmacie() {
        return numeros_pharmacie;
    }

    public void setNumeros_pharmacie(String numeros_pharmacie) {
        this.numeros_pharmacie = numeros_pharmacie;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getPharmapicture() {
        return pharmapicture;
    }

    public void setPharmapicture(String pharmapicture) {
        this.pharmapicture = pharmapicture;
    }

    public int getGarde() {
        return garde;
    }

    public void setGarde(int garde) {
        this.garde = garde;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
