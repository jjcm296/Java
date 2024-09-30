package card;

import account.Deposit;
import client.Client;

public class DebitCard extends BankCard implements Deposit {
    private double balance;

    public DebitCard(Client client) {
        super(client);
        balance = 0;
    }

    public double getBalance(){
        return balance;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Retiro exitoso. Saldo actual: " + this.balance);
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }

    @Override
    public void deposit(double amount){
        if (amount > 0) {
            balance += amount;
            System.out.println("Depósito exitoso. Saldo actual: " + balance);
        } else {
            System.out.println("El monto debe ser positivo.");
        }
    }

    @Override
    public String toString(){
        return "...::Datos de la tarjeta de debito::..." + super.toString();
    }
}