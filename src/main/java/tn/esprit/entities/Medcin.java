package tn.esprit.entities;

import javafx.scene.control.Button;

public class Medcin {
    private int id;
    private String nom;
    private String prenom;
    private String specialite;
    private int etabId;
    private Button btnModifier;
    private Button btnSupprimer;

    public Medcin(int id, String nom, String prenom, String specialite, Button btnModifier, Button btnSupprimer) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.btnModifier = btnModifier;
        this.btnSupprimer = btnSupprimer;
    }

    public Button getBtnModifier() {
        return btnModifier;
    }

    public void setBtnModifier(Button btnModifier) {
        this.btnModifier = btnModifier;
    }

    public Button getBtnSupprimer() {
        return btnSupprimer;
    }

    public void setBtnSupprimer(Button btnSupprimer) {
        this.btnSupprimer = btnSupprimer;
    }

    public Medcin(int id, String nom, String prenom, String specialite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
    }

    public Medcin(String nom, String prenom, String specialite) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
    }

    public Medcin(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
