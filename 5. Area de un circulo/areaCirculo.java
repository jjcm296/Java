/*5. Área de un círculo: Escribe un programa que calcule el 
     área de un círculo a partir de su radio.*/
/*-------------------------------------------------------*/
import java.util.Scanner;

public class areaCirculo {

    public static void main(String[] args) {
        // Crear un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario que ingrese el radio del círculo
        System.out.print("Ingrese el radio del círculo: ");
        double radio = scanner.nextDouble();

        // Calcular el área del círculo
        double area = Math.PI * radio * radio;

        // Mostrar el área del círculo
        System.out.println("El área del círculo con radio " + radio + " es: " + area);

        // Cerrar el Scanner
        scanner.close();
    }
}