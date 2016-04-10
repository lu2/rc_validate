package net.haltuf.rc_validate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Main class with some demonstration of what can be done with {@link RodneCislo}.
 * 
 * @author Lu2
 *
 */
public class Main {

	private static String[] rcs = { "8406240106", "8456240100", "7310285169", "7360285174" };

	public static void main(String[] args) {

		List<RodneCislo> rcList = new ArrayList<>();
		for (String rc : rcs) {
			rcList.add(new RodneCislo(rc));
		}
		printIterable(rcList);

		System.out.println("Sort rodne cislo according to sex");
		Collections.sort(rcList, new Comparator<RodneCislo>() {

			@Override
			public int compare(RodneCislo o1, RodneCislo o2) {
				return o1.getGender().compareTo(o2.getGender());
			}
		});
		printIterable(rcList);

	}

	private static <T> void printIterable(Iterable<T> iterable) {
		for (T t : iterable) {
			System.out.println(t.toString());
		}
	}

}
