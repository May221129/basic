package oop.method;
//��ϰ�⣺�����ĵ��á���������Ƕ�׷�����
public class TestMethod {
	public static void main(String[] args) {
		Car c = new Car();
		//���÷�����
		//c.setName("����");
		//c.getName("����");
		//c.setWheel(4);
		//c.getWheel(4);
		//c.info();
		
		//�������ԣ�
		c.name =  "����";
		c.wheel = 4;
		c.info();
		
		Factory f = new Factory();
		
		//���÷���һ��
		Car c2 = f.produceCar();
		f.describeCar(c2);
		
		//���÷�������
		Car c3 = f.produceCar("·��", 4);
		f.describeCar(c3);
	}
}
class Factory{
	//���쳵(����������������������)
	//����һ
	public Car produceCar(){//����ֵ�� �� ������һ�����������������쳵
		return new Car();
	}
	//������
	public Car produceCar(String name,int wheel){//����ֵ�ǳ�������һ�����������������쳵������� �������֡���̥����
		Car c = new Car();
//	���÷�����
		c.setName(name);
		c.getName();
		c.setWheel(wheel);
		c.getWheel();
//	�������ԣ�
//		c.name = name;
//		c.wheel = wheel;
		
		return c;
	}
	//����������Ϣ��
	public void describeCar(Car c){
		c.info();
	}
}

class Car{
	//����
	String name;
	int wheel;
	//����
	public void setName(String n){
		this.name = n;
	}
	public String getName(){
		//this.name = name;�������֣���ȡ����д��һ��������Ҳ�ǿ��Եģ����Ǿ�����������һд����ģ�黯�����������Ժ�ĳ�����������Ľ����޸ġ�
		return name;
	}
	public void setWheel(int wheel){
		this.wheel = wheel;
	}
	public int getWheel(){
		return wheel;
	}
	public void info(){
		System.out.println("name:" + name + ";" + "wheel:" + wheel);
	}
}