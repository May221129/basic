package oop.constructor;
//��������ʹ�ùؼ���this����ϰ��Girl Boy
public class Girl {
	private String name;
	public void setName(String i){
		name = i;
	}
	public String getName(){
		return name;
	}
	public void marry(Boy boy){//�����Ҳ�������Boy���͵�boy
		System.out.println("��Ҫ�޸�" + boy.getName());//Boy���͵�boy������
		boy.marry(this);//˭���������������Ȣ˭���Ƕ�̬�ġ�
	}
}

class Boy{
	private String name;
	private int age;
	public void setName(String i){
		name = i;
	}
	public String getName(){
		return name;
	}
	public void setAge(int i){
		age = i;
	}
	public int getAge(){
		return age;
	}
	public void marry(Girl girl){//ע�⣡����
		System.out.println("��ҪȢ" + girl.getName());
	}
	public void shout(){
		if(age >= 22){
			System.out.println("�ҵ����������");
		}else{
			System.out.println("�һ�û�����䣡");
		}
	}
}
