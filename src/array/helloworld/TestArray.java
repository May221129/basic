package array.helloworld;
/**
 * ������ϰ�⣺
 * �Ӽ�������ѧ���ɼ����ҳ���߷֣������ѧ���ɼ��ȼ���
 * �ɼ� >= ��߷�-10�֣��ȼ�ΪA;
 * �ɼ� >= ��߷�-20�֣��ȼ�ΪB��
 * �ɼ� >= ��߷�-30�֣��ȼ�ΪC��
 * ����                                   �ȼ�ΪD��
 * ��ʾ���ȶ���ѧ��������������������int���飬���ѧ���ɼ���
 * 1.����Scanner�Ķ��󣬲��Ӽ��̻�ȡѧ���ĸ���n��
 * 2.���������ѧ������n������һ������Ϊn��int�����飻
 * 3.һ�δӼ��̻�ȡn��ѧ���ĳɼ�����������Ӧ������Ԫ�أ�����ȡn��ѧ���е���߷֣�
 * 4.����ѧ���ɼ������飬������ѧ���ɼ�����߷ֵĲ�ֵ��������Ӧ�ĵȼ����������
 */
import java.util.Scanner;
public class TestArray {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		System.out.println("������ѧ���ĸ���");
		int score = s.nextInt();
		int[] j = new int[score];
		System.out.println("����������ѧ���ĳɼ�");
		for(int i = 0; i < j.length; i++){
			j[i] = s.nextInt();
		}
		int max = 0;
		for(int i = 0; i < j.length; i++){
			max = ( j[ i ] > max) ? j[ i ] : max;
		}
		System.out.println("��߷�Ϊ��" + max);
		String level;
		for(int i = 0; i < j.length; i++){
			if( max - j[i] <= 10){
				level = "A";
				//System.out.println(j[i] + "�֣��ȼ�ΪA");
			}else if( max - j[i] <= 20){
				level = "B";
				//System.out.println(j[i] + "�֣��ȼ�ΪB");
			}else if( max - j[i] <= 30){
				level = "C";
				//System.out.println(j[i] + "�֣��ȼ�ΪC");
			}else{
				level = "D";
				//System.out.println(j[i] + "�֣��ȼ�ΪD");
			}
			System.out.println("student" + i + " score is " + j[i] +"��grade is " + level);
		}
	}
}
