package org.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/*
* Clase principal de la aplicación.
* Se encarga de iniciar la interfaz gráfica cargando el archivo FXML correspondiente.
*/
public class App extends Application {

// Título que se mostrará en la ventana principal al iniciar la aplicación.
    final String tituloPantallaInicio = "Pestaña Principal";

// Metodo principal de JavaFX. Se ejecuta al iniciar la aplicación.
// Carga la interfaz gráfica desde un archivo FXML y la muestra en pantalla.
// Siguiendo la arquitectura de JAVAFX → (Escenario (Stage), Escena (Scene))

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle(tituloPantallaInicio);
        stage.setScene(scene);
        stage.show();
    }

// Metodo principal de la aplicación.
    public static void main(String[] args) {
        launch();
    }
}