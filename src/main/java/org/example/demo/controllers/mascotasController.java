package org.example.demo.controllers;

import BBDD.Consultas;
import Clases.Mascotas;
import Clases.Propietarios;
import Utils.UtilsJOptionPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.Date;

public class mascotasController {

    @FXML
    private TableColumn<Mascotas, Date> columnaFechaNacimiento;

    @FXML
    private TableColumn<Mascotas, Integer> columnaId;

    @FXML
    private TableColumn<Mascotas, Integer> columnaIdPropietario;

    @FXML
    private TableColumn<Mascotas, String> columnaNombre;

    @FXML
    private TableColumn<Mascotas, Double> columnaPeso;

    @FXML
    private TableColumn<Mascotas, String> columnaRaza;

    @FXML
    private TableColumn<Mascotas, String> columnaGenero;

    @FXML
    private Button copiarMascota;

    @FXML
    private Button crearMascota;

    @FXML
    private Button editarMascota;

    @FXML
    private Button eliminarMascota;

    @FXML
    private TableView<Mascotas> tablaMascotas;
    private final ObservableList<Mascotas> datos = FXCollections.observableArrayList();


    @FXML
    void copiarMascota(ActionEvent event) {
        Mascotas seleccionado = tablaMascotas.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            Consultas.copiarMascotas(seleccionado);
            actualizarRegistros();
        } else {
            JOptionPane.showMessageDialog(null, "Error. Para copiar debe asegurarse de haber un seleccionado un propietario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void crearMascota(ActionEvent event) {
        ventanaCreacionPropietario();
    }

    @FXML
    void editarMascota(ActionEvent event) {
        ventanaEdicionPropietario();

    }

    @FXML
    void eliminarMascota(ActionEvent event) {
        Mascotas seleccionado = tablaMascotas.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            Consultas.eliminarMascotas(seleccionado);
            actualizarRegistros();
        } else {
            JOptionPane.showMessageDialog(null, "Error. Para eliminar un propietario debe asegurarse de haber un seleccionado un propietario", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }
    @FXML
    void atras(ActionEvent event) throws IOException {
        abrirVentana("principal",event);
    }
    public void abrirVentana(String ventana,ActionEvent event) throws IOException {
        //JOptionPane.showMessageDialog(null,"Mensaje automático CLIENTES","Prueba",JOptionPane.QUESTION_MESSAGE);

        //Cargar FXML de la nueva ventana.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/"+ventana+".fxml"));
        Parent root = loader.load();

        //Crear la nueva escena.
        Scene scene = new Scene(root,700,500);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        currentStage.setScene(scene);

        currentStage.show();
    }

    @FXML
    private void initialize() {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaIdPropietario.setCellValueFactory(new PropertyValueFactory<>("idPropietario"));
        columnaFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        columnaRaza.setCellValueFactory(new PropertyValueFactory<>("raza"));
        columnaPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        columnaGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        actualizarRegistros();
    }

    private void actualizarRegistros() {
        if (datos.size() > 0) {
            datos.clear();
        }
        datos.addAll(Consultas.consultarMascotas());
        tablaMascotas.setItems(datos);
    }

    private void ventanaCreacionPropietario() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/edicionMascotas.fxml"));
        try {
            Parent root = loader.load();
            edicionMascotasController modalController = loader.getController();
            Stage modalStage = new Stage();
            modalController.setStage(modalStage);
            modalStage.setTitle("Ventana Modal");
            modalStage.setScene(new Scene(root));

            modalStage.initModality(Modality.APPLICATION_MODAL);

            modalStage.showAndWait();

            Mascotas nuevaMascotas = modalController.getDatos();
            if (nuevaMascotas != null) {
                Consultas.copiarMascotas(nuevaMascotas);
                actualizarRegistros();
            } else {
                UtilsJOptionPane.OPERACION_CANCEALDA();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void ventanaEdicionPropietario() {
        Mascotas seleccionado = tablaMascotas.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/edicionMascotas.fxml"));
            try {
                Parent root = loader.load();

                edicionMascotasController modalController = loader.getController();


                Stage modalStage = new Stage();
                modalController.setStage(modalStage);
                modalController.setAntiguaMascota(seleccionado); //NO ES EDICIÓN
                modalStage.setTitle("Ventana Modal");

                modalStage.setScene(new Scene(root));

                modalStage.initModality(Modality.APPLICATION_MODAL);

                modalStage.showAndWait();

                Mascotas nuevaMascota = modalController.getDatos();
                if (nuevaMascota != null) {
                    Consultas.modificarMascotas(nuevaMascota);
                    actualizarRegistros();
                } else {
                    UtilsJOptionPane.OPERACION_CANCEALDA();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. Para copiar debe asegurarse de haber un seleccionado un propietario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
