package org.example.holajfx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Enemigo {
    private int x;
    private int y;
    private int ancho;
    private int alto;

    public Enemigo(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void dibujar(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillRect(x, y, ancho, alto);
    }

    public abstract void mover();

}
