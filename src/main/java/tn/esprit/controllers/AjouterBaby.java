package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.entities.Baby;
import tn.esprit.services.BabyServices;

import java.io.IOException;
import java.time.LocalDate;

public class AjouterBaby {

    @FXML
    private DatePicker dateofbirth;

    @FXML
    private ChoiceBox<String> gender;
    private String [] genders={"masculin", "feminine"};
    @FXML
    public void initialize() {
        // Initialize the ChoiceBox type with the values "masculin" and "feminine"
        gender.getItems().addAll(genders);
    }

    @FXML
    private TextField lastname;

    @FXML
    private TextField name;

    @FXML
    private TextField size;

    @FXML
    private TextField weight;

    @FXML
    void ajouterBaby(ActionEvent event) {

            try {
                String nom = name.getText();
                String prenom = lastname.getText();
                String sexe = gender.getValue();
                LocalDate dateNaissance = dateofbirth.getValue();
                float poids = Float.parseFloat(weight.getText());
                float taille = Float.parseFloat(size.getText());

                // Vérifiez si tous les champs sont remplis
                if (nom.isEmpty() || prenom.isEmpty() || sexe == null || dateNaissance == null || weight.getText().isEmpty() || size.getText().isEmpty()) {
                    // Affichez un message d'erreur si un champ est vide
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Veuillez remplir tous les champs !");
                    alert.showAndWait();
                    return; // Sortez de la méthode
                }

                // Créez une instance de votre service BabyServices et ajoutez le bébé
                BabyServices babyServices = new BabyServices ();
                babyServices.add(new Baby(0, nom, prenom, sexe, dateNaissance, poids, taille));

                // Affichez un message de succès
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setContentText("Bébé ajouté avec succès !");
                successAlert.showAndWait();

                // Fermez la fenêtre actuelle si nécessaire
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();

                // Si vous devez naviguer vers une autre vue, utilisez FXMLLoader et affichez la nouvelle vue
                // FXMLLoader loader = new FXMLLoader(getClass().getResource("NOM_DE_VOTRE_FICHIER_FXML.fxml"));
                // Parent root = loader.load();
                // Scene scene = new Scene(root);
                // Stage newStage = new Stage();
                // newStage.setScene(scene);
                // newStage.setTitle("Titre de la nouvelle vue");
                // newStage.show();
                navigateToAjouterInfoMedicaux(event);

            } catch (Exception e) {
                // Gérez les exceptions si nécessaire
                e.printStackTrace();
            }
        }

    private void navigateToAjouterInfoMedicaux(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterInfoMedicaux.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Medical Information");
            stage.show();

            // Close the current window
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            // Handle exceptions if necessary
            e.printStackTrace();
        }
    }




    @FXML
    void initialize(ActionEvent event) {

    }

   

    public void naviguerInfoMedicaux(ActionEvent actionEvent) {
    }

    public void naviguerBaby(ActionEvent actionEvent) {
    }
}
