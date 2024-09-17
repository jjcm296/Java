package org.example.holajfx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Personaje {
    private int x;
    private int y;
    private int ancho;
    private int alto;

    public Personaje(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    // Método para dibujar el personaje
    public void dibujar(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(x, y, ancho, alto);
    }

    // Método para mover el personaje
    public void mover(double dx, double dy) {
        x += dx;
        y += dy;
    }

    // Método para verificar colisiones
    public boolean colisionaCon(Enemigo enemigo) {
        return x < enemigo.getX() + enemigo.getAncho() &&
                x + ancho > enemigo.getX() &&
                y < enemigo.getY() + enemigo.getAlto() &&
                y + alto > enemigo.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
