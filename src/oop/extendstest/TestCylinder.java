package oop.extendstest;
//��ļ̳���ϰ��-2
//����
public class TestCylinder {
	public static void main(String[] args) {
		
//		����Cylinder�Ķ���cylinder
		Cylinder cylinder = new Cylinder();
		
		cylinder.setRadius(3);//����Բ���ײ��İ뾶
		
		cylinder.setLength(10);//����Բ���ĳ���
		
//		cylinder.findArea();Բ���ײ����,����Բ���������ʱ���Ѿ�����Բ���ײ�����ˣ������ⲽ����ʡ�ԡ�
		
		cylinder.findVolume();//Բ�������
		System.out.println("cylinder�ı����Ϊ��" + cylinder.findArea());//���Բ���ı����
		
//		���ø����� public Cylinder(double length) ���������
		Cylinder c = new Cylinder(45);
		System.out.println(c.getRadius() + " , " + c.getLength());//ͨ�����������Ƿ�����Ƿ���ȷ
	}
}