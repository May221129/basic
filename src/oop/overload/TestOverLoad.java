package oop.overload;
//��������overload����ϰ
public class TestOverLoad {
	public static void main(String[] args) {
		
		//��һ����������Ķ���
		TestOverLoad a = new TestOverLoad();
		
		//�ڶ�����ͨ����Ķ��������÷�����
		a.mOL(2,3);
		
		a.mOL(12);
		
		a.mOL("ɵ��");
		
		a.getMax(2,3);
		System.out.println(a.getMax(2,3));
		
		a.getMax(2.3, 4.5);
		System.out.println(a.getMax(2.3, 4.5));
		
		a.getMax(2.3, 3.4,4.5);
		System.out.println(a.getMax(2.3, 3.4,4.5));
	}
	
	//�������صĴ�����
	private void mOL(int a) {
		System.out.println(a*a);
	}
	
	public void mOL(int a, int b) {
		System.out.println(a*b);
	}

	public void mOL(String a) {
		System.out.println(a);
	}
	//��������������������
	
	//��������������������
	public int getMax(int a, int b){
		int max = (a > b)? a : b;
		return max;
	}
	public double getMax(double a, double b){
		double max = (a > b)? a : b;
		return max;
	}
	public double getMax(double a, double b, double c){
		//д��һ��
//		double temp = (a > b)? a : b;
//		double max = (temp > c)? temp : c;
//		return max;
		//д������
		return (getMax(a,b) > c)? getMax(a,b) : c;//������һ��д�õķ���������c���Ƚ�
	}
}