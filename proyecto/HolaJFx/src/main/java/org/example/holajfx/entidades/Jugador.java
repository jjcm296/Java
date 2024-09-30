package org.example.holajfx.entidades;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Jugador {
    private double x;
    private double y;
    private static final double ANCHO = 40;
    private static final double ALTO = 40;
    private Color color;
    private static final double LIMITE_IZQUIERDO = 0;
    private static final double LIMITE_DERECHO = 800 - ANCHO;
    private static final double LIMITE_SUPERIOR = 0;
    private static final double LIMITE_INFERIOR = 600 - ALTO;

    public Jugador(double x, double y) {
        this.x = x;
        this.y = y;
        this.color = Color.BLUE;
    }

    public void dibujar(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, ANCHO, ALTO);
    }

    public void moverIzquierda(double velocidad) {
        x -= velocidad;
        if (x < LIMITE_IZQUIERDO) {
            x = LIMITE_IZQUIERDO; // Limitar al borde izquierdo
        }
    }

    public void moverDerecha(double velocidad) {
        x += velocidad;
        if (x > LIMITE_DERECHO) {
            x = LIMITE_DERECHO; // Limitar al borde derecho
        }
    }

    public void moverArriba(double velocidad) {
        y -= velocidad;
        // Puedes agregar límite superior si es necesario
    }

    public void moverAbajo(double velocidad) {
        y += velocidad;
        // Puedes agregar límite inferior si es necesario
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getAncho() {
        return ANCHO;
    }

    public double getAlto() {
        return ALTO;
    }
}
