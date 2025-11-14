package org.iesalandalus.programacion.robot.modelo;

public enum Orientacion {

    NORTE("Norte"),
    NORESTE("Noreste"),
    ESTE("Este"),
    SURESTE("Sureste"),
    SUR("Sur"),
    SUROESTE("Suroeste"),
    OESTE("Oeste"),
    NOROESTE("Noroeste");

    private String mostrarOrientacion;

    private Orientacion(String mostrarOrientacion) {
        this.mostrarOrientacion = mostrarOrientacion;
    }

    @Override
    public String toString() {
        return mostrarOrientacion;
    }

}
