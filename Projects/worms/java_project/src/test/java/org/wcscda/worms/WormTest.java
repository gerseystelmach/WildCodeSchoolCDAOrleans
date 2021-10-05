package org.wcscda.worms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.awt.*;
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
    Player player = new Player("Hector", Color.BLUE);
    Worm worm = player.createWorm("Achille");

    assertTrue(player.getWorms().contains(worm));
    worm.die();
    assertTrue(player.getWorms().contains(worm));
    worm.onRemoval();
    assertFalse(player.getWorms().contains(worm));
  }

  public void testOnRemoval() throws JsonProcessingException {
    Player player = new Player("Hector", Color.BLUE);
    Worm worm = player.createWorm("Achille");

    XmlMapper xmlMapper = new XmlMapper();
    String xml = xmlMapper.writeValueAsString(worm);
    System.out.println(xml);
  }

  @Override
  public void tearDown() throws Exception {
    super.tearDown();
    AbstractDrawableElement.getAllDrawable().clear();
  }
}
