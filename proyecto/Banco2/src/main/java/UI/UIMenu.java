package UI;

import java.util.Scanner;

import static UI.UIMenuAccount.*;
import static UI.UIMenuBanckCard.*;
public class UIMenu {
    static Scanner sc = new Scanner(System.in);

    public static void menu(){
        System.out.println("...::Bienvenido al cajero automatico::...");

        int respuesta;

        do {
            System.out.println("""
                1 Crear cuenta
                2 Tarjeta de debito
                3 Tarjeta de credito
                """);

            respuesta = sc.nextInt();

            switch (respuesta){
                case 1:
                    getAccountBanck();
                    break;
                case 2:
                    menuDebitCard();
                    break;
                case 3:
                    menuCreditCard();
                    break;
                default:
                    System.out.println("Ingrese una opción correcta");
            }
        }while(true);
    }
}
