package org.example.demo.controllers;

import BBDD.Consultas;
import Clases.HistorialMedico;
import Clases.Propietarios;
import Utils.UtilsJOptionPane;
import javafx.beans.Observable;
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


public class clientesController {

    @FXML
    private TableColumn<Propietarios, String> columnaApellidos;

    @FXML
    private TableColumn<Propietarios, String> columnaDireccion;

    @FXML
    private TableColumn<Propietarios, String> columnaEmail;

    @FXML
    private TableColumn<Propietarios, Integer> columnaId;

    @FXML
    private TableColumn<Propietarios, String> columnaNombre;

    @FXML
    private TableColumn<Propietarios, String> columnaTelefono;

    @FXML
    private TableView<Propietarios> tablaPropietarios;

    private final ObservableList<Propietarios> datos = FXCollections.observableArrayList();

    @FXML
    private Button copiarProp;

    @FXML
    private Button crearProp;

    @FXML
    private Button editarProp;

    @FXML
    private Button eliminarProp;

    @FXML
    void copiarProp(ActionEvent event) {
        Propietarios seleccionado = tablaPropietarios.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            Consultas.copiarPropietario(seleccionado);
            actualizarRegistros();
        } else {
            JOptionPane.showMessageDialog(null, "Aviso. Para copiar debe seleccionar un propietario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void crearProp(ActionEvent event) {
        ventanaCreacionPropietario();
    }


    @FXML
    void editarProp(ActionEvent event) {
        ventanaEdicionPropietario();
    }

    @FXML
    void eliminarProp(ActionEvent event) {
        Propietarios seleccionado = tablaPropietarios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro que desea eliminar?");
            if (opcion == 0) {
                Consultas.eliminarPropietario(seleccionado);
                actualizarRegistros();
                UtilsJOptionPane.OPERACION_CONFIRMADA();
            } else {
                UtilsJOptionPane.OPERACION_CANCEALDA();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aviso. Para eliminar debe seleccionar un propietario.", "Error", JOptionPane.ERROR_MESSAGE);

        }


    }

    @FXML
    void atras(ActionEvent event) throws IOException {
        abrirVentana("principal", event);
    }

    public void abrirVentana(String ventana, ActionEvent event) throws IOException {
        //JOptionPane.showMessageDialog(null,"Mensaje automático CLIENTES","Prueba",JOptionPane.QUESTION_MESSAGE);

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
    private void initialize() {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        actualizarRegistros();
    }

    private void actualizarRegistros() {
        if (datos.size() > 0) {
            datos.clear();
        }
        datos.addAll(Consultas.consultarPropietarios());
        tablaPropietarios.setItems(datos);
    }

    private void ventanaCreacionPropietario() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/edicionPropietario.fxml"));
        try {
            Parent root = loader.load();
            edicionPropietarioController modalController = loader.getController();
            Stage modalStage = new Stage();
            modalController.setStage(modalStage);
            modalStage.setTitle("Ventana Modal");
            modalStage.setScene(new Scene(root));

            modalStage.initModality(Modality.APPLICATION_MODAL);

            modalStage.showAndWait();

            Propietarios nuevoPropietario = modalController.getDatos();
            if (nuevoPropietario != null) {
                Consultas.copiarPropietario(nuevoPropietario);
                actualizarRegistros();
            } else {
                UtilsJOptionPane.OPERACION_CANCEALDA();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void ventanaEdicionPropietario() {
        Propietarios seleccionado = tablaPropietarios.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/edicionPropietario.fxml"));
            try {
                Parent root = loader.load();

                edicionPropietarioController modalController = loader.getController();


                Stage modalStage = new Stage();
                modalController.setStage(modalStage);
                modalController.setAntiguoProp(seleccionado); //NO ES EDICIÓN
                modalStage.setTitle("Ventana Modal");

                modalStage.setScene(new Scene(root));

                modalStage.initModality(Modality.APPLICATION_MODAL);

                modalStage.showAndWait();

                Propietarios nuevoPropietario = modalController.getDatos();
                if (nuevoPropietario != null) {
                    Consultas.modificarPropietario(nuevoPropietario);
                    actualizarRegistros();
                } else {
                    UtilsJOptionPane.OPERACION_CANCEALDA();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aviso. Para copiar debe seleccionar un propietario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void verInforme(ActionEvent event) {
        ventanaInforme();
    }

    private void ventanaInforme() {
        Propietarios seleccionado = tablaPropietarios.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/informe.fxml"));
            try {
                Parent root = loader.load();

                informeController modalController = loader.getController();


                Stage modalStage = new Stage();
                modalController.setStage(modalStage);
                modalController.setAntiguoCliente(seleccionado); //NO ES EDICIÓN
                modalStage.setTitle("Ventana Modal");

                modalStage.setScene(new Scene(root));

                modalStage.initModality(Modality.APPLICATION_MODAL);

                modalStage.showAndWait();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aviso. Seleccione para ver el informe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
