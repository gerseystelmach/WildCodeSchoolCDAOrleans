package WorkshopPoo;

public abstract class Animal {

    private String name;
    // NRO : Never do that ! Just pass the attributes 
    protected String noise;
    protected String howItMoves;

    public Animal(String name) {
        this.name = name;
    }

    public String makeNoise() {
        return this.noise;
    }

    public String move() {
        return " I " + this.howItMoves;
    }

    public String getName() {
        return name;
    }

}
