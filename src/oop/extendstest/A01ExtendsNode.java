package oop.extendstest;

import org.junit.Test;

/**
 * 1.�۵㣺�����Ƿ��ܼ̳и�����������Ժͷ�����
 * 	��Java�����ĵ��Ľ��ͣ������еĹ��з�������(����)������̳��н��ᱻ����̳У�����˽�еĽ����ܱ��̳С�
 * 		��ô�ڼ̳���������β��ܼ̳е������˽�����أ����ǣ�������Ĺ��췽����ͨ��super()�������ø���Ĺ��췽������
 * 	���ܹ������ֹ۵㣬�������https://www.zhihu.com/question/51345942/answer/145388196��
 * 
 * 2.��ʵ�ʱ�̹����У�����Ӧ����ô��������͸�������ԡ������ļ̳����⣺
 * 	�� ���ڸ��������е����ԣ����಻Ҫ��ȥ������ͬ�������ˡ�
 * 		���������Ҳ��Ҫ���ʡ��޸�������ԣ�ֻ���ڸ����н�����������Ϊprotected����������Ҳ�ܼ̳и����ԣ��ҿ��Խ��з��ʺ��޸ġ�
 * 	�� ���ڶ����������ʽ���������ֱַ������÷��͵ĺ�ûʹ�÷��ͣ�ѡ��������Ҫ����������
 * 		Super s = new Subclass();
 * 		Subclass s = new Subclass();
 * 		������
 * 		List list = new ArrayList<>();
 * 		��ô�����ĺô���List��ArrayList�Ľӿڣ�ArrayList�м���
 * 	�� �������Ҫ��д���������е�ĳ�����ԣ���ô����һ��Ҫ���͸�������صķ�������д��
 * 		������Ҫ�޸������Լ����������ʱ��ͨ��������������������ʹ�ö�̬��ʱ��ʵ�ʷ��ʡ��޸ĵĻ��Ǹ�������ԡ�
 * 		�磺test00()��ʾ��
 */
public class A01ExtendsNode {
	/**
	 * ������û�У���������еķ�����
	 */
	@Test
	public void test02(){
		/**
		 * ����һ��B����ֵ������A������ba
		 * ����������еķ�����ba���ܷ��ʡ�
		 */
//		A ba = new B();
//		ba.info();
		
		//����Ҳ���ܷ���������еķ�����
//		A a = new A();
//		a.info();
		
		B b = new B();
		b.info();
	}
	
	/**
	 * �������и�private���ε�����i��ͨ��getJ()���j��ֵ;
	 * ������Ҳ�и�private���ε�����i��ͨ��getJ()���j��ֵ��
	 */
	@Test
	public void test01(){
		A a = new A();
		System.out.println(a.getJ());//11
		
		B b = new B();
		System.out.println(b.getJ());//22
		
		A ba = new B();
		System.out.println(ba.getJ());//22
	}
	
	/**
	 * �������и�public���ε�����i;
	 * ������Ҳ�и�public���ε�����i��
	 * ������һ��������Ϊ���࣬������Ϊ���ࡱ�Ķ���ʱ�����ʡ�������д�˵����ԡ�����ʱ���ʵ��Ǹ�������ԣ���������������д�����ԡ�
	 */
	@Test
	public void test00(){
		B b = new B();
		System.out.println("B b = new B(); ==> " + b.i);//2
		
		A a = new A();
		System.out.println("A a = new A(); ==> " + a.i);//1
		
		A ba = new B();
		System.out.println("B ba = new B(); ==> " + ba.i);//1�������i���ԣ�
	}
}

class A{
	public int i = 1;
	
	private int j = 11;
	public void setJ(int newJ){
		this.j = newJ;
	}
	public int getJ(){
		return j;
	}
	
	protected String name = "����";
	public void setName(String newName){
		this.name = newName;
	}
	public String getName(){
		return name;
	}
}

class B extends A{
	public int i = 2;
	
	private int j = 22;
	public void setJ(int newJ){
		this.j = newJ;
	}
	public int getJ(){
		return j;
	}
	
	public void setName(String newName){
		name = newName;
	}
	public String getName(){
		return name;
	}
	
	public void info(){
		System.out.println("������е�info()������");
	}
}