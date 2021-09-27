package WorkshopPoo;

public class Fish extends Animal implements LayEgg {

    public Fish(String name) {
        super(name);
        this.noise = "gulp gulp";
        this.howItMoves = "swim";
    }

}
