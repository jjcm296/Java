package org.example.banco;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;


public class BancoApp extends Application {
    private Cliente cliente;
    private Cuenta cuentaSeleccionada;

    @Override
    public void start(Stage primaryStage) {
        mostrarVentanaRegistro(primaryStage);
    }

    private void mostrarVentanaRegistro(Stage primaryStage) {
        primaryStage.setTitle("Registro de Cliente");

        // Crear layout para la ventana de registro
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Campos de texto para ingresar datos del cliente
        TextField nombreInput = new TextField();
        nombreInput.setPromptText("Nombre");
        GridPane.setConstraints(nombreInput, 0, 0);

        TextField apellidoInput = new TextField();
        apellidoInput.setPromptText("Apellido");
        GridPane.setConstraints(apellidoInput, 1, 0);

        TextField ineInput = new TextField();
        ineInput.setPromptText("INE");
        GridPane.setConstraints(ineInput, 0, 1);

        TextField edadInput = new TextField();
        edadInput.setPromptText("Edad");
        GridPane.setConstraints(edadInput, 1, 1);

        // Opción para seleccionar tipo de cuenta
        ComboBox<String> tipoCuentaCombo = new ComboBox<>();
        tipoCuentaCombo.getItems().addAll("Cuenta Débito", "Cuenta Crédito");
        tipoCuentaCombo.setPromptText("Selecciona tipo de cuenta");
        GridPane.setConstraints(tipoCuentaCombo, 0, 2);

        // Botón para registrar cliente
        Button registrarBtn = new Button("Registrar Cliente");
        GridPane.setConstraints(registrarBtn, 1, 2);

        registrarBtn.setOnAction(e -> {
            try {
                // Validar edad
                int edad = Integer.parseInt(edadInput.getText());
                if (edad < 18) {
                    mostrarAlerta("Edad no válida", "El cliente debe tener al menos 18 años para registrarse.");
                    return;
                }

                // Crear cliente
                String nombre = nombreInput.getText();
                String apellido = apellidoInput.getText();
                String ine = ineInput.getText();

                cliente = new Cliente(nombre, apellido, edad, ine, new ArrayList<>());

                // Crear cuenta de acuerdo al tipo seleccionado
                String tipoCuenta = tipoCuentaCombo.getValue();
                if (tipoCuenta != null) {
                    if (tipoCuenta.equals("Cuenta Débito")) {
                        cuentaSeleccionada = new CuentaDebito("001", 0);
                        mostrarVentanaCuentaDebito(primaryStage);
                    } else if (tipoCuenta.equals("Cuenta Crédito")) {
                        cuentaSeleccionada = new CuentaCredito("002", 0, 5000); // límite de crédito 5000
                        mostrarVentanaCuentaCredito(primaryStage);
                    }
                    cliente.agregarCuenta(cuentaSeleccionada);
                    System.out.println("Cliente registrado: " + cliente.getNombre() + " " + cliente.getApellido());
                    System.out.println("Cuenta " + tipoCuenta + " creada.");
                } else {
                    mostrarAlerta("Selección de cuenta", "Por favor, selecciona un tipo de cuenta.");
                }

            } catch (NumberFormatException ex) {
                mostrarAlerta("Formato incorrecto", "Por favor ingresa una edad válida.");
            }
        });

        grid.getChildren().addAll(nombreInput, apellidoInput, ineInput, edadInput, tipoCuentaCombo, registrarBtn);

        Scene scene = new Scene(grid, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarVentanaCuentaDebito(Stage primaryStage) {
        Stage ventanaDebito = new Stage();
        ventanaDebito.setTitle("Cuenta Débito");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Campos para realizar operaciones
        TextField cantidadInput = new TextField();
        cantidadInput.setPromptText("Cantidad");
        GridPane.setConstraints(cantidadInput, 0, 0);

        Button depositarBtn = new Button("Depositar");
        GridPane.setConstraints(depositarBtn, 1, 0);

        Button retirarBtn = new Button("Retirar");
        GridPane.setConstraints(retirarBtn, 2, 0);

        Button transferirBtn = new Button("Transferir");
        GridPane.setConstraints(transferirBtn, 3, 0);

        Button crearCreditoBtn = new Button("Crear Cuenta Crédito");
        GridPane.setConstraints(crearCreditoBtn, 0, 1);

        Button mostrarLimiteCreditoBtn = new Button("Mostrar Límite de Crédito");
        GridPane.setConstraints(mostrarLimiteCreditoBtn, 0, 2);

        depositarBtn.setOnAction(e -> {
            try {
                double cantidad = Double.parseDouble(cantidadInput.getText());
                if (cantidad <= 0) {
                    mostrarAlerta("Cantidad inválida", "La cantidad a depositar debe ser mayor que cero.");
                    return;
                }
                cuentaSeleccionada.depositar(cantidad);
                mostrarAlerta("Depósito realizado", "Has depositado " + cantidad + " en tu cuenta.");
            } catch (NumberFormatException ex) {
                mostrarAlerta("Formato incorrecto", "Por favor ingresa una cantidad válida.");
            }
        });

        retirarBtn.setOnAction(e -> {
            try {
                double cantidad = Double.parseDouble(cantidadInput.getText());
                if (cantidad <= 0) {
                    mostrarAlerta("Cantidad inválida", "La cantidad a retirar debe ser mayor que cero.");
                    return;
                }
                cuentaSeleccionada.retirar(cantidad);
                mostrarAlerta("Retiro realizado", "Has retirado " + cantidad + " de tu cuenta.");
            } catch (NumberFormatException ex) {
                mostrarAlerta("Formato incorrecto", "Por favor ingresa una cantidad válida.");
            } catch (SaldoInsuficienteException ex) {
                mostrarAlerta("Saldo insuficiente", "No tienes suficiente saldo para realizar esta operación.");
            }
        });

        transferirBtn.setOnAction(e -> {
            // Aquí podrías agregar lógica para transferir a otra cuenta
            mostrarAlerta("Transferencia", "La función de transferencia aún no está implementada.");
        });

        crearCreditoBtn.setOnAction(e -> {
            mostrarVentanaCreditoCreado(primaryStage);
        });

        mostrarLimiteCreditoBtn.setOnAction(e -> {
            if (cuentaSeleccionada instanceof CuentaCredito) {
                double limite = ((CuentaCredito) cuentaSeleccionada).getLimiteCredito();
                mostrarAlerta("Límite de Crédito", "El límite de crédito es: " + limite);
            } else {
                mostrarAlerta("Sin Crédito", "Esta cuenta no es una cuenta de crédito.");
            }
        });

        // Agregar campos y botones al layout
        grid.getChildren().addAll(cantidadInput, depositarBtn, retirarBtn, transferirBtn, crearCreditoBtn, mostrarLimiteCreditoBtn);

        // Mostrar la escena
        Scene scene = new Scene(grid, 500, 200);
        ventanaDebito.setScene(scene);
        ventanaDebito.show();
    }

    private void mostrarVentanaCuentaCredito(Stage primaryStage) {
        Stage ventanaCredito = new Stage();
        ventanaCredito.setTitle("Cuenta Crédito");

        // Crear layout para la ventana de cuenta de crédito
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Campos para realizar operaciones
        TextField cantidadInput = new TextField();
        cantidadInput.setPromptText("Cantidad");
        GridPane.setConstraints(cantidadInput, 0, 0);

        Button depositarBtn = new Button("Depositar");
        GridPane.setConstraints(depositarBtn, 1, 0);

        Button retirarBtn = new Button("Retirar");
        GridPane.setConstraints(retirarBtn, 2, 0);

        Button transferirBtn = new Button("Transferir");
        GridPane.setConstraints(transferirBtn, 3, 0);

        Button mostrarSaldoBtn = new Button("Mostrar Saldo Disponible");
        GridPane.setConstraints(mostrarSaldoBtn, 0, 1);

        Button mostrarLimiteCreditoBtn = new Button("Mostrar Límite de Crédito");
        GridPane.setConstraints(mostrarLimiteCreditoBtn, 0, 2);

        Button solicitarCreditoBtn = new Button("Solicitar Crédito");
        GridPane.setConstraints(solicitarCreditoBtn, 0, 3);

        depositarBtn.setOnAction(e -> {
            try {
                double cantidad = Double.parseDouble(cantidadInput.getText());
                if (cantidad <= 0) {
                    mostrarAlerta("Cantidad inválida", "La cantidad a depositar debe ser mayor que cero.");
                    return;
                }
                mostrarAlerta("Depósito realizado", "Has depositado " + cantidad + " en tu cuenta.");
                cuentaSeleccionada.depositar(cantidad);
            } catch (NumberFormatException ex) {
                mostrarAlerta("Formato incorrecto", "Por favor ingresa una cantidad válida.");
            }
        });

        retirarBtn.setOnAction(e -> {
            try {
                double cantidad = Double.parseDouble(cantidadInput.getText());
                if (cantidad <= 0) {
                    mostrarAlerta("Cantidad inválida", "La cantidad a retirar debe ser mayor que cero.");
                    return;
                }
                mostrarAlerta("Retiro realizado", "Has retirado " + cantidad + " de tu cuenta.");
                cuentaSeleccionada.retirar(cantidad);
            } catch (NumberFormatException ex) {
                mostrarAlerta("Formato incorrecto", "Por favor ingresa una cantidad válida.");
            } catch (SaldoInsuficienteException ex) {
                mostrarAlerta("Saldo insuficiente", "No tienes suficiente saldo para realizar esta operación.");
            }
        });

        transferirBtn.setOnAction(e -> {
            // Aquí podrías agregar lógica para transferir a otra cuenta
            mostrarAlerta("Transferencia", "La función de transferencia aún no está implementada.");
        });

        solicitarCreditoBtn.setOnAction(e -> {
            if (cuentaSeleccionada instanceof CuentaCredito) {
                ((CuentaCredito) cuentaSeleccionada).aumentarLimite(1000); // Solicita un crédito adicional de 1000
                mostrarAlerta("Crédito Aumentado", "El límite de crédito ha sido aumentado en 1000.");
            } else {
                mostrarAlerta("No es una cuenta de crédito", "No puedes solicitar crédito en esta cuenta.");
            }
        });

        mostrarSaldoBtn.setOnAction(e -> {
            if (cuentaSeleccionada instanceof CuentaCredito) {
                double saldoDisponible = ((CuentaCredito) cuentaSeleccionada).getSaldoDisponible();
                mostrarAlerta("Saldo Disponible", "El saldo disponible es: " + saldoDisponible);
            } else {
                mostrarAlerta("No es una cuenta de crédito", "Esta cuenta no es una cuenta de crédito.");
            }
        });

        mostrarLimiteCreditoBtn.setOnAction(e -> {
            if (cuentaSeleccionada instanceof CuentaCredito) {
                double limiteCredito = ((CuentaCredito) cuentaSeleccionada).getLimiteCredito();
                mostrarAlerta("Límite de Crédito", "El límite de crédito es: " + limiteCredito);
            } else {
                mostrarAlerta("No es una cuenta de crédito", "Esta cuenta no es una cuenta de crédito.");
            }
        });

        // Agregar campos y botones al layout
        grid.getChildren().addAll(cantidadInput, depositarBtn, retirarBtn, transferirBtn, solicitarCreditoBtn, mostrarSaldoBtn, mostrarLimiteCreditoBtn);

        // Mostrar la escena
        Scene scene = new Scene(grid, 500, 250);
        ventanaCredito.setScene(scene);
        ventanaCredito.show();
    }

    private void mostrarVentanaCreditoCreado(Stage primaryStage) {
        Stage ventanaCreditoCreado = new Stage();
        ventanaCreditoCreado.setTitle("Cuenta Crédito Creada");

        // Crear layout para la ventana de cuenta de crédito creada
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        Label mensaje = new Label("Tu cuenta de crédito ha sido creada \ncon un límite inicial de 5000.");
        vbox.getChildren().add(mensaje);

        // Mostrar la escena
        Scene scene = new Scene(vbox, 300, 150);
        ventanaCreditoCreado.setScene(scene);
        ventanaCreditoCreado.show();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
