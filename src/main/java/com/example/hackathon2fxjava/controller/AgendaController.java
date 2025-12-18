package com.example.hackathon2fxjava.controller;

import com.example.hackathon2fxjava.model.Agenda;
import com.example.hackathon2fxjava.model.Contacto;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.Optional;

public class AgendaController {

    @FXML private VBox welcomeVBox;
    @FXML private BorderPane mainContainer;
    @FXML private VBox contentArea; // El contenedor central que limpiaremos
    @FXML private Label welcomeText;

    private Agenda miAgenda;

    // --- LÓGICA DE INICIO ---

    @FXML
    protected void Entrar() {
        ButtonType btnPorDefecto = new ButtonType("Por defecto (10)");
        ButtonType btnPersonalizado = new ButtonType("Tamaño manual");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Configuración");
        alert.setHeaderText("Inicializar Agenda");
        alert.setContentText("Selecciona el tamaño de la agenda:");
        alert.getButtonTypes().setAll(btnPorDefecto, btnPersonalizado);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == btnPorDefecto) {
                miAgenda = new Agenda(10);
                mostrarAgendaPrincipal();
            } else {
                pedirTamañoManual();
            }
        }
    }

    private void pedirTamañoManual() {
        TextInputDialog dialog = new TextInputDialog("15");
        dialog.setHeaderText("Capacidad máxima:");
        dialog.showAndWait().ifPresent(size -> {
            try {
                miAgenda = new Agenda(Integer.parseInt(size));
                mostrarAgendaPrincipal();
            } catch (NumberFormatException e) {
                welcomeText.setText("Error: Introduce un número válido.");
                welcomeText.setTextFill(Color.RED);
            }
        });
    }

    private void mostrarAgendaPrincipal() {
        welcomeVBox.setVisible(false);
        mainContainer.setVisible(true);
        mostrarMensajeEnCentro("Agenda Lista", "Selecciona una opción a la izquierda para gestionar tus contactos.");
    }

    // MÉTODOS DEL MENÚ

    @FXML
    private void añadir() {
        String nombre = solicitarEntrada("Nombre");
        String apellido = solicitarEntrada("Apellido");
        String tel = solicitarEntrada("Teléfono");

        if (nombre.isEmpty() || apellido.isEmpty()) return;

        try {
            Contacto nuevo = new Contacto(nombre, apellido, tel);
            miAgenda.añadirContacto(nuevo);
            mostrarMensajeEnCentro("✅ Contacto Añadido", "Se ha guardado a: " + nuevo.toString());
        } catch (Exception e) {
            mostrarErrorEnCentro("Error al añadir", e.getMessage());
        }
    }

    @FXML
    private void listar() {
        limpiarCentro();

        Label titulo = new Label("📔 Lista de Contactos");
        titulo.setFont(Font.font("System", FontWeight.BOLD, 20));

        String[] contactos = miAgenda.listarContactos();

        if (contactos.length == 0) {
            mostrarMensajeEnCentro("Agenda Vacía", "No hay contactos registrados aún.");
        } else {
            ListView<String> listView = new ListView<>();
            listView.getItems().addAll(contactos);
            listView.setPrefHeight(700);
            listView.setMaxWidth(700);

            contentArea.getChildren().addAll(titulo, listView);
        }
    }

    @FXML
    private void buscar() {
        String nombre = solicitarEntrada("Nombre");
        String apellido = solicitarEntrada("Apellido");

        Contacto encontrado = miAgenda.buscaContacto(nombre, apellido);
        if (encontrado != null) {
            mostrarMensajeEnCentro("🔍 Contacto Encontrado",
                    "Nombre: " + encontrado.getNombre() + "\n" +
                            "Apellido: " + encontrado.getApellido() + "\n" +
                            "Teléfono: " + encontrado.getTelefono());
        } else {
            mostrarErrorEnCentro("No encontrado", "El contacto no existe en la agenda.");
        }
    }

    @FXML
    private void eliminar() {
        String nombre = solicitarEntrada("Nombre");
        String apellido = solicitarEntrada("Apellido");
        Contacto temp = new Contacto(nombre, apellido, "000000000");

        if (miAgenda.eliminarContacto(temp)) {
            mostrarMensajeEnCentro("🗑️ Eliminado", "El contacto ha sido borrado correctamente.");
        } else {
            mostrarErrorEnCentro("Error", "No se pudo eliminar: el contacto no existe.");
        }
    }

    @FXML
    private void modificar() {
        String nombre = solicitarEntrada("Nombre");
        String apellido = solicitarEntrada("Apellido");
        String nuevoTel = solicitarEntrada("Nuevo Teléfono");

        if (miAgenda.modificarTelefono(nombre, apellido, nuevoTel)) {
            mostrarMensajeEnCentro("✏️ Modificado", "Teléfono actualizado con éxito.");
        } else {
            mostrarErrorEnCentro("Error", "No se encontró el contacto.");
        }
    }

    @FXML
    private void agendallena() {
        if (miAgenda.agendaLlena()) {
            mostrarErrorEnCentro("Estado", "La agenda está COMPLETAMENTE LLENA.");
        } else {
            mostrarMensajeEnCentro("Estado", "Todavía tienes espacio disponible.");
        }
    }

    @FXML
    private void espacios() {
        int libres = miAgenda.espaciosLibres();
        mostrarMensajeEnCentro("Espacios", "Te quedan " + libres + " huecos libres.");
    }

    @FXML
    private void existe() {
        String nombre = solicitarEntrada("Nombre");
        String apellido = solicitarEntrada("Apellido");
        Contacto c = new Contacto(nombre, apellido, "000000000");

        if (miAgenda.existeContacto(c)) {
            mostrarMensajeEnCentro("Verificación", "SÍ, el contacto existe.");
        } else {
            mostrarErrorEnCentro("Verificación", "NO, el contacto no está en la lista.");
        }
    }

    // --- MÉTODOS AUXILIARES DE INTERFAZ ---

    private void limpiarCentro() {
        contentArea.getChildren().clear();
    }

    private void mostrarMensajeEnCentro(String titulo, String cuerpo) {
        limpiarCentro();

        Label lblTitulo = new Label(titulo);
        lblTitulo.setFont(Font.font("System", FontWeight.BOLD, 24));
        lblTitulo.setTextFill(Color.web("#2c3e50"));

        Label lblCuerpo = new Label(cuerpo);
        lblCuerpo.setFont(Font.font(16));
        lblCuerpo.setWrapText(true);
        lblCuerpo.setAlignment(Pos.CENTER);

        contentArea.getChildren().addAll(lblTitulo, lblCuerpo);
    }

    private void mostrarErrorEnCentro(String titulo, String mensaje) {
        limpiarCentro();
        Label lblTitulo = new Label("❌ " + titulo);
        lblTitulo.setFont(Font.font("System", FontWeight.BOLD, 20));
        lblTitulo.setTextFill(Color.DARKRED);

        Label lblMensaje = new Label(mensaje);
        lblMensaje.setFont(Font.font(14));

        contentArea.getChildren().addAll(lblTitulo, lblMensaje);
    }

    private String solicitarEntrada(String campo) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Entrada de datos");
        dialog.setHeaderText("Introduce el " + campo + ":");
        return dialog.showAndWait().orElse("");
    }

    @FXML private void Salir() { System.exit(0); }
}