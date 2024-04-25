package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.entities.Medcin;
import tn.esprit.services.MedcinServices;

public class AjouterMedcin {

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField specialite;

    @FXML
    void ajouterMedcin(ActionEvent event) {
        try {
            String nomMedcin = nom.getText();
            String prenomMedcin = prenom.getText();
            String specialiteMedcin = specialite.getText();

            // Vérifiez si tous les champs sont remplis
            if (nomMedcin.isEmpty() || prenomMedcin.isEmpty() || specialiteMedcin.isEmpty()) {
                // Affichez un message d'erreur si un champ est vide
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Veuillez remplir tous les champs !");
                alert.showAndWait();
                return; // Sortez de la méthode
            }

            // Créez une instance de votre service MedcinServices et ajoutez le médecin
            MedcinServices medcinServices = new MedcinServices();
            medcinServices.add(new Medcin(nomMedcin, prenomMedcin, specialiteMedcin));

            // Affichez un message de succès
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setContentText("Médecin ajouté avec succès !");
            successAlert.showAndWait();

            // Effacez les champs après l'ajout réussi
            nom.clear();
            prenom.clear();
            specialite.clear();

        } catch (Exception e) {
            // Gérez les exceptions si nécessaire
            e.printStackTrace();
        }
    }

    @FXML
    void naviguerEtab(ActionEvent event) {
        // Naviguer vers l'interface de gestion des établissements si nécessaire
    }

    @FXML
    void naviguermed(ActionEvent event) {
        // Restez sur la même interface de gestion des médecins si nécessaire
    }

    @FXML
    void naviguerrendez(ActionEvent event) {
        // Naviguer vers l'interface de gestion des rendez-vous si nécessaire
    }
}
