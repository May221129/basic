package generic.a01helloworld;
//������̳еĹ�ϵ:����A����B�����࣬��ôList<A>�Ͳ���List<B>������/�ӽӿ�.

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class TestGenericAndExtends {
	@Test
	public void testGenericAndExtends(){
//		��û��ʹ�÷���ʱ��String����Object������ࡣ
		Object obj = null;
		String str = "AA";
		obj = str;
		System.out.println(obj);//������ΪAA
		
		List<Object> l1 = null;
		List<String> l2 = new ArrayList<String>();
//		l1 = l2;//�����޷���l2��ֵ��l1;
		
		method1(l1);
//		method1(l2);
		
		method2(l1);
		method2(l2);
	}
	
	public void method1(List<Object> list){
		
	}
	public void method2(List<?> list){//ʹ����ͨ����󣬸÷����;���ͨ���ԡ�
		
	}
	
//	ʹ��ͨ�����
	@Test
	public void testGenericAndExtends1(){
		List<?> l1 = new ArrayList();
		List<String> l2 = new ArrayList<String>();
		l2.add("����÷");
		l1 = l2;
		System.out.println("*" + l1);//�����[����÷]
		
		List<Integer> l3 = new ArrayList<Integer>();
		l3.add(123);
		l1 = l3;
		System.out.println("*" + l1);//�����[123]
		
		List<String> l4 = new LinkedList<String>();
		l4.add("�����");
		l1 = l4;
		System.out.println("*" + l1);//�����[�����]
		
		List<Object> l5 = null;
		
		List<? extends Number> l6 = null;//Number�����������int,long,float,double;
		l6 = l3;//���Դ��Number��������
//		l6 = l5;//l5��Object���͵ģ������Ų��ˣ�����
		
		List<? super Number> l7 = null;
		l7 = l5;//���Դ��Number���丸��
//		l7 = l3;//l3��Integer���͵ģ������Ų��ˣ�
//		l7 = l4;//l4��String���͵ģ��Ǹ��࣬Ҳ�����࣬�����Ų��ˣ�
		
	}
}
