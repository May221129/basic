package array.whiletest;
//while��ϰ��100���ڵ�ż���������
public class TestWhile {
	public static void main(String[] args){
		int i = 0;
		int sum = 0;
		while(i < 100){
			if(i % 2 == 0){
				System.out.println(i);
				sum +=i;
			}
			i++;
		}
		System.out.println("100���ڵ�ż�����ܺ�Ϊ" + sum);
	}
}
