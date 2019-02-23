package collection.b_arraylist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * 2018.4.17 ��һ�γ������ArrayList��Դ�롣
 * 1.ΪʲôAbstractList������̳���List�ӿڣ�ArrayList�̳���AbstractList������󣬻���Ҫ��ȥʵ��List�ӿڣ�
 * 	����Ϊ�ڶ�ArrayList���ж�̬�����ʱ����Ҫ֪����ʵ����ʲô�ӿڡ�
 * 	���ArrayListû���Լ�ȥʵ��List�ӿڣ�����ͨ����AbstractListȥʵ��List�ӿڡ���������һ��Ĺ�ϵ��ArrayList��������Class��û���õ���List�ӿڵġ�
 * 2.
 */
public class ArrayListNode {
	@Test
	public void test03(){
		Object[] o = new String[2];
		
	}
	
	/**
	 * .getClass() �� .class �����õ�����ʱ�����������Class��
	 * 
	 * Java��ÿ���඼����һ������ʱ����󣬸�Class�����б����˴������������������Ϣ��
     * ������.class���ش� Object ������ʱ��Class����Ҳ������getClass()��á�
     * ��ô˶����������ô�Class�����һЩ�������Խ��в��������磺
     * 	this.getClass().newInstance(); //��ȱʡ���캯������һ������Ķ���
     * 	this.getClass().getInterfaces(); //��ô���ʵ�ֵĽӿ���Ϣ
     * 	this.getClass().getMethods();//��ô���ʵ�ֵ����й��з���
     * 	Class.forName(" ... JDBC driver class name...."); // Class��ľ�̬����forName, ��DiverManagerע�����JDBC driver��
	 */
	@Test
	public void test02(){
		Object o = new String();
		System.out.println(o.getClass().getName());
		System.out.println(o.getClass());
		
		System.out.println(Person.class);
	}
	
	/**
	 * ���캯����public ArrayList(Collection<? extends E> c)
	 * ��c������ΪArrayList���캯������Σ��ǽ�c���������е�Ԫ�ض����Ƶ�ArrayList�����У������¡��
	 */
	@Test
	public void test01(){
		/**
		 * 1.�����зŵ��ǰ��ֻ������͵İ�װ�ࣺ���¡
		 * �����ֻ������͵İ�װ��Ƚ����⣬���л�����ƣ�����Ϊ��ȷ�����Ե�׼ȷ�ȣ���������ͨ���Զ������������ԣ���2.
		 */
		Collection<Integer> collection1 = new ArrayList<>();
		collection1.add(123);
		collection1.add(234);
		List<Integer> list1 = new ArrayList<>(collection1);
		for(Integer element : list1){
			System.out.println(element);
		}
		
		/**
		 * 2.�����зŵ����Զ���������ã����¡
		 */
		Person p1 = new Person("����", 23);
		Person p2 = new Person("����", 24);
		Collection<Person> collection2 = new LinkedList<>();
		collection2.add(p1);
		collection2.add(p2);
		ArrayList<Person> arrayList2 = new ArrayList<>(collection2);
		for(Person person : arrayList2){
			System.out.println(person);
		}
	}
	
	/**
	 * ArrayList�ı�����ı�LinkedList�ı������ÿ��𣿿���٣�
	 * ����һ��ArrayList��������add��һǧ������֡�
	 * ����һ��LinkedList��������add��һǧ������֡�
	 * �Ƚ�������֮�����ʱ����̽��ArrayList��LinkedList�����ݻ��ơ�
	 */
	@Test
	public void test00(){
		int size = 10000 * 1000;//һǧ��
		
		ArrayList<Integer> array = new ArrayList<>(size);
		long start = System.currentTimeMillis();
		for(int i = 0; i < size; i++){
			array.add(i);
		}
		long end = System.currentTimeMillis();
		System.out.println("array:add() " + (end - start));//2480
		
		System.out.println("----------------------------------");
		
		LinkedList<Integer> linked = new LinkedList<>();
		long start1 = System.currentTimeMillis();
		for(int i = 0; i < size; i++){//һǧ��
			linked.add(i);
		}
		long end1 = System.currentTimeMillis();
		System.out.println("linked:add() " + (end1 - start1));//3813
		
		System.out.println("------------- ��ǿforѭ�� ---------------------");
		
		long start2 = System.currentTimeMillis();
		for(Integer element : array){

		}
		long end2 = System.currentTimeMillis();
		System.out.println("array:������" + (end2 - start2));//29
		
		System.out.println("------------------------------------");
		
		long start3 = System.currentTimeMillis();
		for(Integer element : linked){
			
		}
		long end3 = System.currentTimeMillis();
		System.out.println("linked:������" + (end3 - start3));//80
		
		System.out.println("------------------- ��ͨforѭ�� ----------------------");
		/**
		 * ����ͨforѭ����ArrayList���б���������ǿ��forѭ����ArrayList���б��������ߵ���ʱû��̫������
		 * ����ǿ��forѭ����LinkedList���б�����Ч�ʲ������ArrayList�ı���Ч�����õͺܶ࣬��Ϊ�õ��ǵ�������
		 * ����ͨforѭ����LinkedList���б�����Ч�ʷǳ��������LinkedList������ṹ�йأ�LinkedList��get�κ�һ��λ�õ����ݵ�ʱ�򣬶����ǰ���������һ�顣
		 */
//		long start4 = System.currentTimeMillis();
//		for(int i = 0; i < array.size(); i++){
//			array.get(i);
//		}
//		long end4 = System.currentTimeMillis();
//		System.out.println(end4 - start4);
//		
//		long start5 = System.currentTimeMillis();
//		for(int i = 0; i < linked.size(); i++){
//			linked.get(i);
//		}
//		long end5 = System.currentTimeMillis();
//		System.out.println("��ͨforѭ�������������飺 " + (end5 - start5));
	}
}
