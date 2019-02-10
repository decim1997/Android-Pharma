package com.example.thony.pharma;

public class Order
{

    private int idcommande;
    private int idpharmacie;
    private Double prixtotal;
    private String datecommande;
    private String code;
    private int idclient;
    private  String nompharmacie;
    private String addresse;
    private double logitude;
    private double latitude;

    public Order()
    {

    }

    public Order(int idcommande, int idpharmacie, Double prixtotal, String datecommande, String code, int idclient, String nompharmacie, String addresse, double logitude, double latitude) {
        this.idcommande = idcommande;
        this.idpharmacie = idpharmacie;
        this.prixtotal = prixtotal;
        this.datecommande = datecommande;
        this.code = code;
        this.idclient = idclient;
        this.nompharmacie = nompharmacie;
        this.addresse = addresse;
        this.logitude = logitude;
        this.latitude = latitude;
    }

    public int getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    public int getIdpharmacie() {
        return idpharmacie;
    }

    public void setIdpharmacie(int idpharmacie) {
        this.idpharmacie = idpharmacie;
    }

    public Double getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(Double prixtotal) {
        this.prixtotal = prixtotal;
    }

    public String getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(String datecommande) {
        this.datecommande = datecommande;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public String getNompharmacie() {
        return nompharmacie;
    }

    public void setNompharmacie(String nompharmacie) {
        this.nompharmacie = nompharmacie;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public double getLogitude() {
        return logitude;
    }

    public void setLogitude(double logitude) {
        this.logitude = logitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
