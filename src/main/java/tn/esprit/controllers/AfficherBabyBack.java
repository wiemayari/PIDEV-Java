package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.entities.Baby;
import tn.esprit.services.BabyServices;
import tn.esprit.util.MaConnexion;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class AfficherBabyBack {

    @FXML
    private TableView<Baby> Babytable;

    @FXML
    private TableColumn<Baby, LocalDate> dateNaissanceColumn;

    @FXML
    private TableColumn<Baby, String> nomcolumn;

    @FXML
    private TableColumn<Baby, Float> poidsColumn;

    @FXML
    private TableColumn<Baby, String> prenomcolomn;

    @FXML
    private TableColumn<Baby, String> sexecolomn;

    @FXML
    private TableColumn<Baby, Float> tailleColumn;

    @FXML
    private TableColumn<Baby, Void> updateColumn;

    @FXML
    private TableColumn<Baby, Void> deleteColumn;

    @FXML
    void naviguerBaby(ActionEvent event) {
        try {
                Parent root = FXMLLoader.load(getClass().getResource("/AfficherBabyBack.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void naviguerInfoMedicaux(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherInfoMedicauxBack.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Une erreur s'est produite lors du chargement de la vue.");
            alert.showAndWait();
        }
    }

    public  AfficherBabyBack() {
        Connection connection = MaConnexion.getInstance().getCnx();
        BabyServices BabyServices = new BabyServices(connection);
    }

    @FXML
    void initialize() {
        try {
            BabyServices babyServices = new BabyServices(MaConnexion.getInstance().getCnx());
            List<Baby> babies = babyServices.getAll();
            ObservableList<Baby> observableList = FXCollections.observableList(babies);
            Babytable.setItems(observableList);

            nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenomcolomn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            sexecolomn.setCellValueFactory(new PropertyValueFactory<>("sexe"));
            dateNaissanceColumn.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
            tailleColumn.setCellValueFactory(new PropertyValueFactory<>("taille"));
            poidsColumn.setCellValueFactory(new PropertyValueFactory<>("poids"));

            updateColumn.setCellFactory(param -> new TableCell<>() {
                private final Button modifierButton = new Button("Modifier");

                {
                    modifierButton.setOnAction(event -> {
                        Baby baby = getTableView().getItems().get(getIndex());
                        modifierbaby(baby);
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(modifierButton);
                    }
                }
            });

            deleteColumn.setCellFactory(param -> new TableCell<>() {
                private final Button supprimerButton = new Button("Supprimer");

                {
                    supprimerButton.setOnAction(event -> {
                        Baby baby = getTableView().getItems().get(getIndex());
                        babyServices.delete(baby);
                        observableList.remove(baby);
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(supprimerButton);
                    }
                }
            });

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Une erreur s'est produite lors du chargement des établissements.");
            alert.showAndWait();
        }
    }

    @FXML
    void modifierbaby(Baby baby) {
        try {
            BabyServices babyServices = new BabyServices(MaConnexion.getInstance().getCnx());
            Baby babyAModifier = babyServices.getOne(baby.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierBaby.fxml"));
            Parent root = loader.load();
            ModifierBaby controller = loader.getController();
            controller.initData(babyAModifier);
            Stage window = (Stage) Babytable.getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Une erreur s'est produite lors du chargement de la vue.");
            alert.showAndWait();
        }
    }
}
