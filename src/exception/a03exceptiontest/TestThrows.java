package exception.a03exceptiontest;
//Exception�쳣�������ֶ��׳��쳣��
public class TestThrows {
	public static void main(String[] args) {
		method1();
		
		Circle1 s1 = new Circle1(2.0);
		Circle1 s2 = new Circle1(2.0);
		try {
			System.out.println(s1.compareTo(s2));
			System.out.println(s1.compareTo(new String("���ͣ�")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			System.out.println("�Ҳ����Ҳ��ܣ��Ҿ���Ҫִ��~");
		}
	}
	public static void method1(){
		try {
			int i = 10;
			System.out.println(10/0);;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
class Circle1{
//	���ԣ�
	private double radius;//�뾶
//	��������
	public Circle1(double radius){
		this.radius = radius;
	}
//	�������Ƚ�����Բ�Ĵ�С
	public int compareTo(Object obj) throws Exception{//�ֶ��׳��ı���ʱ�쳣����������
//		System.out.println("ע��thisԲ�Ƚϴ󣬷���1��thisԲ�Ƚ�С������-1������Բһ���󣬷���0������Ķ�����Բ������-2");
		if(this == obj){
			return 0;
		}else if(obj instanceof Circle1){
			Circle1 c = (Circle1)obj;
			if(this.radius == c.radius){
				return 0;
			}else if(this.radius > c.radius){
				return 1;
			}else{
				return -1;
			}
		}else{
//			�ֶ��׳��쳣-����ʱ�쳣��
			throw new Exception("��������� ����");
		}
	}
}