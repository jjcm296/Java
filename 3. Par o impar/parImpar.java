/*3. Par o impar: Escribe un programa que determine 
si un número ingresado por el usuario es par o impar.*/
/*-------------------------------------------------------*/

import java.util.Scanner;

public class parImpar {

    public static void main(String[] args) {
        
        // Crear un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario que ingrese un número
        System.out.print("Ingrese un número: ");
        int numero = scanner.nextInt();

        // Determinar si el número es par o impar
        if (numero % 2 == 0) {
            System.out.println("El número " + numero + " es par.");
        } else {
            System.out.println("El número " + numero + " es impar.");
        }

        // Cerrar el scanner
        scanner.close();
    }
}