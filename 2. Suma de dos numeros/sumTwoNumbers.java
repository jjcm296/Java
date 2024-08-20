import java.util.Scanner;

public class sumTwoNumbers { 
    public static void main(String[] args) { 

        Scanner scanner = new Scanner(System.in); 

        // Solicita al usuario que ingrese el primer número
        System.out.print("Enter the first number: ");
        int number1 = scanner.nextInt(); // Lee el primer número entero ingresado por el usuario

        // Solicita al usuario que ingrese el segundo número
        System.out.print("Enter the second number: ");
        int number2 = scanner.nextInt(); // Lee el segundo número entero ingresado por el usuario

        // Suma los dos números ingresados
        int sum = number1 + number2;

        // Muestra el resultado de la suma en la consola
        System.out.println("The sum of " + number1 + " and " + number2 + " is: " + sum);

        // Cierra el objeto Scanner para liberar los recursos
        scanner.close();
    }
}
