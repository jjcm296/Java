/* 9. Factorial: Escribe un programa que calcule el factorial
     de un número ingresado por el usuario.*/
/*-------------------------------------------------------*/
import java.util.Scanner;

public class factorial {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario que ingrese un número entero positivo
        System.out.print("Ingrese un número entero positivo para calcular su factorial: ");
        int numero = scanner.nextInt();

        // Verificar que el número ingresado sea positivo
        if (numero < 0) {
            System.out.println("El número ingresado debe ser un entero positivo.");
        } else {
            long factorial = 1;

            // Calcular el factorial usando un bucle for
            for (int i = 1; i <= numero; i++) {
                factorial *= i;
            }

            // Mostrar el resultado
            System.out.println("El factorial de " + numero + " es " + factorial + ".");
        }

        // Cerrar el Scanner
        scanner.close();
    }
}

