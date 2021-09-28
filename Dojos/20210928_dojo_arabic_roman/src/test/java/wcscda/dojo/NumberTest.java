package wcscda.dojo;

import junit.framework.TestCase;
import junit.framework.*;

public class NumberTest extends TestCase {
	public void testChiffre() throws Exception{
		assertEquals(1,Number.toArabic("I"));
		assertEquals(20,Number.toArabic("XX"));
		assertEquals(500,Number.toArabic("D"));
		assertEquals(25,Number.toArabic("XXV"));
	}
}

