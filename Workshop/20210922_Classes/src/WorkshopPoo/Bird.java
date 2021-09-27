package WorkshopPoo;

public class Bird extends PawedAnimal implements LayEgg {

    public Bird(String name, int nbPaws) {
        super(name, nbPaws);
        this.noise = "piu piu";
        this.howItMoves = "fly";
    }
}
