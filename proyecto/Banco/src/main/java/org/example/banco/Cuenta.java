package org.example.banco;

public abstract class Cuenta {
    protected String numeroCuenta;
    protected double saldo;

    public Cuenta(String numeroCuenta, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    public abstract void depositar(double cantidad);
    public abstract boolean retirar(double cantidad) throws SaldoInsuficienteException;
    public abstract boolean transferir(double cantidad, Cuenta cuentaDestino);
}
