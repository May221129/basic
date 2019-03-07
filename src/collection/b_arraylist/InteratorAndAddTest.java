package collection.b_arraylist;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

/**
 * ���ԣ��߱������ϣ������ü��������Ԫ�ء�
 * ������׳���ConcurrentModificationException����
 */
public class InteratorAndAddTest {
	
	/**
	 * �׳���ConcurrentModificationException����
	 */
	@Test
	public void iteratorAndAddTest(){
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("A");
		arrayList.add("B");
		arrayList.add("C");
		arrayList.add("D");
		arrayList.add("E");
		
		Iterator<String> iterator = arrayList.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
			arrayList.add("AA");
		}
	}
	
	/**
	 * �׳���ConcurrentModificationException����
	 */
	@Test
	public void iteratorAndAddTest1(){
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("A");
		arrayList.add("B");
		arrayList.add("C");
		arrayList.add("D");
		arrayList.add("E");
		
		Iterator<String> iterator = arrayList.iterator();
		while(iterator.hasNext()){
			if(iterator.next().equals("C")){
				arrayList.remove("C");
			}
		}
	}
	
	/**
	 * ���ᱨ��
	 */
	@Test
	public void iteratorAndAddTest2(){
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("A");
		arrayList.add("B");
		arrayList.add("C");
		arrayList.add("D");
		arrayList.add("E");
		
		Iterator<String> iterator = arrayList.iterator();
		while(iterator.hasNext()){
			if(iterator.next().equals("C")){
				iterator.remove();
			}
		}
	}
}
