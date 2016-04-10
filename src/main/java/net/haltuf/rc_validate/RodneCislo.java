package net.haltuf.rc_validate;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Validates and provides information about rodne cislo.
 * 
 * Rodne cislo is defined by § 13 odst. 3 ÚZ č. 302/2004 Sb.:
 * 
 * (3) Rodné číslo je desetimístné číslo, které je dělitelné jedenácti beze
 * zbytku. První dvojčíslí vyjadřuje poslední dvě číslice roku narození, druhé
 * dvojčíslí vyjadřuje měsíc narození, u žen zvýšené o 50, třetí dvojčíslí
 * vyjadřuje den narození. Čtyřmístná koncovka je rozlišujícím znakem obyvatel
 * narozených v tomtéž kalendářním dnu.
 * 
 * For simplification this class works only with rodne cislo issued after 1.
 * January 1954 ie. § 13 odst. 4 is not considered as valid rodne cislo.
 * 
 * @author Lu2
 *
 */
public class RodneCislo {

	/**
	 * Supported format of rodne cislo.
	 */
	public static final String SUPPORTED_FORMAT = "\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d";
	
	/**
	 * Configures minimum possible year for this class
	 */
	private static final int MIN_YEAR = 54;
	
	/**
	 * Configures maximum possible year for this class
	 */
	private static final int MAX_YEAR = 16;

	/**
	 * Gender obtained from rodneCislo
	 */
	public enum Gender {
		MALE, FEMALE
	}

	/**
	 * rodne cislo in full form (10 digits)
	 */
	private final String rodneCislo;

	/**
	 * last 2 digits of year of birth, taken from rodneCislo
	 */
	private final int birthYear;

	/**
	 * month of birth, taken from rodneCislo
	 */
	private final int birthMonth;

	/**
	 * day of birth, taken from rodneCislo
	 */
	private final int birthDay;

	/**
	 * 4 digit suffix, taken from rodneCislo
	 */
	private final String birthSuffix;

	/**
	 * Gender recognized from rodneCislo
	 */
	private final Gender gender;

	public RodneCislo(String rodneCislo) {
		if (rodneCislo == null) {
			throw new IllegalArgumentException("rodneCislo cannot be null");
		}
		this.rodneCislo = rodneCislo;

		// je desetimístné číslo
		if (!rodneCislo.matches(SUPPORTED_FORMAT)) {
			throw new IllegalArgumentException("rodneCislo is not 10digit number");
		}
		birthSuffix = rodneCislo.substring(6);

		// je dělitelné jedenácti beze zbytku
		try {
			if (Long.valueOf(rodneCislo) % 11 != 0) {
				throw new IllegalArgumentException("rodneCislo is not divisible by 11");
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("rodneCislo has unexpected number format");
		}

		try {
			birthYear = Integer.valueOf(rodneCislo.substring(0, 2));
			// allow only years from 54 until today
			if (birthYear < MIN_YEAR && birthYear >= MAX_YEAR) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("rodneCislo has unexpected year");
		}

		try {
			int birthMonth = Integer.valueOf(rodneCislo.substring(2, 4));
			// allow only only 1-12 for males and 51-62 for females
			if (birthMonth >= 1 && birthMonth <= 12) {
				gender = Gender.MALE;
			} else if (birthMonth >= 51 && birthMonth <= 62) {
				gender = Gender.FEMALE;
				birthMonth = birthMonth - 50;
			} else {
				throw new NumberFormatException();
			}
			this.birthMonth = birthMonth;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("rodneCislo has unexpected month");
		}

		try {
			birthDay = Integer.valueOf(rodneCislo.substring(4, 6));
			LocalDate.of(birthYear, birthMonth, birthDay);
		} catch (NumberFormatException | DateTimeException e) {
			throw new IllegalArgumentException("rodneCislo has unexpected day");
		}
	}

	public String getRodneCislo() {
		return rodneCislo;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public int getBirthMonth() {
		return birthMonth;
	}

	public int getBirthDay() {
		return birthDay;
	}

	public String getBirthSuffix() {
		return birthSuffix;
	}

	public Gender getGender() {
		return gender;
	}

	@Override
	public String toString() {
		return "RodneCislo [rodneCislo=" + rodneCislo + ", gender=" + gender + ", birthYear=" + birthYear
				+ ", birthMonth=" + birthMonth + ", birthDay=" + birthDay + "]";
	}
	
	
}
