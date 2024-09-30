package org.example.holajfx.entidades;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Proyectil {

    protected double x, y;
    protected double velocidad;
    protected Color color;

    public Proyectil(double x, double y, double velocidad, Color color) {
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.color = color;
    }

    public abstract void mover();
    public abstract void dibujar(GraphicsContext gc);

    public boolean colisionaCon(Jugador enemigo) {
        double enemigoX = enemigo.getX();
        double enemigoY = enemigo.getY();
        double enemigoAncho = 30; // Asume que el ancho del enemigo es 30
        double enemigoAlto = 30;  // Asume que el alto del enemigo es 30

        return (x < enemigoX + enemigoAncho &&
                x + 5 > enemigoX &&
                y < enemigoY + enemigoAlto &&
                y + 10 > enemigoY);
    }
    public boolean colisionaCon(Enemigo enemigo) {
        double enemigoX = enemigo.getX();
        double enemigoY = enemigo.getY();
        double enemigoAncho = 30; // Asume que el ancho del enemigo es 30
        double enemigoAlto = 30;  // Asume que el alto del enemigo es 30

        return (x < enemigoX + enemigoAncho &&
                x + 5 > enemigoX &&
                y < enemigoY + enemigoAlto &&
                y + 10 > enemigoY);
    }

    // Getters y setters para x, y, velocidad y color
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public double getVelocidad() { return velocidad; }
    public void setVelocidad(double velocidad) { this.velocidad = velocidad; }
    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
}