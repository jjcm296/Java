package org.example.holajfx.entidades;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ProyectilEnemigo extends Proyectil {
    public ProyectilEnemigo(double x, double y, double velocidad, Color color) {
        super(x, y, velocidad, color);
    }

    @Override
    public void mover() {
        y += velocidad; // Mueve el proyectil hacia abajo
    }

    @Override
    public void dibujar(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, 5, 10); // Ajusta el tamaño según tus necesidades
    }
}
