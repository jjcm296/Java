public class CuentaBancaria {
    
    private String numeroCuenta;
    private double saldo;

     // Constructor sin parametros
    public CuentaBancaria() {
        this.numeroCuenta = "12345678";
        this.saldo = 1000;
    }
     // Constructor con parametro
    public CuentaBancaria(String numeroCuenta, int saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }
    // Getter para numeroCuenta
    public String getNumeroCuenta() {

        return this.numeroCuenta;
    }
    // Setter para numeroCuenta
    public void setNumeroCuenta(String numeroCuneta) {

        this.numeroCuenta = numeroCuneta;
    }
    // Getter para saldo
    public double getSaldo() {

        return this.saldo;
    }
    // Setter para saldo
    public void setSaldo(double saldo) {

        this.saldo = saldo;
    }
    //Método para depositar dinero en la cuenta
    public void depositar(double monto) {
        this.saldo += monto;
    }
    // Método para retirar dinero de la cuenta
    public void retirar(double monto) {
        this.saldo -= monto;
    }
}
