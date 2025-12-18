package com.example.hackathon2fxjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AgendaAplicacion extends Application {
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AgendaAplicacion.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        //cargar css
        var cssResource = AgendaAplicacion.class.getResource("style.css");
        if (cssResource != null) {
            scene.getStylesheets().add(cssResource.toExternalForm());
        } else {
            System.out.println("Error: No se encontró el archivo style.css en la ruta especificada.");
        }

        stage.setTitle("Agenda Pro");
        stage.setScene(scene);
        stage.show();
    }
}