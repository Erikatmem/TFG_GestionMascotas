package org.example.demo.controllers;

import Clases.Propietarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class edicionPropietarioController {

    @FXML
    private Button aceptar;

    @FXML
    private Button cancelar;

    @FXML
    private TextField textApellidos;

    @FXML
    private TextField textDireccion;

    @FXML
    private TextField textEmail;

    @FXML
    private TextField textNombre;

    @FXML
    private TextField textTelefono;

    private Propietarios datos;
    private Propietarios antiguoProp = null;
    private Stage stage;
    private boolean initialized = false;

    //Método entre ventanas
    public Propietarios getDatos() {
        return datos;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setAntiguoProp(Propietarios antiguoProp) {
        this.antiguoProp = antiguoProp;
        if (initialized) {
            cargarDatosSiExisten();
        }
    }
    private void cargarDatosSiExisten() {
        if (antiguoProp != null) {
            textNombre.setText(antiguoProp.getNombre());
            textApellidos.setText(antiguoProp.getApellidos());
            textDireccion.setText(antiguoProp.getDireccion());
            textTelefono.setText(antiguoProp.getTelefono());
            textEmail.setText(antiguoProp.getEmail());
        } else {
        }
    }

    @FXML
    private void initialize() {
        initialized = true;
        cargarDatosSiExisten();
    }


    @FXML
    void aceptar(ActionEvent event) {
        if (antiguoProp != null){
            datos = new Propietarios(
                    antiguoProp.getId(), textDireccion.getText(),
                    textEmail.getText(), textTelefono.getText(),
                    textApellidos.getText(), textNombre.getText());
        }else{
            datos = new Propietarios(1,
                    textDireccion.getText(),
                    textEmail.getText(), textTelefono.getText(),
                    textApellidos.getText(), textNombre.getText());
        }
        stage.close();
    }

    @FXML
    void cancelar(ActionEvent event) {
        stage.close();


    }

}
