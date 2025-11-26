package org.iesalandalus.programacion.robot.modelo;

import java.util.Objects;

public record Zona (int ancho, int alto) {
    public static final int ANCHO_MINIMO = 10;
    public static final int ANCHO_MAXIMO = 100;
    public static final int ALTO_MINIMO = 10;
    public static final int ALTO_MAXIMO = 100;
    public static final Zona ZONA_POR_DEFECTO = new Zona(10, 10);

    // Constructores
    public Zona {
        validarAncho(ancho);
        validarAlto(alto);
    }

    public Zona() {
        // Constructor por defecto - zona mínima
        this(ANCHO_MINIMO, ALTO_MINIMO);
    }


    //metodos de validación
    private void validarAlto(int alto) {
        if (alto < ALTO_MINIMO || alto > ALTO_MAXIMO) {
            throw new IllegalArgumentException("Alto no válido.");
        }
    }

    private void validarAncho(int ancho) {
        if (ancho < ANCHO_MINIMO || ancho > ANCHO_MAXIMO) {
            throw new IllegalArgumentException("Ancho no válido.");
        }
    }

    // El centro
    public Coordenada getCentro() {
        return new Coordenada(ancho / 2, alto / 2);
    }

    public boolean pertenece(Coordenada coordenada) {
        Objects.requireNonNull(coordenada, "La coordenada no puede ser nula.");
        return perteneceX(coordenada.x()) && perteneceY(coordenada.y());
    }

    private boolean perteneceX(int x) {
        return x >= 0 && x < ancho;
    }

    private boolean perteneceY(int y) {
        return y >= 0 && y < alto;
    }

}
