module com.example.hackathon2fxjava {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    // Permite que JavaFX acceda al controlador para manejar los botones
    opens com.example.hackathon2fxjava.controller to javafx.fxml;
    // Permite que JavaFX lea los datos de tus contactos (necesario para TableView)
    opens com.example.hackathon2fxjava.model to javafx.base;
    opens com.example.hackathon2fxjava to javafx.fxml;
    exports com.example.hackathon2fxjava;
}