package oop.polymorphism;
//��̬����ϰ
public class TestGeometricObject {//������
//	���
	public static void main(String[] args) {
		TestGeometricObject t = new TestGeometricObject();//���õ��ǵ�ǰTestGeometricObject����ķ���

		Circle c1 = new Circle(3.0, "��ɫ", 6.0);//����һ��Բ
		Circle c2 = new Circle(3.0, "��ɫ", 6.0);//����һ��Բ		
		
		MyRectangle m1 = new MyRectangle(5, 6, "��ɫ", 10);//����һ������
		
		t.displayGeometricObject(m1);//��ô������Σ�����
//==>ǰ��Ҫ�ȴ���һ��Բ����.������Ƕ�̬�����á���GeometricObject g = new Circle(3.0, "��ɫ", 6) ��
//	   ��ֵ���̣�new Circle(3.0, "��ɫ", 6)��ֵ��g, c�ٸ�ֵ������������o
		
		boolean b = t.equalsArea(c1, c2);//����GeometricObject�ж��������������Ƿ���ȣ�����ֵ��Boolean���͵�b
		System.out.println(b);//ͨ����ֵ��Boolean���͵�b�����ܴ�ӡ������Ϊ����ļ�飡����
	}
//	������
	public boolean equalsArea(GeometricObject g1, GeometricObject g2){//�ж��������������Ƿ����
//ע��==>1����Ȼ���жϣ��Ǿ���booblean���͵ķ������ͣ�
//ע��==>2���������Σ�д��������GeometricObject�Ķ����ܴ��ҵ�һ���͵ڶ�������ζ������Ƚ������������������ֶ�̬�Ե�Ӧ�á�
//		public boolean GeometricObject(Circle c, MyRectangle m)Ҳ�ǿ��Եģ�ֻ�ǹ涨������ε�һ��дʲô���ڶ���дʲô��
//		д��һ��
//		if(g1.findArae() == g2.findArae()){
//			return true;
//		}else{
//			return false;
//		}
//		д������
		return g1.findArae() == g2.findArae();
	}
	public void displayGeometricObject(GeometricObject g){//��ʾ��������
		System.out.println("�ö�������Ϊ�� " + g.findArae());
//ע�⣺==>������Щ�����Բ���д��������Ϊ���õ�������Ը���findArea��������д�������ǵ������������еķ���������
//		if(g instanceof Circle){
//			Circle c = (Circle)g;
//			System.out.println("��Բ�ε����Ϊ�� " + c.findArea());
//		}
//		if(g instanceof MyRectangle){
//			MyRectangle m = (MyRectangle)g;
//			System.out.println("�þ��ε����Ϊ�� " + m.fidArae());
//		}
	}
}
class GeometricObject{//���ࣺ������״
//	���ԣ�
	protected String color;//��ɫ
	protected double weight;//����
//	��������
	protected GeometricObject(){//ע�⣺ÿ�������У���ö�Ҫ����һ���ղεĹ�����
		
	}
	protected GeometricObject(String color, double weight){
		this.color = color;
		this.weight = weight;
	}
//	������
	public String getColor(){//��ȡ��ɫ
		return color;
	}
	public void setColor(String color){//������ɫ
		this.color = color;
	}
	public double getWeight(){//��ȡ����
		return weight;
	}
	public void setWeight(){//��������
		this.weight = weight;
	}
	public double findArae(){//�󼸺���״�����
//==>ע�⣺��Ŀ�в�û���ṩ�����󼸺���״������ķ��������Կ�����Ĭ�Ϸ���0������
		return 0;
	}
}
class Circle extends GeometricObject{//���ࣺԲ��
//	���ԣ�
	private double radius;//�뾶
//	��������
	public Circle(double radius, String color, double weight){
		super(color,weight);
		this.radius = radius;
	}
//	������
	public double getRadius() {//��ȡԲ�İ뾶
		return radius;
	}
	public void setRadius(double radius) {//����Բ�İ뾶
		this.radius = radius;
	}
	public double findArae(){//����Բ�����==>ע�⣺����Ҫ��Ҫ����Σ�����
		return Math.PI * radius * radius;
	}
}
class MyRectangle extends GeometricObject{//���ࣺ����
//	���ԣ�
	private double width;//��
	private double height;//��
//	��������
	public MyRectangle(double width, double height, String color, double weight){
		super(color, weight);
		this.width = width;
		this.height = height;
	}
//	������
	public double getWidth() {//��ȡ���εĿ�
		return width;
	}
	public void setWidth(double width) {//���þ��εĿ�
		this.width = width;
	}
	public double getHeight() {//��ȡ���εĸ�
		return height;
	}
	public void setHeight(double height) {//���þ��εĸ�
		this.height = height;
	}
	public double findArae(){//������ε����
		return width * height;
	}
}