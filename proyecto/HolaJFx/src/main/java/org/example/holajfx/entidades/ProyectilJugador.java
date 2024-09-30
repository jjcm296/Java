package org.example.holajfx.entidades;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ProyectilJugador extends Proyectil {

    public ProyectilJugador(double x, double y, double velocidad, Color color) {
        super(x, y, velocidad, color);
    }

    @Override
    public void mover() {
        y -= velocidad;
    }

    @Override
    public void dibujar(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, 5, 10);
    }
}
