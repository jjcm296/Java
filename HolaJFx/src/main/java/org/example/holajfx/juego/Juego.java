package org.example.holajfx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Juego extends Application {
    private Personaje jugador;
    private Escenario escenario;
    private AnimationTimer gameLoop;
    private boolean moverIzquierda;
    private boolean moverDerecha;
    private boolean moverArriba;
    private boolean moverAbajo;

    private List<Proyectil> proyectiles;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        inicializarJuego();

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> manejarEntrada(event));
        scene.setOnKeyReleased(event -> manejarEntradaLiberada(event));

        primaryStage.setScene(scene);
        primaryStage.setTitle("Videojuego 2D JavaFX");
        primaryStage.show();

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                actualizarJuego(gc);
            }
        };
        gameLoop.start();
    }

    private void inicializarJuego() {
        jugador = new Personaje(400, 300, 50, 50);
        escenario = new Escenario(800, 600);
        escenario.agregarPersonaje(jugador);
        proyectiles = new ArrayList<>();

        // Agregar enemigos voladores
        EnemigoVolador enemigo1 = new EnemigoVolador(0, 0);
        EnemigoVolador enemigo2 = new EnemigoVolador(750, 0);
        EnemigoVolador enemigo3 = new EnemigoVolador(0, 550);
        EnemigoVolador enemigo4 = new EnemigoVolador(750, 560);
        EnemigoVolador enemigo5 = new EnemigoVolador(100, 600);
        EnemigoVolador enemigo6 = new EnemigoVolador(600, 100);

        escenario.agregarEnemigo(enemigo1);
        escenario.agregarEnemigo(enemigo2);
        escenario.agregarEnemigo(enemigo3);
        escenario.agregarEnemigo(enemigo4);
        escenario.agregarEnemigo(enemigo5);
        escenario.agregarEnemigo(enemigo6);
    }

    private void manejarEntrada(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            moverIzquierda = true;
        }
        if (event.getCode() == KeyCode.RIGHT) {
            moverDerecha = true;
        }
        if (event.getCode() == KeyCode.UP) {
            moverArriba = true;
        }
        if (event.getCode() == KeyCode.DOWN) {
            moverAbajo = true;
        }
        if (event.getCode() == KeyCode.SPACE) {
            dispararProyectil();
        }
    }

    private void manejarEntradaLiberada(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            moverIzquierda = false;
        }
        if (event.getCode() == KeyCode.RIGHT) {
            moverDerecha = false;
        }
        if (event.getCode() == KeyCode.UP) {
            moverArriba = false;
        }
        if (event.getCode() == KeyCode.DOWN) {
            moverAbajo = false;
        }
    }

    private void dispararProyectil() {
        Proyectil proyectil = new Proyectil(jugador.getX() + jugador.getAncho(), jugador.getY() + jugador.getAlto() / 2);
        proyectiles.add(proyectil);
        System.out.println("Proyectil disparado en: " + proyectil.getX() + ", " + proyectil.getY());
    }

    private void actualizarJuego(GraphicsContext gc) {
        gc.clearRect(0, 0, 800, 600);

        // Mover al personaje según las teclas presionadas
        double velocidad = 5;
        if (moverIzquierda) {
            jugador.mover(-velocidad, 0);
        }
        if (moverDerecha) {
            jugador.mover(velocidad, 0);
        }
        if (moverArriba) {
            jugador.mover(0, -velocidad);
        }
        if (moverAbajo) {
            jugador.mover(0, velocidad);
        }

        ajustarLimitesPantalla(jugador);
        jugador.dibujar(gc);

        Iterator<Proyectil> iterProyectiles = proyectiles.iterator();
        while (iterProyectiles.hasNext()) {
            Proyectil proyectil = iterProyectiles.next();
            proyectil.mover();
            proyectil.dibujar(gc);

            // Eliminar proyectiles que salgan de la pantalla
            if (proyectil.getX() > 800) {
                iterProyectiles.remove();
            }

            // Verificar colisiones con enemigos
            Iterator<Enemigo> iterEnemigos = escenario.getEnemigos().iterator();
            while (iterEnemigos.hasNext()) {
                Enemigo enemigo = iterEnemigos.next();
                if (proyectil.colisionar(enemigo)) {
                    iterEnemigos.remove();
                    iterProyectiles.remove();
                    break;
                }
            }
        }

        // Mover y dibujar los enemigos
        for (Enemigo enemigo : escenario.getEnemigos()) {
            if (enemigo instanceof EnemigoVolador) {
                ((EnemigoVolador) enemigo).mover();  // Llamar mover() si es EnemigoVolador
            }
            enemigo.dibujar(gc);

            if (jugador.colisionaCon(enemigo)) {
                mostrarGameOver();
            }
        }

        // Victoria
        if (escenario.getEnemigos().isEmpty()){
            mostrarVictoria();
        }
    }

    private void mostrarVictoria() {
        gameLoop.stop();  // Detener el bucle de animación

        // Mostrar el diálogo de "Victoria" en el hilo de la aplicación
        javafx.application.Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("¡Victoria!");
            alert.setHeaderText(null);
            alert.setContentText("¡Has ganado!");
            alert.showAndWait();

            reiniciarJuego();
            gameLoop.start();
        });
    }

    private void ajustarLimitesPantalla(Personaje personaje) {
        // Limitar el movimiento del personaje dentro de la pantalla
        if (personaje.getX() < 0) {
            personaje.mover(-personaje.getX(), 0);
        }
        if (personaje.getX() + personaje.getAncho() > 800) {
            personaje.mover(800 - (personaje.getX() + personaje.getAncho()), 0);
        }
        if (personaje.getY() < 0) {
            personaje.mover(0, -personaje.getY());
        }
        if (personaje.getY() + personaje.getAlto() > 600) {
            personaje.mover(0, 600 - (personaje.getY() + personaje.getAlto()));
        }
    }

    private void mostrarGameOver() {
        gameLoop.stop();  // Detener el bucle de animación

        // Mostrar el diálogo de "Game Over" en el hilo de la aplicación
        javafx.application.Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("¡Game Over!");
            alert.showAndWait();

            reiniciarJuego();
            gameLoop.start();
        });
    }

    private void reiniciarJuego() {
        escenario.limpiar();
        inicializarJuego();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
