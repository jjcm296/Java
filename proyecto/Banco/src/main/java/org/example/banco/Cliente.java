package org.example.banco;

import java.util.List;

public class Cliente {
    private String nombre;
    private String apellido;
    private int edad;
    private String ine;
    private List<Cuenta> cuenta;

    public Cliente(String nombre, String apellido, int edad, String ine, List<Cuenta> cuenta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ine = ine;
        this.cuenta = cuenta;
    }

    // Método para agregar una cuenta
    public void agregarCuenta(Cuenta cuenta) {
        this.cuenta.add(cuenta);
    }

    // Getter para la lista de cuentas
    public List<Cuenta> getCuentas() {
        return cuenta;
    }

    // Otros getters y setters si son necesarios
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getIne() {
        return ine;
    }

    public void setIne(String ine) {
        this.ine = ine;
    }
}
