/*10. Tablas de multiplicar: Escribe un programa que imprima la 
      tabla de multiplicar de un número ingresado por el usuario. */
/*--------------------------------------------------------*/
import java.util.Scanner;

public class tablaDeMultiplicar {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario que ingrese un número
        System.out.print("Ingrese un número para imprimir su tabla de multiplicar: ");
        int numero = scanner.nextInt();

        // Imprimir la tabla de multiplicar del número ingresado
        System.out.println("Tabla de multiplicar del " + numero + ":");
        for (int i = 1; i <= 10; i++) {
            int resultado = numero * i;
            System.out.println(numero + " x " + i + " = " + resultado);
        }

        // Cerrar el Scanner
        scanner.close();
    }
}

