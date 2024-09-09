public class EquipoSonido {

    private String marca;
    private String modelo;
    private int potencia;
    private int numCd;

    public EquipoSonido() {
        this.marca = "Sony";
        this.potencia = 100;
        this.numCd = 2;
    }
    public EquipoSonido(String marca, String modelo, int potencia, int voltios, int numCd) {
        this.marca = marca;
        this.modelo = modelo;
        this.potencia = potencia;
        this.numCd = numCd;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public int getPotencia() {
        return potencia;
    }
    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }
    public int getNumCd() {
        return numCd;
    }
    public void setNumCd(int numCd) {
        this.numCd = numCd;
    }
    @Override
    public String toString() {
        return ("Marca: " + this.marca + "\nModelo: " + this.modelo+ "\nPotencia: "
                + this.potencia + "\nNúmeros de CD: " + this.numCd);
    }
}
