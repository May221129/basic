package oop.extendstest;
//��ļ̳���ϰ��-2-Բ��
//����
public class Cylinder extends Circle{
	private double length;//����
//	������
	public Cylinder(){
//		super();û�н�����ʽ���ø�����ĳһ������ʱ�������еĿղεĹ�������Ҳ���ɻ���ʽ��ȥ���ø����еĿղεĹ�������
//		super(123);����ʽ�ĵ��ø����е�ĳһ������
		length = 1;//�����ȳ�ʼ��Ϊ1
	}
	public Cylinder(double length){
		super(33);
		this.length = length;
	}
	public void setLength(double length){//����Բ���ĳ���
		this.length = length;
	}
	public double getLength(){//��ȡԲ���ĳ���
		return length;
	}
	
//	��Բ���ı����������һ��
//	public double findArea(){//��д����Circle�е���Բ����ķ�������Ϊ��Բ���ı������أrr * 2 + 2أrh
//		return Math.PI * this.getRadius() * this.getRadius() * 2
//				+ 2 * Math.PI * this.getRadius() * length;
	
//	��Բ���ı��������������
	public double findArea(){//��д����Circle�е���Բ����ķ�������Ϊ��Բ���ı������أrr * 2 + 2أrh
		return super.findArea() * 2 + 2 * Math.PI * this.getRadius() * length;
		
	}
	public double findVolume(){//����Բ�������
		System.out.println("Cylinder'volnme is : " + Math.PI * this.getRadius() * this.getRadius() * length);
//		return Math.PI * radius *radius * length;//�����е�radius��protectedʱ������ֱ�ӵ���
		return Math.PI * this.getRadius() * this.getRadius() * length;//���Ҳ����д�ɣ�return super.findArae * length;
	}
}
