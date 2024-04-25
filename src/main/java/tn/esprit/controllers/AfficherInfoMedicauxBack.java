package tn.esprit.controllers;

import javafx.scene.control.TableCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.entities.Baby;
import tn.esprit.entities.InfoMedicaux;
import tn.esprit.services.BabyServices;
import tn.esprit.services.InfoMedicauxServices;
import tn.esprit.util.MaConnexion;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class AfficherInfoMedicauxBack {

    @FXML
    private TableView<InfoMedicaux> InfoMedicauxtable;

    @FXML
    private Button babylisteButton;

    @FXML
    private TableColumn<InfoMedicaux, String> bloodtypeColumn;

    @FXML
    private TableColumn<InfoMedicaux, LocalDate> datevaccinColumn;



    @FXML
    private TableColumn<InfoMedicaux, String> descriptioncolomn;

    @FXML
    private Button infoMedicauxButton;

    @FXML
    private TableColumn<InfoMedicaux, String> maladiecolomn;

    @FXML
    private TableColumn<InfoMedicaux, String> nombabycolumn;

    @FXML
    private TableColumn<InfoMedicaux, Integer> nombrevaccinColumn;

    @FXML
    private TableColumn<InfoMedicaux, String> sicknessestimationColumn;

    @FXML
    private TableColumn<InfoMedicaux, Void> updateColumn;

    @FXML
    private TableColumn<InfoMedicaux, Void> deleteColumn;

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

    public  AfficherInfoMedicauxBack() {
        Connection connection = MaConnexion.getInstance().getCnx();

    }
    @FXML
    void initialize() {
        try {


            InfoMedicauxServices infoMedicauxServices  = new InfoMedicauxServices(MaConnexion.getInstance().getCnx());
            List<InfoMedicaux> infoMedicauxs = infoMedicauxServices.getAll();
            ObservableList<InfoMedicaux> observableList = FXCollections.observableList(infoMedicauxs);
            InfoMedicauxtable.setItems(observableList);

            nombabycolumn.setCellValueFactory(new PropertyValueFactory<>("nomBaby"));
            maladiecolomn.setCellValueFactory(new PropertyValueFactory<>("maladie"));
            descriptioncolomn.setCellValueFactory(new PropertyValueFactory<>("description"));
            nombrevaccinColumn.setCellValueFactory(new PropertyValueFactory<>("nbr_vaccin"));
            datevaccinColumn.setCellValueFactory(new PropertyValueFactory<>("date_vaccin"));
            bloodtypeColumn.setCellValueFactory(new PropertyValueFactory<>("blood_type"));
            sicknessestimationColumn.setCellValueFactory(new PropertyValueFactory<>("sickness_estimation"));

            updateColumn.setCellFactory(param -> new TableCell<>() {
                private final Button modifierButton = new Button("Modifier");

                {
                    modifierButton.setOnAction(event -> {
                        InfoMedicaux infoMedicaux = getTableView().getItems().get(getIndex());
                        modifierinfoMedicaux(infoMedicaux);
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
                        InfoMedicaux infoMedicaux = getTableView().getItems().get(getIndex());
                        infoMedicauxServices.delete(infoMedicaux);
                        observableList.remove(infoMedicaux);
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
            alert.setContentText("Une erreur s'est produite lors du chargement des infoMedicaux.");
            alert.showAndWait();
        }
    }



    @FXML
    void modifierinfoMedicaux(InfoMedicaux infoMedicaux) {
        try {
            InfoMedicauxServices infoMedicauxServices = new InfoMedicauxServices(MaConnexion.getInstance().getCnx());
            InfoMedicaux infoMedicauxAModifier = infoMedicauxServices.getOne(infoMedicaux.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierinfoMedicaux.fxml"));
            Parent root = loader.load();
            ModifierInfoMedicaux controller = loader.getController();
            controller.initData(infoMedicauxAModifier);
            Stage window = (Stage) InfoMedicauxtable.getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Une erreur s'est produite lors du chargement de la vue.");
            alert.showAndWait();
        }
    }

    public void naviguer(ActionEvent actionEvent) {
    }
}

