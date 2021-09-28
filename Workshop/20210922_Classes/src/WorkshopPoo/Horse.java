package WorkshopPoo;

public class Horse extends PawedAnimal {


    public Horse(String name, int nbPaws) {
        super(name, nbPaws);
        // NRO 2021-09-27 : à ne jamais faire ! Transférer les arguments via le 
        //  constructeur
        this.noise = "iiirrrrí";
        this.howItMoves = "gallop";
    }
}
