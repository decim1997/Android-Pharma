package com.example.thony.pharma;

public class Perssone
{
    int id_session;
    int id_personne;
    String email;
    String pseudo;
    String password;
    String photo;
    String number;
    int role;
    int activate;

    public Perssone(int id_session, int id_personne, String email, String pseudo, String password, String photo, String number, int role, int activate) {
        this.id_session = id_session;
        this.id_personne = id_personne;
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
        this.photo = photo;
        this.number = number;
        this.role = role;
        this.activate = activate;
    }

    public Perssone()
    {

    }

    public int getId_personne() {
        return id_personne;
    }

    public void setId_personne(int id_personne) {
        this.id_personne = id_personne;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getActivate() {
        return activate;
    }

    public void setActivate(int activate) {
        this.activate = activate;
    }


    public int getId_session() {
        return id_session;
    }

    public void setId_session(int id_session) {
        this.id_session = id_session;
    }

    @Override
    public String toString() {
        return "Perssone{" +
                "id_session=" + id_session +
                ", id_personne=" + id_personne +
                ", email='" + email + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", number='" + number + '\'' +
                ", role=" + role +
                ", activate=" + activate +
                '}';
    }
}
