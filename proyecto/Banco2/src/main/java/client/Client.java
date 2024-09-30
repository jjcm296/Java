package client;

import account.Account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client {
    private String name;
    private String firstSurname;
    private String secondSurname;
    private String email;
    private String curp;
    private Date birthdate;
    private String phoneNumber;

    private List<Account> accounts;

    public Client(String name, String firstSurname, String secondSurname, String email, String curp, Date birthdate, String phoneNumber) {
        this.name = name;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.email = email;
        this.curp = curp;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        if (!accounts.contains(account)) {
            accounts.add(account);
        } else {
            System.out.println("Esta cuenta ya existe para el cliente.");
        }
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getFullName() {
        return this.name + " " + this.firstSurname + " " + this.secondSurname;
    }

    public String getCurp() {
        return this.curp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(getFullName())
                .append(", CURP: ").append(curp)
                .append(", Cuentas: ").append(accounts.toString());
        return sb.toString();
    }
}
