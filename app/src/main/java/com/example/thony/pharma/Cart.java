package com.example.thony.pharma;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Cart implements Parcelable
{
    private  int id_panier;
    private  int id_medicamen;
    private String nom_medicament;
    private  String image_medicament;
    private  double prix_medicament;
    private  int quantite_medicament;
    private  int id_client;
    private int id_pharmacie;


    public Cart()
    {

    }

    public Cart(int id_panier, int id_medicamen, String nom_medicament, String image_medicament, double prix_medicament, int quantite_medicament, int id_client, int id_pharmacie) {
        this.id_panier = id_panier;
        this.id_medicamen = id_medicamen;
        this.nom_medicament = nom_medicament;
        this.image_medicament = image_medicament;
        this.prix_medicament = prix_medicament;
        this.quantite_medicament = quantite_medicament;
        this.id_client = id_client;
        this.id_pharmacie = id_pharmacie;
    }

    public Cart(int id_panier, int id_medicamen, String nom_medicament, String image_medicament, double prix_medicament, int quantite_medicament, int id_client)
    {
        this.id_panier = id_panier;
        this.id_medicamen = id_medicamen;
        this.nom_medicament = nom_medicament;
        this.image_medicament = image_medicament;
        this.prix_medicament = prix_medicament;
        this.quantite_medicament = quantite_medicament;
        this.id_client = id_client;
    }

    protected Cart(Parcel in) {
        id_panier = in.readInt();
        id_medicamen = in.readInt();
        nom_medicament = in.readString();
        image_medicament = in.readString();
        prix_medicament = in.readDouble();
        quantite_medicament = in.readInt();
        id_client = in.readInt();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public int getId_medicamen() {
        return id_medicamen;
    }

    public void setId_medicamen(int id_medicamen) {
        this.id_medicamen = id_medicamen;
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

    public int getQuantite_medicament() {
        return quantite_medicament;
    }

    public void setQuantite_medicament(int quantite_medicament) {
        this.quantite_medicament = quantite_medicament;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
dest.writeInt(id_panier);
dest.writeInt(id_medicamen);
dest.writeString(nom_medicament);
dest.writeString(image_medicament);
dest.writeInt(quantite_medicament);
dest.writeDouble(prix_medicament);
dest.writeInt(id_client);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id_panier=" + id_panier +
                ", id_medicamen=" + id_medicamen +
                ", nom_medicament='" + nom_medicament + '\'' +
                ", image_medicament='" + image_medicament + '\'' +
                ", prix_medicament=" + prix_medicament +
                ", quantite_medicament=" + quantite_medicament +
                ", id_client=" + id_client +
                '}';
    }


    public int getId_pharmacie() {
        return id_pharmacie;
    }

    public void setId_pharmacie(int id_pharmacie) {
        this.id_pharmacie = id_pharmacie;
    }


}
