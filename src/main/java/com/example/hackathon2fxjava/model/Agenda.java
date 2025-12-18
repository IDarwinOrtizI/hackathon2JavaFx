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

    //        añadirContacto(Contacto c):

    public void añadirContacto(Contacto c) {

        if (c == null) {
            throw new IllegalArgumentException("El contacto no puede ser nulo");
        }

        if (c.getNombre().trim().isEmpty() || c.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre y apellido no pueden estar vacíos");
        }

        if (existeContacto(c)) {
            throw new IllegalStateException("El contacto ya existe");
        }

        for (int i = 0; i < contactos.length; i++) {
            if (contactos[i] == null) {
                contactos[i] = c;
                return;
            }
        }

        throw new IllegalStateException("La agenda está llena");
    }
//            existeContacto(Conctacto c):

        public boolean existeContacto(Contacto c) {
            for (Contacto actual : contactos) {
                if (actual != null &&
                        actual.getNombre().equalsIgnoreCase(c.getNombre()) &&
                        actual.getApellido().equalsIgnoreCase(c.getApellido())) {
                    return true;
                }
            }
            return false;
        }

//            listarContactos():


        public String[] listarContactos() {
            return Arrays.stream(contactos)
                    .filter(c -> c != null)
                    .sorted(Comparator
                            .comparing(Contacto::getNombre)
                            .thenComparing(Contacto::getApellido))
                    .map(c -> c.getNombre() + " " + c.getApellido() + " - " + c.getTelefono())
                    .toArray(String[]::new);
        }

//        buscaContacto(String nombre):

        public Contacto buscaContacto(String nombre, String apellido) {
            for (Contacto c : contactos) {
                if (c != null &&
                        c.getNombre().equalsIgnoreCase(nombre) &&
                        c.getApellido().equalsIgnoreCase(apellido)) {
                    return c;
                }
            }
            return null;
        }

//        eliminarContacto(Contacto c):


        public boolean eliminarContacto(Contacto c) {
            for (int i = 0; i < contactos.length; i++) {
                if (contactos[i] != null &&
                        contactos[i].getNombre().equalsIgnoreCase(c.getNombre()) &&
                        contactos[i].getApellido().equalsIgnoreCase(c.getApellido())) {
                    contactos[i] = null;
                    return true;
                }
            }
            return false;
        }

//        modificarTelefono
        public boolean modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
            Contacto c = buscaContacto(nombre, apellido);
            if (c != null) {
                c.setTelefono(nuevoTelefono);
                return true;
            }
            return false;
        }
    }