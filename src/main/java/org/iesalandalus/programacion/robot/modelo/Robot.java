package org.iesalandalus.programacion.robot.modelo;

import java.util.Objects;

public class Robot {
    private Coordenada coordenada;
    private Orientacion orientacion;
    private Zona zona;

    public Robot() {
        this(Zona.ZONA_POR_DEFECTO);
    }

    public Robot(Zona zona) {
        this(Objects.requireNonNull(zona, "La zona no puede ser nula."), Orientacion.NORTE);
    }

    public Robot(Zona zona, Orientacion orientacion) {
        this(Objects.requireNonNull(zona, "La zona no puede ser nula."),
                Objects.requireNonNull(orientacion, "La orientación no puede ser nula."),
                zona.getCentro());
    }

    public Robot(Zona zona, Orientacion orientacion, Coordenada coordenada) {
        this.zona = Objects.requireNonNull(zona, "La zona no puede ser nula.");
        this.orientacion = Objects.requireNonNull(orientacion, "La orientación no puede ser nula.");
        this.coordenada = Objects.requireNonNull(coordenada, "La coordenada no puede ser nula.");

        if (!zona.pertenece(coordenada)) {
            throw new IllegalArgumentException("La coordenada no pertenece a la zona.");
        }
    }

    public Robot(Robot robot) {
        this(Objects.requireNonNull(robot, "El robot no puede ser nulo.").zona,
                robot.orientacion,
                robot.coordenada);
    }

    public Zona getZona() {
        return zona;
    }

    public Orientacion getOrientacion() {
        return orientacion;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    private void setZona(Zona zona) {
        this.zona = Objects.requireNonNull(zona, "La zona no puede ser nula.");
        if (!zona.pertenece(this.coordenada)) {
            throw new IllegalArgumentException("La coordenada no pertenece a la zona.");
        }
    }

    private void setOrientacion(Orientacion orientacion) {
        this.orientacion = Objects.requireNonNull(orientacion, "La orientación no puede ser nula.");
    }

    private void setCoordenada(Coordenada coordenada) {
        this.coordenada = Objects.requireNonNull(coordenada, "La coordenada no puede ser nula.");
        if (!zona.pertenece(coordenada)) {
            throw new IllegalArgumentException("La coordenada no pertenece a la zona.");
        }
    }

    public void avanzar() {
        int nuevaX = coordenada.x();
        int nuevaY = coordenada.y();

        // SISTEMA CORREGIDO - Y aumenta hacia SUR, X aumenta hacia ESTE
        switch (orientacion) {
            case NORTE -> nuevaY++;
            case NORESTE -> { nuevaX++; nuevaY++; }
            case ESTE -> nuevaX++;
            case SURESTE -> { nuevaX++; nuevaY--; }
            case SUR -> nuevaY--;
            case SUROESTE -> { nuevaX--; nuevaY--; }
            case OESTE -> nuevaX--;
            case NOROESTE -> { nuevaX--; nuevaY++; }
        }

        Coordenada nuevaCoordenada = new Coordenada(nuevaX, nuevaY);

        if (!zona.pertenece(nuevaCoordenada)) {
            throw new RobotExcepcion("No se puede avanzar, ya que se sale de la zona.");
        }

        this.coordenada = nuevaCoordenada;
    }

    public void girarALaDerecha() {
        orientacion = switch (orientacion) {
            case NORTE -> Orientacion.NORESTE;
            case NORESTE -> Orientacion.ESTE;
            case ESTE -> Orientacion.SURESTE;
            case SURESTE -> Orientacion.SUR;
            case SUR -> Orientacion.SUROESTE;
            case SUROESTE -> Orientacion.OESTE;
            case OESTE -> Orientacion.NOROESTE;
            case NOROESTE -> Orientacion.NORTE;
        };
    }

    public void girarALaIzquierda() {
        orientacion = switch (orientacion) {
            case NORTE -> Orientacion.NOROESTE;
            case NOROESTE -> Orientacion.OESTE;
            case OESTE -> Orientacion.SUROESTE;
            case SUROESTE -> Orientacion.SUR;
            case SUR -> Orientacion.SURESTE;
            case SURESTE -> Orientacion.ESTE;
            case ESTE -> Orientacion.NORESTE;
            case NORESTE -> Orientacion.NORTE;
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Robot robot = (Robot) obj;
        return Objects.equals(coordenada, robot.coordenada) &&
                orientacion == robot.orientacion &&
                Objects.equals(zona, robot.zona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordenada, orientacion, zona);
    }

    @Override
    public String toString() {
        return String.format("Robot [coordenada=%s, orientacion=%s, zona=%s]", coordenada, orientacion, zona);
    }
}
