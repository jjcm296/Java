public class Gerente extends Empleado {
    private String departamento;

    public Gerente(String nombre, String puesto, String departamento) {
        super(nombre, puesto);
        this.departamento = departamento;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void mostrarDepartamento() {
        System.out.println("Departamento: " + departamento);
    }
}