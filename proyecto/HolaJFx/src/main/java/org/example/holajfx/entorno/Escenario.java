package org.example.holajfx.entorno;

import org.example.holajfx.entidades.Enemigo;

import javafx.scene.canvas.GraphicsContext;
import java.util.HashSet;
import java.util.Set;

public class Escenario {
    private Set<Enemigo> enemigos;

    public Escenario() {
        enemigos = new HashSet<>();
    }

    public void agregarEnemigo(Enemigo enemigo) {
        enemigos.add(enemigo);
    }

    public Set<Enemigo> getEnemigos() {
        return enemigos;
    }

    public void dibujar(GraphicsContext gc) {
        enemigos.forEach(enemigo -> enemigo.dibujar(gc));
    }
}
