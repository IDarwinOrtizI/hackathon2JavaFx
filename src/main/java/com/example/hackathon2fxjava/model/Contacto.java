package com.example.hackathon2fxjava.model;
//  Atributos nombre, apellido, tel.

import com.example.hackathon2fxjava.util.Validador;
import java.util.Objects;

public class Contacto {
    private String nombre;
    private String apellido;
    private String telefono;

    public Contacto(String nombre, String apellido, String telefono) {

        if (!Validador.validarTexto(nombre)) {
            throw new IllegalArgumentException("¡Oops! El campo 'nombre' no puede estar vacío.");
        }

        if (!Validador.validarTexto(apellido)) {
            throw new IllegalArgumentException("¡Oops! El campo 'apellido' no puede estar vacío.");
        }

        if (!Validador.validarTelefono(telefono)) {
            throw new IllegalArgumentException("¡Oops! Este teléfono no es válido.");
        }

        this.nombre = nombre.trim();
        this.apellido = apellido.trim();
        this.telefono = telefono;
    }

    // Aquí usamos los getters.
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (!Validador.validarTelefono(telefono)) {
            throw new IllegalArgumentException("¡Oops! Este teléfono no es válido.");
        }
        this.telefono = telefono;
    }

    //Inicio override, cuidado.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contacto)) return false;

        Contacto contacto = (Contacto) o;

        return nombre.equalsIgnoreCase(contacto.nombre)
                && apellido.equalsIgnoreCase(contacto.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                nombre.toLowerCase(),
                apellido.toLowerCase()
        );
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " - " + telefono;
    }
}

