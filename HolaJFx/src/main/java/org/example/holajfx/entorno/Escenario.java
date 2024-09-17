package org.example.holajfx;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Escenario {
    private int ancho;
    private int alto;
    private List<Enemigo> enemigos;
    private Personaje personaje;

    public Escenario(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.enemigos = new ArrayList<>();
    }

    public void agregarEnemigo(Enemigo enemigo) {
        enemigos.add(enemigo);
    }

    public List<Enemigo> getEnemigos() {
        return enemigos;
    }

    public void agregarPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public void dibujar(GraphicsContext gc) {
        if (personaje != null) {
            personaje.dibujar(gc);
        }
        for (Enemigo enemigo : enemigos) {
            enemigo.dibujar(gc);
        }
    }

    public void limpiar() {
        if (personaje != null) {
            personaje.setX(400);
            personaje.setY(300);
        }
        enemigos.clear();
    }
}

