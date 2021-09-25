package WorkshopPoo;

public class PawedAnimal extends Animal {

    private int nbPaws;

    public PawedAnimal(String name, int nbPaws) {
        super(name);
        this.nbPaws = nbPaws;
    }

    public int getNbPaws() {
        return nbPaws;
    }

}
