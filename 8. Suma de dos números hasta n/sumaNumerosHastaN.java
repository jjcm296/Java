/* 8. Suma de números hasta n: Escribe un programa que sume todos los números del 1 al n, 
      donde n es un valor ingresado por el usuario. */
/*-------------------------------------------------------*/
      
import java.util.Scanner;

public class sumaNumerosHastaN {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario que ingrese el valor de n
        System.out.print("Ingrese un valor entero positivo para n: ");
        int n = scanner.nextInt();

        // Verificar que el valor de n sea positivo
        if (n <= 0) {
            System.out.println("El valor ingresado debe ser un entero positivo.");
        } else {
            int suma = 0;

            // Sumar todos los números del 1 hasta n
            for (int i = 1; i <= n; i++) {
                suma += i;
            }

            // Mostrar el resultado
            System.out.println("La suma de los números del 1 al " + n + " es " + suma + ".");
        }

        // Cerrar el Scanner
        scanner.close();
    }
}
