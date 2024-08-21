/*4. Número mayor: Escribe un programa que solicite tres 
     números al usuario y determine cuál es el mayor. */
/*-------------------------------------------------------*/

import java.util.Scanner;

public class numeroMayor {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario que ingrese tres números
        System.out.print("Ingrese el primer número: ");
        int numero1 = scanner.nextInt();

        System.out.print("Ingrese el segundo número: ");
        int numero2 = scanner.nextInt();

        System.out.print("Ingrese el tercer número: ");
        int numero3 = scanner.nextInt();

        // Inicializar una variable para almacenar el número mayor
        int mayor = numero1;

        // Comparar el primer número con el segundo
        if (numero2 > mayor) {
            mayor = numero2;
        }

        // Comparar el número mayor actual con el tercer número
        if (numero3 > mayor) {
            mayor = numero3;
        }

        // Mostrar el número mayor
        System.out.println("El número mayor es: " + mayor);

        // Cerrar el Scanner
        scanner.close();
    }
} 