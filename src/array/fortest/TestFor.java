package array.fortest;
//forѭ������ϰ��:
import org.junit.Test;

public class TestFor {
	@Test
	public void test1(){//��0~100֮�����������ĺ�
		int sum = 0;
		for(int i = 1; i <= 100; i += 2){
			//sum = i;
			sum += i;
		}
		System.out.println("0~100֮�����������ĺ�Ϊ" + sum);
	}
	
	@Test
	public void test2(){//��0~100֮�����������ĺ�
		int sum = 0;
		for(int i = 0; i <= 100; i++){
			if(i % 2 == 1){
				sum += i;	
			}	
		}
		System.out.println("0~100֮�����������ĺ�Ϊ" + sum);
	}
	
	@Test
	public void test3(){//��100����ż�����ܺͼ�ż�����ܸ���
		int sum = 0;
		int count = 0;
		for(int i = 0; i < 100; i++){
			if(i % 2 == 0){
				sum += i;
				count++; 
			}
		}
		System.out.println("100����ż�����ܺ�Ϊ" + sum);
		System.out.println("100���ڵ�ż���ܹ���" + count + "��");
	}
	
	@Test
	public void test4(){//��1~100֮��������7�����������ĸ�����1~100֮��������7�������������ܺ�
		int count = 0;
		int sum = 0;
		for(int i = 1; i <= 100; i++){
			if(i % 7 == 0){
				count ++;
				sum += i;
			}
		}
		System.out.println("1~100֮��������7�����������ĸ�����" + count + "��");
		System.out.println("1~100֮��������7�������������ܺ�Ϊ" + sum );
	}
}
