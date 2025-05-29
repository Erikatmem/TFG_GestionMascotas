package org.example.demo.controllers;

import BBDD.Consultas;
import Clases.HistorialMedico;
import Clases.Mascotas;
import Clases.Propietarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class edicionHMedicoController {

    @FXML
    private Button aceptar;

    @FXML
    private Button cancelar;

    @FXML
    private ComboBox<Mascotas> comboMascota;

    @FXML
    private TextArea diagnostico;

    @FXML
    private DatePicker fechaConsulta;

    @FXML
    private TextArea motivoVisita;

    @FXML
    private TextArea observaciones;

    @FXML
    private DatePicker proximaVisita;

    @FXML
    private TextArea tratamiento;

    private HistorialMedico datos;
    private HistorialMedico antiguoHistMedico = null;
    private Stage stage;
    private boolean initialized = false;

    //Metodo entre ventanas.
    public HistorialMedico getDatos() {
        return datos;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setAntiguoHistMedico(HistorialMedico hMedico) {
        this.antiguoHistMedico = hMedico;
        if (initialized) {
            cargarDatosSiExisten();
        }
    }

    private void cargarDatosSiExisten() {
        if (antiguoHistMedico != null) {
            fechaConsulta.setValue(new java.sql.Date(antiguoHistMedico.getFechaConsulta().getTime()).toLocalDate());
            proximaVisita.setValue(new java.sql.Date(antiguoHistMedico.getFechaVisita().getTime()).toLocalDate());
            comboMascota.setValue(Consultas.consultarMascota(antiguoHistMedico.getIdMascota()));
            diagnostico.setText(antiguoHistMedico.getDiagnostico());
            motivoVisita.setText(antiguoHistMedico.getMotivoVisita());
            observaciones.setText(antiguoHistMedico.getObservaciones());
            tratamiento.setText(antiguoHistMedico.getTratamiento());
        } else {
        }
    }

    @FXML
    private void initialize() {
        ArrayList<Mascotas> p = Consultas.consultarMascotas();
        for (Mascotas mascota : p){
            comboMascota.getItems().add(mascota);
        }

        initialized = true;
        cargarDatosSiExisten();
    }


    @FXML
    void aceptar(ActionEvent event) {


        if (antiguoHistMedico != null) {
            datos = new HistorialMedico(antiguoHistMedico.getId(),
                    comboMascota.getValue().getId()
                    , Date.from(fechaConsulta.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(proximaVisita.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    motivoVisita.getText(), diagnostico.getText(), tratamiento.getText(), observaciones.getText());
        } else {
            datos = new HistorialMedico(0,
                    comboMascota.getValue().getId()
                    , Date.from(fechaConsulta.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(proximaVisita.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    motivoVisita.getText(), diagnostico.getText(), tratamiento.getText(), observaciones.getText());

        }

        stage.close();
    }

    @FXML
    void cancelar(ActionEvent event) {
        stage.close();


    }
    @FXML
    void comboMascota(ActionEvent event) {

    }

}
