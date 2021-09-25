package WorkshopPoo;

public class Dog extends PawedAnimal{

    public Dog(String name, int nbPaws) {
        super(name, nbPaws);
        this.noise = "auf auf";
        this.howItMoves = "walk";
    }
}
