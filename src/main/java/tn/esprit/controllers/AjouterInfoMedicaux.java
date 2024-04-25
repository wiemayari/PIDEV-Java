package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tn.esprit.entities.InfoMedicaux;

import tn.esprit.services.InfoMedicauxServices;

import java.sql.Date;
import java.time.LocalDate;

public class AjouterInfoMedicaux {

    @FXML
    private ChoiceBox<String> bloodtype;
    private String[] bloodtypes = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};



    @FXML
    private DatePicker dateoflastvaccin;

    @FXML
    private TextArea description;

    @FXML
    private TextField diseasename;

    @FXML
    private TextField numberofvaccines;

    @FXML
    private ChoiceBox<String> sicknessestimation;
    private String[] sicknessestimations = {"25%", "50%", "75%", "100%"};

    @FXML
    public void initialize() {
        // Initialize the ChoiceBox type with the values "O+", "O-", etc.
        bloodtype.getItems().addAll(bloodtypes);

        // Initialize the ChoiceBox for sickness estimation
        sicknessestimation.getItems().addAll(sicknessestimations);
    }


    @FXML
    void ajouterInfoMedicaux(ActionEvent event) {
        try {
            int babyNameId = 1; // Remplacez 1 par l'ID réel du bébé, car il est actuellement en dur.
            String maladie = diseasename.getText();
            String desc = description.getText();
            int nbrVaccin = Integer.parseInt(numberofvaccines.getText());
            LocalDate dateVaccin = dateoflastvaccin.getValue();
            String bloodType = bloodtype.getValue();
            String sicknessEstimation = sicknessestimation.getValue();

            // Vérifiez si tous les champs sont remplis
            if (maladie.isEmpty() || desc.isEmpty() || dateVaccin == null || bloodType == null || sicknessEstimation == null) {
                // Affichez un message d'erreur si un champ est vide
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Veuillez remplir tous les champs !");
                alert.showAndWait();
                return; // Sortez de la méthode
            }

            // Créez une instance de votre service InfoMedicauxService et ajoutez les informations médicales
            InfoMedicauxServices infoMedicauxService = new InfoMedicauxServices();
            infoMedicauxService.add(new InfoMedicaux(0, babyNameId, maladie, desc, nbrVaccin, Date.valueOf(dateVaccin), bloodType, sicknessEstimation));

            // Affichez un message de succès
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setContentText("Informations médicales ajoutées avec succès !");
            successAlert.showAndWait();
        } catch (Exception e) {
            // Gérez les exceptions si nécessaire
            e.printStackTrace();
        }
    }

    @FXML
    void initialize(ActionEvent event) {
    }

    @FXML
    void naviguerEtab(ActionEvent event) {
    }

    @FXML
    void naviguerrendez(ActionEvent event) {
    }
}
