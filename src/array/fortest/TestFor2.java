package array.fortest;
//forѭ����ϰ���й�ˮ�ɻ�����ˮ�ɻ�������λ���ڣ���153=1*1*1+5*5*5+3*3*3����
public class TestFor2 {
	public static void main(String[] args){//��1000�������е�ˮ�ɻ����ĸ���
		for(int i = 100; i < 1000; i++){
			int d = i / 100;
			int e = (i % 100) / 10;
			int f = i % 10;
			if(i == d*d*d + e*e*e + f*f*f){
				System.out.println("ˮ�ɻ�����:" + i);
			} 
		}
		
		System.out.println("-----------------------------------------");
		
		for(int i = 100; i < 1000; i++){//��100~999֮���ˮ�ɻ����ĸ���
			int a = i / 100;
			int b = (i - (a * 100)) / 10;
			int c = i - (a * 100) - (b * 10);
			if(i == a*a*a + b*b*b + c*c*c){	
				System.out.println("100~999֮���ˮ�ɻ�����" + i);
			}
		}
	}
}
