package exception.a03exceptiontest;
//Exception�쳣�������ֶ��׳��쳣��
public class TestCircle {
	public static void main(String[] args) {
		Circle c1 = new Circle(2.0);
		Circle c2 = new Circle(3.0);
		Circle c3 = new Circle(3.0);
		Person p = new Person();
		
		int i1 = c1.compareTo(c2);
		System.out.println(i1);
		
		int i2 = c1.compareTo(p);
		System.out.println(i2);
//==>�������c1��p��ȣ�ִ�����ֶ��׳� �쳣֮��������γ���Ͳ�����ִ���ˡ�
		int i3 = c2.compareTo(c3);
		System.out.println(i3);
	}
}
class Circle{
//	���ԣ�
	private double radius;//�뾶
//	��������
	public Circle(){
		super();
	}
	public Circle(double radius){
		this.radius = radius;
	}
//	������
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public int compareTo(Object obj){//�Ƚ�����Բ�Ĵ�С
//		System.out.println("ע��thisԲ�Ƚϴ󣬷���1��thisԲ�Ƚ�С������-1������Բһ���󣬷���0������Ķ�����Բ������-2");
		if(this == obj){
			return 0;
		}else if(obj instanceof Circle){
			Circle c = (Circle)obj;
			if(this.radius == c.radius){
				return 0;
			}else if(this.radius > c.radius){
				return 1;
			}else{
				return -1;
			}
		}else{
//			�ֶ��׳��쳣��
//			throw new RuntimeException("�������������") ;//�����׵�������ʱ�쳣�����Ա���ʱ���ᱨ��
//			�ֶ��׳�һ���Լ��������쳣�ࣺ
			throw new MyException("�������������");
			
//			try {//�ֶ��׳�����ʱ�쳣��������try-catch�ķ�ʽ������
//				throw new Exception("��������� ����");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
}
class Person{
	
}