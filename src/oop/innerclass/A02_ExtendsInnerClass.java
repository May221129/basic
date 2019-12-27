package oop.innerclass;

/**
 * һ�������μ̳е�һ���ڲ��ࣿ���ڲ�����ò��ü̳У���Ȼ�����ҡ���
 * ��ʹ��ר���﷨����ȷ�����ù����ԡ�
 * 	���ڡ�JAVA���˼�롷�е����ڼ̳�inner classes�������ࣩ�����⣺
 * 	����inner class�Ĺ��캯���������ӵ�һ��referenceָ��outer class �������ϣ�
 * 	���Ե���̳�inner classʱ���������΢����Щ��������ڡ�ָ��outer class���󡱵��Ǹ�����reference���뱻��ʼ����
 * 	��derived class֮�ڲ����ڿ����ӵ�ȱʡ���󡣡�
 * 
 * @author May
 * 2019��12��27��
 */
public class A02_ExtendsInnerClass extends Outer.Inner {//�ڲ��������
	
	//��һ�����ܳɹ��̳�һ���ڲ��ࣺʹ��ר���﷨����ȷ�����ù����ԡ� <----------------------------------------------------------
	A02_ExtendsInnerClass(Outer outer) {
		outer.super();//WithInner��ĸ�����Object����������ΪʲôҪ����д���д��룿
	}
	
	//��������֮main�������ȼ��ء���ʼ��main�������ڵ�InheritInner�࣬���ָ����и��࣬�����ȼ����丸��WithInner.Inner��
	//so������ô����WithInner��ļ��ء���ʼ���ģ�û��WithInner��ʵ�����ڲ���Inner�ִӺζ�����
	public static void main(String[] args) {
		Outer outer = new Outer();
    	new A02_ExtendsInnerClass(outer);
	}
}

class Outer {//�ڲ������ڵ���
	class Inner{//�ڲ���
	}
}
