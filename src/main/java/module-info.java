module com.example.hackathon2fxjava {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.hackathon2fxjava to javafx.fxml;
    exports com.example.hackathon2fxjava;
}