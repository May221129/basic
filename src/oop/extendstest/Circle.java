package oop.extendstest;
//��ļ̳���ϰ��-2-Բ
//����
public class Circle {
//	����
	private double radius;
//	������
	public Circle(){
		System.out.println("���Ǹ���Circle�ĿյĹ�����");
		radius = 1;//���뾶��ʼ��Ϊ1
	}
	public Circle(double radius){
		this.radius = radius;
	}
//	����
	public void setRadius(double radius){//����Բ�İ뾶
		this.radius = radius;
	}
	public double getRadius(){//��ȡԲ�İ뾶
		return radius;
	}
	public double findArea(){//��Բ�����
		System.out.println("Circle'area is : " + Math.PI * radius *radius);
		return Math.PI * radius *radius;	
	}
}
