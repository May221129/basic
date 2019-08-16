package collection.b_hashset;
//Collection½Ó¿Ú-Set-HashSet£º

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class TestHashSet {
	@Test
	public void hashSet(){
		Set<Object> set = new HashSet<>();
		set.add(11);
		set.add(2);
		set.add(3);
		set.add("AA");
		set.add("AA");
		System.out.println(set);
		int i = set.size();
		System.out.println(i);
	}
}
