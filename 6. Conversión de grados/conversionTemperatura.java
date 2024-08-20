import java.util.Scanner;

public class conversionTemperatura {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario que ingrese la temperatura en grados Celsius
        System.out.print("Ingrese la temperatura en grados Celsius: ");
        double celsius = scanner.nextDouble();

        // Convertir la temperatura a Fahrenheit usando la fórmula
        double fahrenheit = (celsius * 9/5) + 32;

        // Mostrar la temperatura convertida en grados Fahrenheit
        System.out.println(celsius + " grados Celsius es igual a " + fahrenheit + " grados Fahrenheit.");

        // Cerrar el Scanner
        scanner.close();
    }
}
