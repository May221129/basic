package oop.extendstest;
//��ļ̳���ϰ��-1
public class Kids extends Mankind{
	public static void main(String[] args) {
		Kids someKid = new Kids();
		someKid.yearsOld = 26;
		someKid.printAge();
//		someKid.sex = 1; �����е�˽�����ԣ��������п��Ա���ȡ�����ǲ���ֱ�ӵ��á�
		someKid.setSex(0);
		someKid.setSalary(10000);
		someKid.manOrWomen();
		someKid.employeed();
	}
//	����
//	���ԡ�����
	private int yearsOld;
	
	public int getYearsOld() {
		return yearsOld;
	}

	public void setYearsOld(int yearsOld) {
		this.yearsOld = yearsOld;
	}

	public void printAge(){
		System.out.println("yearsOld : " + yearsOld);
	}
	public void employeed(){//��������д�����Ǹ���Mankind�е�employeed����
		System.out.println("Kids should study and no job.");
	}
}
