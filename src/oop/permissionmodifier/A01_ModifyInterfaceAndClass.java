package oop.permissionmodifier;

/**
 * ���ʣ��ĸ�Ȩ�����η�����Щ�����������νӿڡ�
 * ��public��ȱʡ���ԣ�protected��private�����ԡ�
 * 
 * ���ʣ�Ϊʲô�ĸ�Ȩ�����η��У�ֻ����public��ȱʡ�����νӿڣ�
 * ��
 * 	��protectedΪʲô���У�
 * 		��������Ҫ��ȷprotected�����ã���protected���εĶ��������ֶΡ��������������ڲ���ͬһ�����С������������ط������Է��ʵ���
 * 		����ν�ġ����ࡱ��Ҳ���Է��ʣ����ǽ��ڡ�ͬһ�������͡���������֮���һ����Χ��������ͬһ���������ֲ��������������еĵط������Է��ʡ�
 * 		������һ���ӿڻ�һ������ԣ�����ڵ�λ�þ�ֻ�С��Ƿ���ͬһ�����������������Ƿ���Է����������Ƕ����ֶΡ��������Եģ�
 * 	��privateΪʲô���У����һ���ӿڿ�������Ϊ˽�еģ��Ǿ���ζ�ű��˶��޷����������������ڵ�������ʲô��
 * 
 * @author May
 * 2019��12��26��
 */
public interface A01_ModifyInterfaceAndClass {// ��
	
}

//protected interface A01_ModifyInterface1{// ��
//	
//}

interface A01_ModifyInterface2{// �� ���ӿڲ�д�������η�����ȱʡ��
	
}

//default interface A01_ModifyInterface3{// ��
//	
//}

//private interface A01_ModifyInterface4{// ��
//	
//}

interface A01_ModifyInterface5 extends A01_ModifyInterfaceAndClass{
	
}