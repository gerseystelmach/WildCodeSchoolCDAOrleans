package org.wcscda.worms;

import junit.framework.TestCase;
import org.wcscda.worms.board.AbstractDrawableElement;

import java.awt.*;

public class WormTest extends TestCase {

    public void testTakeDamage() {
        Worm worm = new Worm(null, "gus");
        assertEquals(100, worm.getLife());
        worm.takeDamage(50);
        assertEquals(50, worm.getLife());
    }

    public void testDie() {
        Player player = new Player("Hector", Color.BLUE);
        Worm worm = player.createWorm("Achille");

        assertTrue(player.getWorms().contains(worm));
        worm.die();
        assertTrue(player.getWorms().contains(worm));
        worm.onRemoval();
        assertFalse(player.getWorms().contains(worm));
    }

    public void testOnRemoval() {
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        AbstractDrawableElement.getAllDrawable().clear();
    }
}