package WorkshopPoo;

public interface LayEgg {

    default Egg layEgg() {
        return new Egg();
    }
}
