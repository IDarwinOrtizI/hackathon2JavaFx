package com.example.hackathon2fxjava.util;

public class Validador {

    public static boolean validarTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return false;
        }
        texto = texto.trim();
        if (texto.length() > 90) {
            return false;
        }
        for (int i = 0; i < texto.length(); i++) {
            if (Character.isDigit(texto.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean validarTelefono(String telefono){
        return telefono.matches("\\d{7,10}");
    }
}
