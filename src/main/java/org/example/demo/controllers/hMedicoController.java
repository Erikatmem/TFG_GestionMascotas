package org.example.demo.controllers;

import BBDD.Consultas;
import Clases.HistorialMedico;
import Clases.Mascotas;
import Clases.Propietarios;
import Utils.UtilsJOptionPane;
import javafx.beans.property.SimpleStringProperty;
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

// Clase controlador histrial Medico.
public class hMedicoController {

    @FXML
    private TableColumn<HistorialMedico, Integer> columnaId;

    @FXML
    private TableColumn<HistorialMedico, Integer> columnaIdMascota;
    @FXML
    private TableColumn<HistorialMedico, String> columnaNombreMascota;

    @FXML
    private TableColumn<HistorialMedico, String> columnaMotivoVisita;

    @FXML
    private TableColumn<HistorialMedico, String> columnaObservaciones;

    @FXML
    private Button copiarHMedico;

    @FXML
    private Button crearHMedico;

    @FXML
    private Button editarHMedico;

    @FXML
    private Button eliminarHMedico;

    @FXML
    private TableView<HistorialMedico> tablaHMedico;
    private final ObservableList<HistorialMedico> datos = FXCollections.observableArrayList();


    @FXML
    void atras(ActionEvent event) throws IOException {
        abrirVentana("principal", event);
    }

    public void abrirVentana(String ventana, ActionEvent event) throws IOException {

        //Cargar FXML de la nueva ventana.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/" + ventana + ".fxml"));
        Parent root = loader.load();

        //Crear la nueva escena.
        Scene scene = new Scene(root, 800, 500);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        currentStage.setScene(scene);
        currentStage.show();
    }


    @FXML
    void copiarHMedico(ActionEvent event) {
        HistorialMedico seleccionado = tablaHMedico.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            Consultas.copiarHMedico(seleccionado);
            actualizarRegistros();
        } else {
            JOptionPane.showMessageDialog(null, "Aviso. Para copiar debe seleccionar el historial", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void crearHMedico(ActionEvent event) {
        ventanaCreacion();
    }

    @FXML
    void editarHMedico(ActionEvent event) {
        ventanaEdicion();
    }

    @FXML
    void eliminarHMedico(ActionEvent event) {
        HistorialMedico seleccionado = tablaHMedico.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro que desea eliminar?");
            if (opcion == 0) {
                Consultas.eliminarHMedico(seleccionado);
                actualizarRegistros();
                UtilsJOptionPane.OPERACION_CONFIRMADA();

            } else {
                UtilsJOptionPane.OPERACION_CANCEALDA();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aviso. Para eliminar debe seleccionar el historial.", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    @FXML
    void verInforme(ActionEvent event) {
        ventanaInforme();
    }

    @FXML
    private void initialize() {
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaIdMascota.setCellValueFactory(new PropertyValueFactory<>("idMascota"));
        columnaMotivoVisita.setCellValueFactory(new PropertyValueFactory<>("motivoVisita"));
        columnaObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        columnaNombreMascota.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(Consultas.consultarMascota(cellData.getValue().getIdMascota()).getNombre());
        });
        actualizarRegistros();
    }

    private void actualizarRegistros() {
        if (datos.size() > 0) {
            datos.clear();
        }
        datos.addAll(Consultas.consultarHistorialesMedicos());
        tablaHMedico.setItems(datos);
    }

    private void ventanaCreacion() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/edicionHMedico.fxml"));
        try {
            Parent root = loader.load();
            edicionHMedicoController modalController = loader.getController();
            Stage modalStage = new Stage();
            modalController.setStage(modalStage);
            modalStage.setTitle("Ventana Modal");
            modalStage.setScene(new Scene(root));

            modalStage.initModality(Modality.APPLICATION_MODAL);

            modalStage.showAndWait();

            HistorialMedico nuevoObjeto = modalController.getDatos();
            if (nuevoObjeto != null) {
                Consultas.copiarHMedico(nuevoObjeto);
                actualizarRegistros();
            } else {
                UtilsJOptionPane.OPERACION_CANCEALDA();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void ventanaEdicion() {
        HistorialMedico seleccionado = tablaHMedico.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/edicionHMedico.fxml"));
            try {
                Parent root = loader.load();

                edicionHMedicoController modalController = loader.getController();


                Stage modalStage = new Stage();
                modalController.setStage(modalStage);
                modalController.setAntiguoHistMedico(seleccionado); //NO ES EDICIÓN
                modalStage.setTitle("Ventana Modal");

                modalStage.setScene(new Scene(root));

                modalStage.initModality(Modality.APPLICATION_MODAL);

                modalStage.showAndWait();

                HistorialMedico historialMedico = modalController.getDatos();
                if (historialMedico != null) {
                    Consultas.modificarHistMedico(historialMedico);
                    actualizarRegistros();
                } else {
                    UtilsJOptionPane.OPERACION_CANCEALDA();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aviso. Para editar debe seleccionar el historial.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ventanaInforme() {
        HistorialMedico seleccionado = tablaHMedico.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/informe.fxml"));
            try {
                Parent root = loader.load();

                informeController modalController = loader.getController();


                Stage modalStage = new Stage();
                modalController.setStage(modalStage);
                modalController.setAntiguoHistMedico(seleccionado); //NO ES EDICIÓN
                modalStage.setTitle("Ventana Modal");

                modalStage.setScene(new Scene(root));

                modalStage.initModality(Modality.APPLICATION_MODAL);

                modalStage.showAndWait();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aviso. Seleccione para ver el informe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
