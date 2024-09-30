package UI;

import static UI.UIMenu.*;
import account.Account;
import card.CreditCard;
import card.DebitCard;
import client.Client;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UIMenuAccount {
    static Scanner sc = new Scanner(System.in);

    private static ArrayList<CreditCard> creditCards = new ArrayList<>();
    private static ArrayList<DebitCard> debitCards = new ArrayList<>();
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static ArrayList<Client> clients = new ArrayList<>(); // Agregar lista de clientes

    public static void getAccountBanck() {
        System.out.println("Ingrese su CURP:");
        String curp = sc.nextLine();

        Client existingClient = findClientByCurp(curp);

        if (existingClient != null) {
            System.out.println("""
                1 Crear cuenta de debito
                2 Crear cuenta de credito
                3 Salir
                """);

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    createDebitCard(existingClient);
                    break;
                case 2:
                    createCreditCard(existingClient);
                    break;
                case 3:
                    menu();
                    break;
            }
        } else {
            Client newClient = getData(curp);
            clients.add(newClient);
            System.out.println("""
                1 Crear cuenta de debito
                2 Crear cuenta de credito
                3 Salir
                """);
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    createDebitCard(newClient);
                    break;
                case 2:
                    createCreditCard(newClient);
                    break;
                case 3:
                    menu();
                    break;
            }
        }
    }

    private static void createDebitCard(Client client) {
        String curp = client.getCurp();
        if (debitAccountExists(curp)) {
            System.out.println("Ya tienes una cuenta de débito.");
        } else {
            Account account = new Account(client, "Debito");
            accounts.add(account);

            DebitCard debitCard = new DebitCard(client);
            debitCards.add(debitCard);

            System.out.println("Cuenta de débito creada.");
            System.out.println(debitCard.toString());
        }
    }

    private static void createCreditCard(Client client) {
        String curp = client.getCurp();
        if (creditAccountExists(curp)) {
            System.out.println("Ya tienes una cuenta de crédito.");
        } else {
            Account account = new Account(client, "Credit");
            accounts.add(account);

            CreditCard creditCard = new CreditCard(client);
            creditCard.ApplyForCredit();
            creditCards.add(creditCard);

            System.out.println("Cuenta de crédito creada.");
            System.out.println(creditCard.toString());
        }
    }

    private static Client findClientByCurp(String curp) {
        for (Client client : clients) {
            if (client.getCurp().equalsIgnoreCase(curp)) {
                return client;
            }
        }
        return null;
    }

    public static Client getData(String curp) {
        System.out.println("Ingrese su nombre:");
        String name = sc.nextLine();
        System.out.println("Ingrese su primer apellido:");
        String firstSurname = sc.nextLine();
        System.out.println("Ingrese su segundo apellido:");
        String secondSurname = sc.nextLine();
        System.out.println("Ingrese su correo electrónico:");
        String email = sc.nextLine();
        System.out.println("Ingrese su fecha de nacimiento (dd/MM/yyyy):");

        Date birthdate = null;

        while (true) {
            String birthdateStr = sc.nextLine();
            try {
                birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(birthdateStr);
                if (isAdult(birthdate)) {
                    break;
                } else {
                    System.out.println("Debes ser mayor de edad (18 años o más) para crear una cuenta. Intenta nuevamente.");
                }
            } catch (ParseException e) {
                System.out.println("Formato de fecha incorrecto. Intenta nuevamente.");
            }
        }

        System.out.println("Ingrese su número de teléfono:");
        String phoneNumber = sc.nextLine();

        return new Client(name, firstSurname, secondSurname, email, curp, birthdate, phoneNumber);
    }

    private static boolean debitAccountExists(String curp) {
        for (DebitCard debitCard : debitCards) {
            if (curp.equalsIgnoreCase(debitCard.getClient().getCurp())) {
                return true;
            }
        }
        return false;
    }

    private static boolean creditAccountExists(String curp) {
        for (CreditCard creditCard : creditCards) {
            if (curp.equalsIgnoreCase(creditCard.getClient().getCurp())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isAdult(Date birthdate) {
        Date today = new Date();
        int age = today.getYear() - birthdate.getYear();
        if (today.getMonth() < birthdate.getMonth() ||
                (today.getMonth() == birthdate.getMonth() && today.getDate() < birthdate.getDate())) {
            age--;
        }
        return age >= 18;
    }

    public static ArrayList<DebitCard> getDebitCards(){
        return debitCards;
    }

    public static ArrayList<CreditCard> getCreditCards(){
        return creditCards;
    }

    public static void addCreditCard(CreditCard creditCard) {
        creditCards.add(creditCard);
    }

    public static void addDebitCard(DebitCard debitCard) {
        debitCards.add(debitCard);
    }
}