package net.haltuf.rc_validate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for {@link RCValidator}.
 * 
 * @author banterCZ
 * 
 * Taken from https://gist.github.com/banterCZ/3979014#file-rcvalidatortest-java
 */
public class RCValidatorTest {
	
	private RCValidator validator;
	
	@Before
	public void setUp() {
		validator = new RCValidator();
	}

	@Test
	public void nullValue() {
		boolean result = validator.isValid(null);
		assertFalse(result);
	}
	
	@Test
	public void empty() {
		boolean result = validator.isValid("");
		assertFalse(result);
	}
	
	@Test
	public void numberFormatException() {
		boolean result = validator.isValid("x123456789");
		assertFalse(result);
	}
	
	/**
	 * Only 8 instead of 9 or 10 numbers.
	 */
	@Test
	public void invalidLengthShort() {
		boolean result = validator.isValid("73602851");
		assertFalse(result);
	}
	
	/**
	 * Too many characters, 11 instead of 9 or 10 numbers.
	 */
	@Test
	public void invalidLengthLong() {
		boolean result = validator.isValid("73102851691");
		assertFalse(result);
	}
	
	/**
	 * 731028/5169
	 */
	@Test
	public void validNormalMan() {
		boolean result = validator.isValid("7310285169");
		assertTrue(result);
	}
	
	/**
	 * 736000/5158
	 */
	@Test
	public void invalidDayZero() {
		boolean result = validator.isValid("7360005158");
		assertFalse(result);
	}
	
	/**
	 * 736033/5158
	 */
	@Test
	public void invalidDay() {
		boolean result = validator.isValid("7360335158");
		assertFalse(result);
	}
	
	/**
	 * 120230/0011
	 */
	@Test
	public void invalidDayFebruary() {
		boolean result = validator.isValid("1202300011");
		assertFalse(result);
	}
	
	/**
	 * 2011-02-29
	 */
	@Test
	public void invalidLeapYear() {
		boolean result = validator.isValid("1102290002");
		assertFalse(result);
	}
	
	/**
	 * 2012-02-29
	 */
	@Test
	public void validLeapYear() {
		boolean result = validator.isValid("1202290001");
		assertTrue(result);
	}
	
	/**
	 * 840501/133
	 * @see #validExceptionMod11()
	 */
	@Test
	public void invalidExceptionMod11() {
		boolean result = validator.isValid("8405011331");
		assertFalse(result);
	}
	
	/**
	 * 835607/8656
	 */
	@Test
	public void invalid() {
		boolean result = validator.isValid("8356078656");
		assertFalse(result);
	}
	
	/**
	 * 530506/12
	 */
	@Test
	public void oldInvalid8() {
		boolean result = validator.isValid("53050612");
		assertFalse(result);
	}
	
	/**
	 * 540506/123
	 */
	@Test
	public void newInvalid8() {
		boolean result = validator.isValid("540506123");
		assertFalse(result);
	}
	
	/**
	 * 120203/1237
	 */
	@Test
	public void validAfter2K() {
		boolean result = validator.isValid("1202031237");
		assertTrue(result);
	}
	
	/**
	 * 126231/0005
	 */
	@Test
	public void monthOffset50() {
		boolean result = validator.isValid("1262310005");
		assertTrue(result);
	}
	
	/**
	 * 033231/0000
	 */
	@Test
	public void invalidMonthOffset20() {
		boolean result = validator.isValid("0332310000");
		assertFalse(result);
	}
	
	/**
	 * 120001/0009
	 */
	@Test
	public void invalidMonth() {
		boolean result = validator.isValid("1200010009");
		assertFalse(result);
	}
	
}