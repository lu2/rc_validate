package net.haltuf.rc_validate;

import static org.junit.Assert.*;

import org.junit.Test;

import net.haltuf.rc_validate.RodneCislo.Gender;

/**
 * Tests for {@link RodneCislo}.
 * 
 * 
 * @author Lu2
 *
 */
public class RodneCisloTest {

	@Test
	public void testRodneCisloValid() {
		RodneCislo rodneCislo = new RodneCislo("8406240106");
		assertEquals("Day check", 24, rodneCislo.getBirthDay());
		assertEquals("Month check", 6, rodneCislo.getBirthMonth());
		assertEquals("Suffix check", "0106", rodneCislo.getBirthSuffix());
		assertEquals("Year check", 84, rodneCislo.getBirthYear());
		assertEquals("Gender check", Gender.MALE, rodneCislo.getGender());
		assertEquals("Rodne Cislo check", "8406240106", rodneCislo.getRodneCislo());
	}

}
