package oop.object;

/**
 * ֤����Object��������ĳ��ࡱ��
 * 
 * ֤��һ�������ģ�
 * ֤�ݶ����������������͵Ķ��󶼿���ָ��Object���á�
 * 
 * @author May
 * 2019��12��17��
 */
public class A01_ObjectIsTheSuperClassOfAllClasses {
	
	/**
	 * ֤��һ��ObjectNoteĬ���䶥��������Object�����Կ�����дObject�еķ�����
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}
