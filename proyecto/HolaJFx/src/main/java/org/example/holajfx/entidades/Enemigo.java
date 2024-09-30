package org.example.holajfx.entidades;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Enemigo implements Movible{
    protected double x, y;
    protected Color color;
    protected double velocidad;
    protected boolean movimiento;
    protected int nivel;

    public Enemigo(double x, double y, int nivel) {
        this.x = x;
        this.y = y;
        this.velocidad = 2; // Velocidad de movimiento
        this.movimiento = true; // Comienza moviéndose hacia la derecha
        this.nivel = nivel; // Asignar nivel
    }

    public abstract void dibujar(GraphicsContext gc);

    public void mover() {
        if (movimiento) {
            x += velocidad;
            if (x >= 800 - 30) { // Si alcanza el borde derecho
                movimiento = false; // Cambiar dirección
            }
        } else {
            x -= velocidad;
            if (x <= 0) { // Si alcanza el borde izquierdo
                movimiento = true; // Cambiar dirección
            }
        }
    }

    public boolean colisionaCon(Proyectil proyectil) {
        double proyectilX = proyectil.getX();
        double proyectilY = proyectil.getY();
        double proyectilAncho = 5;  // Asume que el ancho del proyectil es 5
        double proyectilAlto = 10;  // Asume que el alto del proyectil es 10

        return (x < proyectilX + proyectilAncho &&
                x + 30 > proyectilX &&
                y < proyectilY + proyectilAlto &&
                y + 30 > proyectilY);
    }

    public int getNivel() {
        return nivel;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public boolean isMovimiento() {
        return movimiento;
    }

    public void setMovimiento(boolean movimiento) {
        this.movimiento = movimiento;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}