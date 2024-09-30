package org.example.holajfx.juego;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.holajfx.entidades.*;
import org.example.holajfx.entorno.Escenario;

import javafx.scene.paint.Color;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Iterator;

public class Juego extends Application {

    private static final double VENTANA_ANCHO = 800;
    private static final double VENTANA_ALTO = 600;
    private static final double VELOCIDAD_JUGADOR = 5;
    private static final double VELOCIDAD_PROYECTIL = 10;
    private static final Color COLOR_PERSONAJE = Color.BLUE;
    private static final Color COLOR_PROYECTIL_JUGADOR = Color.RED;
    private static final Color COLOR_PROYECTIL_ENEMIGO = Color.GREEN;
    private static final int VIDA_INICIAL = 3; // Vida inicial del jugador

    private Jugador jugador;
    private Set<Proyectil> proyectiles;
    private Escenario escenario;
    private Set<Direccion> direccionesMovimiento;
    private int vidas;

    private boolean victoriaMostrada = true;
    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(VENTANA_ANCHO, VENTANA_ALTO);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        inicializarJuego();

        Pane root = new Pane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        configurarControles(scene);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Juego 2D con JavaFX");
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (vidas > 0) {
                    actualizarJuego();
                    dibujarJuego(gc);
                } else {
                    mostrarGameOver(gc);
                }
            }
        };
        timer.start();
    }

    private void inicializarJuego() {
        jugador = new Jugador(VENTANA_ANCHO / 2, VENTANA_ALTO - 50);
        proyectiles = new HashSet<>();
        escenario = new Escenario();
        direccionesMovimiento = new HashSet<>();
        vidas = VIDA_INICIAL;
        inicializarEnemigosVoladores();
    }

    private void reiniciarJuego() {
        victoriaMostrada = false;
        vidas = VIDA_INICIAL;
        inicializarJuego();
    }

    private void inicializarEnemigosVoladores() {
        double margenSuperior = 50;
        double anchoVentana = VENTANA_ANCHO;

        double rangoY = 100; // Rango vertical para las posiciones y de los enemigos

        EnemigoVolador[] enemigos = {
                new EnemigoVolador(0, margenSuperior + Math.random() * rangoY, 1),
                new EnemigoVolador(anchoVentana / 4, margenSuperior + Math.random() * rangoY, 2),
                new EnemigoVolador(anchoVentana / 2, margenSuperior + Math.random() * rangoY, 3),
                new EnemigoVolador(3 * anchoVentana / 4, margenSuperior + Math.random() * rangoY, 4),
                new EnemigoVolador(anchoVentana - 30, margenSuperior + Math.random() * rangoY, 5) // Ajusta la posición para que no se salga del borde
        };

        for (EnemigoVolador enemigo : enemigos) {
            escenario.agregarEnemigo(enemigo);
        }
    }

    private void configurarControles(Scene scene) {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                dispararProyectilJugador();
            } else {
                manejarEntrada(e.getCode());
            }
        });

        scene.setOnKeyReleased(e -> manejarEntradaLiberada(e.getCode()));
    }

    private void manejarEntrada(KeyCode code) {
        switch (code) {
            case LEFT -> direccionesMovimiento.add(Direccion.IZQUIERDA);
            case RIGHT -> direccionesMovimiento.add(Direccion.DERECHA);
            case UP -> direccionesMovimiento.add(Direccion.ARRIBA);
            case DOWN -> direccionesMovimiento.add(Direccion.ABAJO);
        }
    }

    private void manejarEntradaLiberada(KeyCode code) {
        switch (code) {
            case LEFT -> direccionesMovimiento.remove(Direccion.IZQUIERDA);
            case RIGHT -> direccionesMovimiento.remove(Direccion.DERECHA);
            case UP -> direccionesMovimiento.remove(Direccion.ARRIBA);
            case DOWN -> direccionesMovimiento.remove(Direccion.ABAJO);
        }
    }

    private void dispararProyectilJugador() {
        Proyectil proyectil = new ProyectilJugador(jugador.getX() + jugador.getAncho() / 2, jugador.getY(), VELOCIDAD_PROYECTIL, COLOR_PROYECTIL_JUGADOR);
        proyectiles.add(proyectil);
    }

    private void dispararProyectilEnemigo(Enemigo enemigo) {
        Proyectil proyectil = new ProyectilEnemigo(
                enemigo.getX() + 15, // Ajustar posición del proyectil
                enemigo.getY() + 30,
                VELOCIDAD_PROYECTIL,
                COLOR_PROYECTIL_ENEMIGO
        );
        proyectiles.add(proyectil);
    }

    private void actualizarJuego() {
        moverPersonaje();
        moverProyectiles();
        escenario.getEnemigos().forEach(Enemigo::mover);
        verificarColisiones();

        // Disparo de proyectiles enemigos
        escenario.getEnemigos().forEach(enemigo -> {
            if (Math.random() < 0.01) { // Probabilidad de disparo
                dispararProyectilEnemigo(enemigo);
            }
        });
    }

    private void dibujarJuego(GraphicsContext gc) {
        gc.clearRect(0, 0, VENTANA_ANCHO, VENTANA_ALTO);
        jugador.dibujar(gc);
        proyectiles.forEach(proyectil -> proyectil.dibujar(gc));
        escenario.dibujar(gc);

        // Mostrar vida en pantalla
        gc.setFill(Color.WHITE);
        gc.fillText("Vida: " + vidas, 10, 20);
    }

    private void mostrarGameOver(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillText("GAME OVER", VENTANA_ANCHO / 2 - 50, VENTANA_ALTO / 2);
        // Puedes agregar más detalles aquí, como reiniciar el juego con una tecla o similar.
    }

    private void moverPersonaje() {
        if (direccionesMovimiento.contains(Direccion.IZQUIERDA)) {
            jugador.moverIzquierda(VELOCIDAD_JUGADOR);
        }
        if (direccionesMovimiento.contains(Direccion.DERECHA)) {
            jugador.moverDerecha(VELOCIDAD_JUGADOR);
        }
        if (direccionesMovimiento.contains(Direccion.ARRIBA)) {
            jugador.moverArriba(VELOCIDAD_JUGADOR);
        }
        if (direccionesMovimiento.contains(Direccion.ABAJO)) {
            jugador.moverAbajo(VELOCIDAD_JUGADOR);
        }

        // Restringir el movimiento del jugador dentro de la ventana
        if (jugador.getX() < 0) jugador.setX(0);
        if (jugador.getX() > VENTANA_ANCHO - jugador.getAncho()) jugador.setX(VENTANA_ANCHO - jugador.getAncho());
        if (jugador.getY() < 0) jugador.setY(0);
        if (jugador.getY() > VENTANA_ALTO - jugador.getAlto()) jugador.setY(VENTANA_ALTO - jugador.getAlto());
    }

    private void moverProyectiles() {
        Iterator<Proyectil> iterProyectiles = proyectiles.iterator();
        while (iterProyectiles.hasNext()) {
            Proyectil proyectil = iterProyectiles.next();
            proyectil.mover();
            if (proyectil.getY() < 0 || proyectil.getY() > VENTANA_ALTO) {
                iterProyectiles.remove(); // Eliminar proyectiles fuera de la pantalla
            }
        }
    }

    private void verificarColisiones() {
        Iterator<Proyectil> iterProyectiles = proyectiles.iterator();
        while (iterProyectiles.hasNext()) {
            Proyectil proyectil = iterProyectiles.next();

            // Comprobar si el proyectil es un proyectil enemigo y colisiona con el jugador
            if (proyectil instanceof ProyectilEnemigo && proyectil.colisionaCon(jugador)) {
                // El proyectil enemigo impacta al jugador
                iterProyectiles.remove(); // Eliminar el proyectil de la lista
                vidas--; // Reducir vida del jugador

                // Comprobar si el jugador ha perdido todas las vidas
                if (vidas <= 0) {
                    mostrarGameOver(gc); // Mostrar mensaje de Game Over
                    return; // Terminar el juego si las vidas son 0
                }
            } else {
                // Verificar colisión del proyectil con enemigos
                verificarColisionConEnemigos(proyectil, iterProyectiles);
            }
        }

        // Verificar si el jugador ha ganado
        verificarVictoria();
    }

    private void verificarColisionConEnemigos(Proyectil proyectil, Iterator<Proyectil> iterProyectiles) {
        Iterator<Enemigo> iterEnemigos = escenario.getEnemigos().iterator();
        while (iterEnemigos.hasNext()) {
            Enemigo enemigo = iterEnemigos.next();
            if (proyectil.colisionaCon(enemigo)) {
                iterEnemigos.remove(); // Eliminar enemigo si es impactado
                iterProyectiles.remove(); // Eliminar proyectil
                break; // Salir del bucle al detectar una colisión
            }
        }
    }

    private void verificarVictoria() {
        Iterator<Enemigo> iterEnemigos = escenario.getEnemigos().iterator();
        while (iterEnemigos.hasNext()) {
            Enemigo enemigo = iterEnemigos.next();
            if (proyectil.colisionaCon(enemigo)) {
                iterEnemigos.remove(); // Eliminar enemigo si es impactado
                iterProyectiles.remove(); // Eliminar proyectil
                break; // Salir del bucle al detectar una colisión
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}