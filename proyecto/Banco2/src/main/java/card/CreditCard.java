package card;

import account.Account;
import client.Client;

public class CreditCard extends BankCard{
    private static final int CREDIT = 1000;
    private double credit;
    private int limitCredit;
    public CreditCard(Client client){
        super(client);
        ApplyForCredit();
    }

    public void ApplyForCredit(){
        this.credit += CREDIT;
        this.limitCredit += CREDIT;
    }

    public double getCredit() {
        return credit;
    }

    public int getLimitCredit() {
        return limitCredit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= this.credit) {
            this.credit -= (int) amount;
            System.out.println("Retiro exitoso. \nSaldo Actual" + getCredit());
        }
    }

    @Override
    public String toString() {
        return "...::Datos de la tarjeta de credito::..." + super.toString();
    }
}
