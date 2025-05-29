package org.example.demo.controllers;

import BBDD.Consultas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Clase controlador principal

public class PrincipalController {


    @FXML
    private Button accesoClientes;

    @FXML
    private Button accesoMascotas;

    @FXML
    private Button accesoHMedico;

    @FXML
    void accesoClientes(ActionEvent event) throws IOException {
        abrirVentana("clientes",event);


    }

    @FXML
    void accesoMascotas(ActionEvent event) throws IOException{
        abrirVentana("mascotas",event);


    }
    @FXML
    void accesoHMedico(ActionEvent event) throws IOException{
        abrirVentana("historialMedico",event);


    }

    public void abrirVentana(String ventana,ActionEvent event) throws IOException {

        //Cargar FXML de la nueva ventana.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/"+ventana+".fxml"));
        Parent root = loader.load();

        //Crear la nueva escena.
        Scene scene = new Scene(root,800,500);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        currentStage.setScene(scene);

        currentStage.show();
    }

}
