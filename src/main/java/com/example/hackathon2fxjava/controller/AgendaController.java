package com.example.hackathon2fxjava.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import javafx.scene.control.Label;

public class AgendaController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void Entrar() {
        ButtonType btnPorDefecto = new ButtonType("Por defecto (10)");
        ButtonType btnPersonalizado = new ButtonType("Tamaño manual");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Configuracion de Agenda");
        alert.setHeaderText("¿Cómo deseas inicializar tu agenda?");
        alert.setContentText("Elige el tamaño máximo de contactos:");

        alert.getButtonTypes().setAll(btnPorDefecto, btnPersonalizado);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == btnPorDefecto){
            System.out.println("Creando agenda de 10 Contactos");
        } else {
            pedirTamañoManual();
        }
    }

    private void pedirTamañoManual(){
        TextInputDialog dialog = new TextInputDialog("15");
        dialog.setTitle("Tamaño Personalizado");
        dialog.setHeaderText("Indica el tamaño máximo");
        dialog.setContentText("Capacidad:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(size ->{
            try {
                int capacidad = Integer.parseInt(size);
                System.out.println("Creando agenda de: " + capacidad);
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un número válido");
            }
        });
    }
}

