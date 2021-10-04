package org.wcscda.worms;

import junit.framework.TestCase;
import org.wcscda.worms.board.AbstractDrawableElement;

public class WormTest extends TestCase {

    public void testTakeDamage() {
        Worm worm = new Worm(null, "gus");
        assertEquals(100, worm.getLife());
        worm.takeDamage(50);
        assertEquals(50, worm.getLife());
    }

    public void testDie() {
    }

    public void testOnRemoval() {
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        AbstractDrawableElement.getAllDrawable().clear();
    }
}