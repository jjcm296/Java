package UI;

import card.CreditCard;
import card.DebitCard;
import java.util.Scanner;


public class UIMenuBanckCard {
    static Scanner sc = new Scanner(System.in);
    private static DebitCard debitCard;
    private static CreditCard creditCard;

    public static void menuDebitCard(){
        System.out.println("...:Menu Tarjeta de débito::...");

        boolean nipValid = true;

        do{
            System.out.println("Ingrese su NIP por favor");
            String nip = sc.nextLine();

            for (DebitCard card : UIMenuAccount.getDebitCards()) {
                if (card.getNip().equals(nip)) {
                    debitCard = card;
                    nipValid = false;
                    break;
                }
            }

            if (nipValid) {
                System.out.println("Nip incorrecto. intente nuevamente.");
            }
        }while(nipValid);

        boolean continueMenu = true;

        do {
            System.out.println("""
                1 Retirar
                2 Depositar
                3 Mostrar saldo
                4 Regresar
                0 Salir
                """);

            int option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Ingrese el monto a retirar:");
                    double withdrawAmount = sc.nextDouble();
                    debitCard.withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.println("Ingrese el monto a depositar:");
                    double depositAmount = sc.nextDouble();
                    debitCard.deposit(depositAmount);
                    break;
                case 3:
                    System.out.println("Saldo actual: " + debitCard.getBalance());
                    break;
                case 4:
                    UIMenu.menu();
                    break;
                case 0:
                    UIMenu.menu();
                    break;
                default:
                    System.out.println("Ingrese una opción correcta");
            }
        }while(continueMenu);
    }

    public static void menuCreditCard(){
        System.out.println("...::Menu Tarjeta de credito::...");

        boolean nipValid = true;
        do{
            System.out.println("Ingrese su NIP por favor");
            String nip = sc.nextLine();

            for (CreditCard card : UIMenuAccount.getCreditCards()) {
                if (card.getNip().equals(nip)) {
                    creditCard = card;
                    nipValid = false;
                    break;
                }
            }

            if (nipValid) {
                System.out.println("Nip incorrecto. intente nuevamente.");
            }
        }while(nipValid);

        boolean continueMenu = true;
        do {
            System.out.println("""
                1 Retirar
                2 Mostrar saldo
                3 Mostrar limite de credito
                4 Fecha de corte
                5 Solicitar credito
                6 Regresar
                0 Salir
                """);

            int option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Ingrese el monto a retirar:");
                    double withdrawAmount = sc.nextDouble();
                    creditCard.withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.println("Saldo actual: " + creditCard.getCredit());
                    break;
                case 3:
                    System.out.println("Limite de credito: " + creditCard.getLimitCredit());
                    break;
                case 4:
                    System.out.println("Futura actualización");
                    break;
                case 5:
                    System.out.println("Credito aceptado: +1000");
                    creditCard.ApplyForCredit();
                    break;
                case 6:
                    UIMenu.menu();
                    break;
                case 0:
                    UIMenu.menu();
                    break;
                default:
                    System.out.println("Ingrese una opción correcta");
            }
        }while(continueMenu);
    }
}
