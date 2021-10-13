import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KataSquareSumTest {
    @Test
    public void getPotentialNeighbours() {
        assert(new KataSquareSum(6).getPotentialNeighbours(2).isEmpty());
        assert(new KataSquareSum(7).getPotentialNeighbours(2).size() == 1);
    }

    @Test
    public void buildUp() {
        assert(new KataSquareSum(6).buildUp() == null);
    }
}