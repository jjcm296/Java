class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void makeSound() {
        System.out.println(name + " makes a sound");
    }
}
class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    @Override
    public void makeSound() {
        System.out.println(getName() + " hace: " + "woofs");
    }
}
class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
    @Override
    public void makeSound() {
        System.out.println(getName() + " hace: " + "Miau!");
    }
}
public class Main {
    public static void main(String[] args) {
        Animal dog = new Dog("Buddy");
        Animal cat = new Cat("Ing");

        dog.makeSound();
        cat.makeSound();
    }
}
