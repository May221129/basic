package oop.toString;
//equals()������toString()��������ϰ
public class TestGeometricObject {
	public static void main(String[] args) {
//		�������������ж�����ɫ�Ƿ���ȣ�
		Circle c1 = new Circle();
		c1.setColor("red");
//		Ҳ���Ե�������������εĹ�������ֱ�����ú�Բ��ĳЩ���ԣ����£�
		Circle c2 = new Circle(30,"red",30);//ͨ�����������뾶����ɫ�����������ú���
		System.out.println(c1.color.equals(c2.color));
//		����equals()�������Ƚ���뾶�Ƿ���ȣ�
		c1.setRadius(20);
		System.out.println(c1.equals(c2));
//		����toString()�����������뾶��
		System.out.println(c1.toString());
		System.out.println(c2.toString());
	}
}

class GeometricObject{//���ࣺ������״
//	���ԣ�
	protected String color;//��ɫ
	protected double weight;//����
//	��������
	protected GeometricObject(){
		super();
//	��ʼ�������color����Ϊ"white"��weight����Ϊ"1.0"
		color = "white";
		weight = 1.0;
	}
	protected GeometricObject(String color, double weight){
		this.color = color;
		this.weight = weight;
	}
//	������
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
}

class Circle extends GeometricObject{//���ࣺԲ
//	���ԣ�
	private double radius;//Բ�İ뾶
//	��������
	public Circle(){
		super();
//		��ʼ�������color����Ϊ"white",weight����Ϊ1.0,
//		��Ϊsuper()�������иպ�������һ��������ֱ�ӵ��ü��ɡ�
		this.radius = this.getRadius();
	}
	public Circle(double radius){
		this.radius = radius;
	}
	public Circle(double radius, String color, double weight){
		super(color,weight);
//		this(1.0);//ע�⣺�����Ǵ���ģ�
//		һ���������У�ֻ�ܵ���һ��super������������һ��this��������������ͬʱ����������
//		��Ϊsuper��������this��������������д�����У�ͬʱ�������������ﲻ�����Ҫ��
		this.radius = radius;
	}
//	������
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double findArea(){//����Բ�����
		return Math.PI * radius * radius;
	}
//�ֶ���дObject���equals()�������Ƚ�����Բ���ӽ��Ƿ���ȣ�������򷵻�true
	public boolean equals(Circle c){
		if(this == c){
			return true;
		}
		if(c instanceof Circle){
			Circle c1 = (Circle)c;
//			�������return������ϵͳ���е�equals()�����е�returnд�ģ�������double���͵�weight��double���͵�radius��
//			return Double.doubleToLongBits(radius) == Double.doubleToLongBits(c1.radius);
			return this.radius == c1.radius;
		}else{
			return false;
		}
	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Circle other = (Circle) obj;
//		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
//			return false;
//		return true;
//		}
//�ֶ���дObject���toString()����������Բ�İ뾶��	
//	public String toString(){
////		return "Circle [radius = " + radius + "]";
////		������double����ת��String���ͣ��漰����װ�࣬������������ʽ��д��
//		return String.valueOf(radius);
//	}
	@Override
	public String toString() {
		return "Circle [radius=" + radius + "]";
	}
}