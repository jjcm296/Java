public class Empleado extends Persona {
    private String puesto;

    public Empleado(String nombre, String puesto) {
        super(nombre);
        this.puesto = puesto;
    }

    public String getPuesto() {
        return puesto;
    }
    public void mostrarPuesto() {
        System.out.println("Puesto: " + puesto);
    }
}