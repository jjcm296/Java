package org.example.banco;

import javafx.scene.control.Alert;

public class CuentaCredito extends Cuenta{
    private double limiteCredito;

    public CuentaCredito(String numeroCuenta, double saldo, double limiteCredito) {
        super(numeroCuenta, saldo);
        this.limiteCredito = limiteCredito;
        this.saldo = saldo + limiteCredito;
    }

    @Override
    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            mostrarAlerta("Depósito Exitoso", "Nuevo saldo = " + saldo);
        } else {
            mostrarAlerta("Error", "La cantidad debe ser mayor que 0.");
        }
    }

    @Override
    public boolean retirar(double cantidad) throws SaldoInsuficienteException {
        if (cantidad <= 0) {
            mostrarAlerta("Error", "La cantidad debe ser mayor que 0.");
            return false;
        }
        if (cantidad > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente. Saldo disponible: " + saldo);
        }
        saldo -= cantidad;
        mostrarAlerta("Retiro Exitoso", "Nuevo saldo = " + saldo);
        return true;
    }

    @Override
    public boolean transferir(double cantidad, Cuenta cuentaDestino) {
        try {
            if (retirar(cantidad)) {
                cuentaDestino.depositar(cantidad);
                return true;
            }
        } catch (SaldoInsuficienteException e) {
            mostrarAlerta("Error de transferencia", e.getMessage());
        }
        return false;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void aumentarLimite(double cantidad) {
        limiteCredito += cantidad;
        saldo += cantidad;
    }

    public double getSaldoDisponible() {
        return saldo;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
