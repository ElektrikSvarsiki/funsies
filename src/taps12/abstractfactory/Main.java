package taps12.abstractfactory;

interface Animal {
    void speak();
}

class Elephant implements Animal {
    public void speak() { System.out.println("Elephant says: Trumpet!"); }
}

class Cat implements Animal {
    public void speak() { System.out.println("Cat says: Meow!"); }
}

interface AnimalFactory {
    Animal createElephant();
    Animal createCat();
}

class WildAnimalFactory implements AnimalFactory {
    public Animal createElephant() { return new Elephant(); }
    public Animal createCat() { return new Cat(); }
}

class FarmAnimalFactory implements AnimalFactory {
    public Animal createElephant() {
        return () -> System.out.println("Farm Elephant says: Trumpet softly");
    }
    public Animal createCat() {
        return () -> System.out.println("Farm Cat says: Purr");
    }
}

class AnimalWorld {
    private final Animal elephant;
    private final Animal cat;

    public AnimalWorld(AnimalFactory factory) {
        elephant = factory.createElephant();
        cat = factory.createCat();
    }

    public void makeAnimalsSpeak() {
        elephant.speak();
        cat.speak();
    }
}

public class Main {
    public static void main(String[] args) {
        AnimalFactory factory = new WildAnimalFactory();
        AnimalWorld wildWorld = new AnimalWorld(factory);
        wildWorld.makeAnimalsSpeak();

        System.out.println("---");

        factory = new FarmAnimalFactory();
        AnimalWorld farmWorld = new AnimalWorld(factory);
        farmWorld.makeAnimalsSpeak();
    }
}
