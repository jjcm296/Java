public class EquipoSonidoTests {
    public static void main(String[] args) {

        EquipoSonido es = new EquipoSonido();

        es.setMarca("Sony");
        es.setModelo("LBT-D790");
        es.setPotencia(100);
        es.setNumCd(5);

        System.out.println(es.toString());
        System.out.println("----------------");
        System.out.println(es);
    }
}
