package WorkshopPoo;

public class Cat extends PawedAnimal {

    public Cat(String name, int nbPaws) {
        super(name, nbPaws);
        this.noise = "meow";
        this.howItMoves = "walk";
    }
}
