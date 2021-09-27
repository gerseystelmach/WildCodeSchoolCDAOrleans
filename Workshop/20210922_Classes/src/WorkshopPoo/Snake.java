package WorkshopPoo;

public class Snake extends Animal implements LayEgg {

    public Snake(String name) {
        super(name);
        this.noise = "sssssssssssssssss";
        this.howItMoves = "crawl";
    }
}
