package org.example.holajfx.entidades;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Set;
import java.util.HashSet;

public class EnemigoVolador extends Enemigo {

    private static final double VELOCIDAD_PROYECTIL_ENEMIGO = 5;
    private static final Color COLOR_PROYECTIL_ENEMIGO = Color.GREEN;

    public EnemigoVolador(double x, double y, int nivel) {
        super(x, y, nivel);
        this.color = Color.GREEN;
    }

    @Override
    public void dibujar(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, 30, 30); // Ajusta el tamaño según sea necesario
    }

    public void disparar(Set<Proyectil> proyectiles) {
        // Aquí puedes ajustar los valores según necesites
        double VELOCIDAD_PROYECTIL_ENEMIGO = 5.0;
        Color COLOR_PROYECTIL_ENEMIGO = Color.RED;

        Proyectil proyectil = new ProyectilEnemigo(this.getX() + 15, this.getY() + 30, VELOCIDAD_PROYECTIL_ENEMIGO, COLOR_PROYECTIL_ENEMIGO);
        proyectiles.add(proyectil);
    }
}
