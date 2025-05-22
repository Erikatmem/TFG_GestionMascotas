package org.example.demo.controllers;

import BBDD.Consultas;
import Clases.HistorialMedico;
import Clases.Mascotas;
import Clases.Propietarios;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;

public class informeController {

    @FXML
    private Label etiqueta1;

    @FXML
    private Label etiqueta2;

    @FXML
    private Label etiqueta3;

    @FXML
    private Label etiqueta4;

    @FXML
    private Label etiqueta5;

    @FXML
    private Label etiqueta6;

    @FXML
    private Label etiqueta7;

    @FXML
    private Label etiqueta8;

    @FXML
    private Label informe;

    @FXML
    private Label valor1;

    @FXML
    private Label valor2;

    @FXML
    private Label valor3;

    @FXML
    private Label valor4;

    @FXML
    private Label valor5;

    @FXML
    private Label valor6;

    @FXML
    private Label valor7;

    @FXML
    private Label valor8;

    private Stage stage;
    private boolean initialized = false;
    private HistorialMedico antiguoHistMedico = null;
    private Propietarios antiguoPro = null;
    private Mascotas mascotas = null;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setAntiguoHistMedico(HistorialMedico hMedico) {
        this.antiguoHistMedico = hMedico;
        if (initialized) {
            informe.setText("INFORME *HISTORIAL MÉDICO*");
            etiqueta1.setText("ID: ");
            valor1.setText(hMedico.getId()+"");
            etiqueta2.setText("ID Mascota: ");
            valor2.setText(Consultas.consultarMascota(hMedico.getIdMascota()).getNombre());
            etiqueta3.setText("Fecha Consulta: ");
            valor3.setText(hMedico.getFechaConsulta()+"");
            etiqueta4.setText("Motivo Visita: ");
            valor4.setText(hMedico.getMotivoVisita()+"");
            etiqueta5.setText("Diagnóstico: ");
            valor5.setText(hMedico.getDiagnostico()+"");
            etiqueta6.setText("Tratamiento: ");
            if (hMedico.getTratamiento()==null){
                valor6.setText("Sin Especificar");
            }else{
                valor6.setText(hMedico.getTratamiento()+"");
            }

            etiqueta7.setText("Observaciones: ");
            valor7.setText(hMedico.getObservaciones()+"");
            etiqueta8.setText("Fecha Visita: ");
            valor8.setText(hMedico.getFechaVisita()+"");

        }
    }
    public void setAntiguoCliente(Propietarios propietarios) {
        this.antiguoPro = propietarios;
        if (initialized) {
            informe.setText("INFORME *PROPIETARIO*");
            etiqueta1.setText("ID: ");
            valor1.setText(antiguoPro.getId()+"");
            etiqueta2.setText("Nombre: ");
            valor2.setText(antiguoPro.getNombre());
            etiqueta3.setText("Apellidos: ");
            valor3.setText(antiguoPro.getApellidos());
            etiqueta4.setText("Email: ");
            valor4.setText(antiguoPro.getEmail());
            etiqueta5.setText("Dirección: ");
            valor5.setText(antiguoPro.getDireccion());


            etiqueta7.setText("Teléfono: ");
            valor7.setText(antiguoPro.getTelefono());
            etiqueta8.setText("");
            valor8.setText("");

        }
    }
    public void setAntiguaMascota(Mascotas mascota) {
        this.mascotas = mascota;
        if (initialized) {
            informe.setText("INFORME *MASCOTA*");
            etiqueta1.setText("ID: ");
            valor1.setText(mascotas.getId()+"");
            etiqueta2.setText("Nombre: ");
            valor2.setText(mascotas.getNombre());
            etiqueta3.setText("Raza: ");
            valor3.setText(mascotas.getRaza());
            etiqueta4.setText("Género: ");
            valor4.setText(mascotas.getGenero());
            etiqueta5.setText("Peso: ");
            valor5.setText(mascotas.getPeso()+" kg");
            etiqueta7.setText("Fecha de Nacimiento: ");
            valor7.setText(mascotas.getFechaNacimiento()+"");
            etiqueta8.setText("Propietario");
            valor8.setText( Consultas.consultarPropietarios(mascotas.getIdPropietario()).getNombre()+" "+Consultas.consultarPropietarios(mascotas.getIdPropietario()).getApellidos());

        }
    }

    @FXML
    private void initialize() {
        initialized = true;
    }


}
