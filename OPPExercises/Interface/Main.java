//Definir la primera interfaz
interface Flyable {
  void Fly();
}
//Definir la segunda interfaz
interface Swimmable{
    void Swim();
}
//Clase que implementa ambas interfaces
class Duck implements Flyable, Swimmable {
    @Override
    public void Fly() {
        System.out.println("El pato estuvo volando");
    }
    @Override
    public void Swim() {
        System.out.println("El pato estuvo nadando");
    }
}
//clase principal
public class Main {
    public static void main(String[] args) {
        Duck pato = new Duck();
        //Utilizamos los métodos de las interfaces
        pato.Fly();
        pato.Swim();
    }
}