package com.example.hackathon2fxjava.model;
import java.util.Arrays;
import java.util.Comparator;
//  Logica de la lista, añadir, buscar, borrar...
public class Agenda {
    private Contacto[] contactos;
    public Agenda(int capacidad) {
        contactos = new Contacto[capacidad];
    }

    public Agenda() {
        this(10);
    }

    public void añadirContacto(Contacto c) {

        if (c == null) {
            throw new IllegalArgumentException("El contacto no puede ser nulo.");
        }

        if (existeContacto(c)) {
            throw new IllegalStateException("El contacto ya existe.");
        }

        for (int i = 0; i < contactos.length; i++) {
            if (contactos[i] == null) {
                contactos[i] = c;
                return;
            }
        }

        throw new IllegalStateException("La agenda está llena.");
    }

    private boolean existeContacto(Contacto c) {
        for (Contacto actual : contactos) {
            if (actual != null && actual.equals(c)) {
                return true;
            }
        }
        return false;
    }

    public Contacto[] obtenerContactosOrdenados() {
        return Arrays.stream(contactos)
                .filter(c -> c != null)
                .sorted(Comparator
                        .comparing(Contacto::getNombre)
                        .thenComparing(Contacto::getApellido))
                .toArray(Contacto[]::new);
    }
}

