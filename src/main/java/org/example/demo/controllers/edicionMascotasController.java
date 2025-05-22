package org.example.demo.controllers;

import BBDD.Consultas;
import Clases.Mascotas;
import Clases.Propietarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class edicionMascotasController {

    @FXML
    private Button aceptar;

    @FXML
    private Button cancelar;

    @FXML
    private ComboBox<Propietarios> comboPropietario;

    @FXML
    private DatePicker dateFechaNacimiento;

    @FXML
    private TextField textNombre;

    @FXML
    private TextField textPeso;

    @FXML
    private TextField textRaza;

    private Mascotas datos;
    private Mascotas antiguaMasc = null;
    private Stage stage;
    private boolean initialized = false;

    //Método entre ventanas
    public Mascotas getDatos() {
        return datos;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private ComboBox<String> comboGenero;

    public void setAntiguaMascota(Mascotas antiguaMasc) {
        this.antiguaMasc = antiguaMasc;
        if (initialized) {
            cargarDatosSiExisten();
        }
    }

    private void cargarDatosSiExisten() {
        if (antiguaMasc != null) {
            textNombre.setText(antiguaMasc.getNombre());
            textPeso.setText(antiguaMasc.getPeso() + "");
            textRaza.setText(antiguaMasc.getRaza());
            comboPropietario.setValue(Consultas.consultarPropietarios(antiguaMasc.getIdPropietario()));
            dateFechaNacimiento.setValue(new java.sql.Date(antiguaMasc.getFechaNacimiento().getTime()).toLocalDate());
            comboGenero.setValue(antiguaMasc.getGenero());
            //dateFechaNacimiento.setValue(antiguaMasc.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        } else {
        }
    }

    @FXML
    private void initialize() {
        ArrayList<Propietarios> p = Consultas.consultarPropietarios();
        for (Propietarios prop : p){
            comboPropietario.getItems().add(prop);
        }
        comboGenero.getItems().addAll("Macho","Hembra");


// Valor por defecto (selección vacía)
        comboPropietario.getSelectionModel().select(null);
        initialized = true;
        cargarDatosSiExisten();
    }


    @FXML
    void aceptar(ActionEvent event) {
        if (antiguaMasc != null) {
            datos = new Mascotas(antiguaMasc.getId(), comboPropietario.getValue().getId(), textNombre.getText(),
                    textRaza.getText(),Double.parseDouble(textPeso.getText()), Date.from(dateFechaNacimiento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),comboGenero.getValue());

        } else {
            datos = new Mascotas(1, comboPropietario.getValue().getId(), textNombre.getText(),
                    textRaza.getText(),Double.parseDouble(textPeso.getText()), Date.from(dateFechaNacimiento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),comboGenero.getValue());

        }
        stage.close();
    }

    @FXML
    void cancelar(ActionEvent event) {
        stage.close();


    }

    @FXML
    void comboPropietario(ActionEvent event) {

    }

    @FXML
    void comboGenero(ActionEvent event) {

    }

}
