package oop.wrapperclass;
//���ڰ�װ�����ϰ�����ü���Vector����ѧ���ɼ���ص����㣺
import java.util.Scanner;
import java.util.Vector;//���ϣ�������Աȣ��������ڴ�������ݸ���������ʼ������
public class TestScore {
	public static void main(String[] args) {
//1������Scanner�Ķ�����ʾ�Ӽ�������ѧ���ĳɼ����Ը������������������
		Scanner s = new Scanner(System.in);
		System.out.println("������ѧ���ĳɼ����Ը��������������");	
//2������һ������ѭ�����Ӽ������λ�ȡѧ���ĳɼ�����������Vector = new Vector()�����Ķ���v�У�
		Vector v = new Vector();//���ϣ����ڴ���ѧ���ɼ�
		int max = 0;//��¼��߷�
		for(;;){//����ѭ��
			int score = s.nextInt();//��������ѧ���ɼ�
			if(score < 0){
				break;//�Ը��������������
			}
//3���������������ĳɼ��е���߷֣�
			if(max < score){
				max = score;
			}
			Integer score1 = new Integer(score);//��������������ת�ɰ�װ��
			v.addElement(score1);//¼�뼯��v��
		}
//4��ͨ��ѭ�����жϲ����ÿ���ɼ��ĵȼ�������߷����10������==>A; 20������==>B; 30������==>C; ����==> D;
		for(int i = 0; i < v.size(); i++){
//5��ͨ��v.elementAt(i)���λ�ȡ֮ǰ����v�е�Ԫ��
			Integer score = (Integer)v.elementAt(i);
			char level;//���ڽ��յȼ�
			if(max - score <= 10){
				level = 'A';
			}else if(max - score <= 20){
				level = 'B';
			}else if(max - score <= 30){
				level = 'C';
			}else{
				level = 'D';
			}
			System.out.println("ѧ���ɼ�Ϊ�� " + score + ", �ȼ�Ϊ�� " + level);
		}
	}
}
