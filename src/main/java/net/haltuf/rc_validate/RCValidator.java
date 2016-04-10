package net.haltuf.rc_validate;

/**
 * Forwards banterCZ's JUnit calls to {@link RodneCislo}
 * @author Lu2
 *
 */
public class RCValidator {

	public boolean isValid(String rc) {
		try {
			new RodneCislo(rc);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}

}
