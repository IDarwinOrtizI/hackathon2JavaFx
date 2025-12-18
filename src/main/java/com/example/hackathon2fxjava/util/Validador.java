package com.example.hackathon2fxjava.util;

public class Validador {

    public static boolean validarNombre(String texto){
        return texto != null && !texto.trim().isEmpty();
    }
    public static boolean validarTelefono(String telefono){
        return telefono.matches("\\d{7,10}");
    }
}
