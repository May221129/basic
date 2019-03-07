package oop.interfacetest;
/**
 * �ӿڵ���ϰ��
 */
public class A04Exercise_Circle {
	public static void main(String[] args) {
		ComparableCircle c1 = new ComparableCircle(2.1);
		ComparableCircle c2 = new ComparableCircle(3.0);
		ComparableCircle c3 = new ComparableCircle(3.0);
		
		int i1 = c1.compaereTo(c2);
		System.out.println(i1);
		int i2 = c2.compaereTo(c3);
		System.out.println(i2);
	}
}

/**
 * �ӿڣ�дһ������ķ�������"����"ȥʵ��
 */
interface CompareObject{
	int compaereTo(Object obj);//�Ƚ���������Ĵ�С
}

/**
 * ���ࣺԲ
 */
class Circle{
	protected double radius;//�뾶
//	��������
	public Circle(){
		super();
	}
	public Circle(double radius){
		super();
		this.radius = radius;
	}
//	������
	public void setRadius(double radius){
		this.radius = radius;
	}
	public double getRadius(){
		return radius;
	}
}

/**
 * ����һ��ComparableCircle���࣬����̳�Circle�ࣺ
 */
class ComparableCircle extends Circle implements CompareObject {
	public ComparableCircle(double radius){
		super(radius);
	}
	public int compaereTo(Object obj){//�Ƚ�����Բ�Ĵ�С:������ֵ��0��������ȣ���Ϊ����������ǰ����󣻸���������ǰ����С��
		System.out.println("ע�⣺������ֵ��0��������ȣ���Ϊ����������ǰ����󣻸���������ǰ����С��");
		if(this == obj){
			return 0;
		}else if(obj instanceof ComparableCircle){
			ComparableCircle c = (ComparableCircle)obj;
			if(this.radius > c.radius){
				return 1;
			}else if(this.radius < c.radius){
				return -1;
			}else{
				return 0;
			}
		}else{
			throw new RuntimeException("����Ĳ���ComparableCircle�Ķ��󣬲��ܽ��бȽϣ�");
		}
	}
}