package oop.innerclass;
//�ڲ����֪ʶ-�ֲ��ڲ���
public class TestInnerClass1 {

}
class OutClass{
//	��������ʹ�÷�ʽ�Ƚϲ�������
	public void method1(){
		class InnerClass{
			
		}
	}
//	�����Ǿֲ��ڲ��ೣ����ʹ�÷�ʽ��
//	����ʹ��һ��������ʹ�䷵��ֵΪĳ�����ӿڵĶ��󣬶�������ӿ��ڷ����ڲ�������
//	ʹ�÷�ʽһ��������Ķ���
	public Comparable getComparable(){
		//1.����һ��ʵ��Comparable�ӿڵ���
		class MyComparable implements Comparable{
			@Override
			public int compareTo(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
		}
		//2.����һ��ʵ����Ķ���
		return new MyComparable();
	}
//	ʹ�÷�ʽ����������Ķ���
	public Comparable getComparable1(){
		return new Comparable(){//�����ʵ�ֽӿڵ�һ�������࣬���ǽӿڵĹ��������ӿ���û�й������ģ�
			@Override
			public int compareTo(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}
}