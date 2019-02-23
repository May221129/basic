package collection.b_arraylist;
//Collection����-List-ArrayList��֪ʶ����

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

public class TestArrayList {
	@Test
	public void testCollection4(){
		
		Collection c2 = new ArrayList();	
		c2.add(123);
		c2.add("����������ã�");
		c2.add("I Love You");
		
//		14.iterator():����һ��Iterator�ӿ�ʵ����Ķ��󣬴Ӷ�ʵ�ּ��ϵı�����
		Iterator iterator = c2.iterator();//����Ҫ��Iterator���е�����
//		��ʽһ(�Ƽ�ʹ�ã���
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
//		�������������Ƽ�ʹ�ã���Ϊ�����Ѿ����ֳɵķ������Ե����ˣ���
		Object[] obj = c2.toArray();
		for(int i = 0; i < obj.length; i++){
			System.out.println("*" + obj[i]);//iterator.next()
		}
		
//		��ʽ����������ͨ���ԣ���Ϊ�㲻��ÿ�ζ�֪���������м���Ԫ�أ���
//		System.out.println(iterator.next());
//		System.out.println(iterator.next());
//		System.out.println(iterator.next());
	}
	
	@Test
	public void TestCollection3(){
		
		Collection c2 = new ArrayList();	
		c2.add(123);
		c2.add("����������ã�");
		c2.add("I Love You");
		
		Collection c3 = new ArrayList();
		c3.add(123);
		c3.add(new String("����������ã�"));
		c3.add(5566);
		
		Collection c4 = new ArrayList();	
		c4.add("I Love You");
		
//		10.removeAll(Collection coll):�ӵ�ǰ������ɾ��������coll�е�Ԫ�أ��൱��ɾ���������������
		c2.removeAll(c3);//ɾ������c2��c3�еĽ���
		System.out.println("10: " + c2);
		
//		11.equals(Object obj)�ж����������е�����Ԫ���Ƿ���ȫ��ͬ
		boolean b3 = c2.equals(c4);
		System.out.println("11: " + b3);
		
//		12.hashCode():
		System.out.println(c3.hashCode());	
		
		System.out.println();
		
//		13.toArray():������ת��Ϊ����
		Object[] obj = c3.toArray();
//==>����ΪʲôҪת��Object���͵����飿��Ϊc3���������ʲô���͵����ݶ��С�
		for(int i = 0; i < obj.length; i++){
			System.out.println(obj[i]);
		}	
	}
	
	@Test
	public void testCollection2(){
		
		Collection c2 = new ArrayList();	
		c2.add(1234);
		c2.add("����������ã�");
		c2.add("I Love You");
		
//		6.contains(Object obj)���жϼ������Ƿ����ָ����objԪ�أ�������������turn����֮����false��
//		�жϵ����ݣ�����Ԫ���������equals()���������ж�
//		��ȷ��������뼯���е�Ԫ�����Զ���������Ҫ���Զ�����Ҫ��дequals()������
		System.out.println("6_1: " + c2.contains(123));
		c2.add(new Person("�������",26));
		boolean b1 = c2.contains(new Person("�������", 26));
		//��Person���У�equals()����û����д��ʱ�����ﷵ�ص���false����д֮�󷵻�turn��
		System.out.println("6_2: " + b1);
		
//		7.containsAll(Collection coll):�жϵ�ǰ�������Ƿ����coll������Ԫ��
		Collection c3 = new ArrayList();
		c3.add(12345);
		c3.add(new String("����������ã�"));
		c3.add(5566);
		System.out.println("7: " + c2.containsAll(c3));
		
//		8.retainAll(Collection coll):��ǰ������coll�й��е�Ԫ�أ��������������ظ���ǰ���ϡ�
//		ע�⣺�����ǰ������collû�н�������ǰ���ϻᱻ�޸�Ϊ �յļ���[]
		c2.retainAll(c3);
		System.out.println("8: " + c2);
		
//		9.remove(Object obj):ɾ�������е�objԪ�أ���ɾ���ɹ�������turn��ɾ��ʧ�ܣ�����false��
		boolean b2 = c2.remove("����������ã�");
		System.out.println("9_1: " + b2);
		System.out.println("9_2: c2" + c2);
	}
	
	@Test
	public void testCollection1(){
		
		Collection c1 = new ArrayList();//����Ҫ�������ǵİ�
		
//		1.size():���ؼ�����Ԫ�صĸ���
		System.out.println(c1.size());
		
//		2.add(Object obj):�򼯺������һ��Ԫ��
		c1.add(123);//��Ϊadd()����������β���һ���������������123�ǽ����Զ�װ����
		c1.add("�Ұ���");
		c1.add(90.34);
		c1.add(12+90);
		System.out.println(c1.size());
		
//		3.addAll(Collection coll):���β�coll�а���������Ԫ�ض���ӵ���ǰ������
		Collection c2 = Arrays.asList(1,2,3,4,5);
		c1.addAll(c2);
		System.out.println(c1.size());
		
//		4.isEmpty():�ж���������Ƿ�Ϊ��
		System.out.println(c1.isEmpty());
//		������鿴�����е�Ԫ�أ�
		System.out.println(c1.toString());//ArrayList������д��toString()����
		
//		5.clear():��ռ����е�����Ԫ��
		c1.clear();
		System.out.println("5: " + c1.isEmpty());
		System.out.println("5: " + c1.toString());
		
		
	}
}
