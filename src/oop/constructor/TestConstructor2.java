package oop.constructor;
//����������ϰ��
public class TestConstructor2 {
	public static void main(String[] args) {	
//		����һ��ʹ�ù����������������ε����
		TriAngle ta = new TriAngle(2,9);
		System.out.println(ta.getArea());;	
//		��������ֱ�ӵ��÷��������������ε����
//		TriAngle ta = new TriAngle();//û��������ʽ�Ĺ�������������Ĭ�Ϲ�����
//		ta.setBase(4);
//		ta.setHeight(3);
//		System.out.println("�������ε����Ϊ��" + ta.getArea());
		
	}
}
class TriAngle{
//	���ԣ�
	private double base;//�ױ߳�
	private double height;//��
//	��������
	public TriAngle(double base,double height){
		this.base = base;//this.��ʾ��ǰ���󣬺����base��ʾ�����β�
		this.height = height;
	} 
//	������
	public void setBase(double base){
		this.base = base;
	}
	public double getBase(){
		return base;
	}
	public void setHeight(double height){
		this.height = height;
	}
	public double getHeight(){
		return height;
	}
//	���������ε������ǰ���Ѿ�д�����øߡ��ױߵķ����ˣ����Կ���ֱ����ǰ�����õĸߺ͵ױ����������������
	public double getArea(){
		return (this.base * this.height) / 2;
	}
}