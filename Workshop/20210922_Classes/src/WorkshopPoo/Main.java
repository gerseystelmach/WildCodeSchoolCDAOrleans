package WorkshopPoo;

public class Main {

    public static void main(String[] args) {

        Bird woodpecker = new Bird("Woodpecker", 2);
        woodpecker.layEgg();
        Cat felix = new Cat("Felix", 4);
        Dauphin flipper = new Dauphin("Flipper");
        Horse carpeado = new Horse("Carpeado", 4);
        Snake anaconda = new Snake("Anaconda");
        Dog lessie = new Dog("Lessie", 4);
        Fish nemo = new Fish("Nemo");

        System.out.println("I am " + woodpecker.getName() + woodpecker.move() + " I do " + woodpecker.makeNoise() + " and I have " + woodpecker.getNbPaws() + " paws.");
        System.out.println("I am " + felix.getName() + felix.move() + " I do " + felix.makeNoise() + " and I have " + felix.getNbPaws() + " paws.");
        System.out.println("I am " + flipper.getName() +  flipper.move() + " I do " + flipper.makeNoise() );
        System.out.println("I am " + carpeado.getName() + carpeado.move() + " I do " + carpeado.makeNoise() + " and I have " + carpeado.getNbPaws() + " paws.");
        System.out.println("I am " + anaconda.getName() +  anaconda.move() + " I do " + anaconda.makeNoise() );
        System.out.println("I am " + lessie.getName() +  lessie.move() + " I do " + lessie.makeNoise() + " and I have " + lessie.getNbPaws() + " paws.");
        System.out.println("I am " + nemo.getName() +  nemo.move() + " I do " + nemo.makeNoise() );

    }
}
