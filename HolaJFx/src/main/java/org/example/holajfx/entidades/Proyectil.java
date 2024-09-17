package org.example.holajfx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Proyectil {
    private double x;
    private double y;
    private double ancho;
    private double alto;
    private double velocidad;

    public Proyectil(double x, double y){
        this.x = x;
        this.y = y;
        this.ancho = 10;
        this.alto = 5;
        this.velocidad = 10;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAncho() {
        return ancho;
    }

    public double getAlto() {
        return alto;
    }

    public void mover(){
        this.x+= this.velocidad;
    }

    public void dibujar(GraphicsContext gc){
        gc.setFill(Color.RED);
        gc.fillRect(x,y,ancho,alto);
    }

    public boolean colisionar(Enemigo enemigo){
        boolean result = (this.x < enemigo.getX() + enemigo.getAncho() &&
                this.x + this.ancho > enemigo.getX() &&
                this.y < enemigo.getY() + enemigo.getAlto() &&
                this.y + this.alto > enemigo.getY());

        return result;
    }
}
