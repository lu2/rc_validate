package net.haltuf.rc_validate;

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

	private final String rodneCislo;

	public RodneCislo(String rodneCislo) {
		// TODO Auto-generated constructor stub
	}
}
