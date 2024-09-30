package account;

import client.Client;

public class Account {
    private static int idCounter = 1;

    private int id;
    private Client client;
    private double balance;
    private String accountType; // Para diferenciar entre cuentas de débito y crédito

    // Constructor
    public Account(Client client, String accountType) {
        this.id = idCounter++;
        this.client = client;
        this.balance = 0;
        this.accountType = accountType; // Almacena el tipo de cuenta
    }

    // Getters
    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Se han depositado " + amount + ". Nuevo saldo: " + balance);
        } else {
            System.out.println("El monto debe ser mayor a 0.");
        }
    }


    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Se han retirado " + amount + ". Nuevo saldo: " + balance);
        } else {
            System.out.println("Monto inválido. Verifique su saldo y el monto a retirar.");
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", client=" + client +
                ", balance=" + balance +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
