import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        CuentaBancaria cuentaBancaria = new CuentaBancaria();

        System.out.println("Ingrese un numero de cuenta: ");
        cuentaBancaria.setNumeroCuenta(input.nextLine());

        System.out.println("Ingrese un monto inicial: ");
        cuentaBancaria.setSaldo(input.nextDouble());

        while (true) {
            System.out.println("mostrar saldo: 1, depositar: 2, retirar: 3, salir: 4");
            int opcion = input.nextInt();

            switch (opcion) {
                case 1:
                    System.out.printf("Saldo: %.2f%n", cuentaBancaria.getSaldo());
                    break;
                case 2:
                    System.out.println("Ingrese el monto a depositar: ");
                    double deposito = input.nextDouble();

                    if(deposito < 0){
                        System.out.println("El monto a depositar no puede ser menor a 0.");
                        break;
                    }
                    cuentaBancaria.depositar(deposito);
                    System.out.println("Deposito exitoso!");
                    break;
                case 3:
                    System.out.println("Ingrese el monto a retirar: ");
                    double retiro = input.nextDouble();

                    if(retiro < 0  || retiro > cuentaBancaria.getSaldo()){
                        System.out.println("El monto a retirar no puede ser menor a 0 o mayor al saldo actual.");
                        break;
                    }else{
                        cuentaBancaria.retirar(retiro);
                        System.out.println("Retiro exitoso!");
                        break;
                    }
                case 4:
                    System.out.println("Gracias por utilizar nuestros servicios!");
                    return;
            }
        }
    }
}
-