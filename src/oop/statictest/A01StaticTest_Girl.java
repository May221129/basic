package oop.statictest;
/**
 * static�ؼ��ֵ���ϰ��
 */
public class A01StaticTest_Girl {
	public static void main(String[] args) {
	Girl g1 = new Girl("С��",23);
	Girl g2 = new Girl("С��",24);
//��������������֤��һ�����������֮��ֱ��Ӱ����һ���ĸ�������������
	g1.beautiful = "����";//ͨ�����������õ��������
	System.out.println(g1.toString());
	System.out.println(g2.toString());
	
	Girl.beautiful = "��Ҫ��Ư����";//ͨ��������õ��������
//�������д���֤��������������ڶ�����ֵģ����Կ���ͨ������ֱ�ӵ������������Ȼ��Ҳ����ͨ�����������������
	System.out.println(g1.toString());
	System.out.println(g2.toString());
	
	g1.beautiful();//ͨ�����������õ����෽��
	Girl.beautiful();//ͨ�����������෽��
	}
}
class Girl{
//	���ԣ�
//	ʵ�����������Ŷ���Ĵ����������ص�
	private String name;
	private int age;
//	�������������Ĵ����������ص�
	static String beautiful;
//	��������
	public Girl(){
		super();
	}
	public Girl(String name, int age){
		this.name = name;
		this.age = age;
	}
//	������
//	ʵ��������
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
@Override
	public String toString() {
		return "Girl [name=" + name + ", age=" + age + 
				", beautiful=" + beautiful + "]";
	}
	//	�෽����
	public static void beautiful(){
		System.out.println(beautiful);
//		System.out.println(name + beautiful);
		System.out.println("Ů���������Ŷ�������");
	}
	public static String getBeautiful() {
		return beautiful;
	}
	public static void setBeautiful(String beautiful) {
		Girl.beautiful = beautiful;
	}
}