package org.example.holajfx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EnemigoVolador extends Enemigo {

    private int velocidadX = 1;
    private int velocidadY = 1;

    public EnemigoVolador(int x, int y) {
        super(x, y, 50, 50);
    }

    @Override
    public void dibujar(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillRect(getX(), getY(), getAncho(), getAlto());
    }

    @Override
    public void mover() {
        setX(getX() + velocidadX);
        setY(getY() + velocidadY);

        // Limitar el movimiento dentro de la pantalla
        if (getX() < 0) {
            setX(0);
            velocidadX = -velocidadX;
        }
        if (getX() + getAncho() > 800) {
            setX(800 - getAncho());
            velocidadX = -velocidadX;
        }
        if (getY() < 0) {
            setY(0);
            velocidadY = -velocidadY;
        }
        if (getY() + getAlto() > 600) {
            setY(600 - getAlto());
            velocidadY = -velocidadY;
        }
    }
}
