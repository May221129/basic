package array.helloworld;
/**
 * �����г��������㣬ת��Ϊ�ɱ����õķ�����
 */
public class TestArrayMethod {
	//�����������ֵ��
	public int getMax(int[] arr){
		int max = arr[0];
		for(int i = 1; i < arr.length-1; i++){
			if(max < arr[i]){
				max = arr[i];
			}
		}
		return max;
	}
	//����������Сֵ��
	public int getMin(int[] arr){
		int min = arr[0];
		for(int i = 1; i < arr.length-1; i++){
			if(min > arr[i]){
				min = arr[i];
			}
		}
		return min;
	}
	//��������ܺͣ�
	public int getSum(int[] arr){
		int sum = 0;
		for(int i = 0; i < arr.length; i++){
			sum += arr[i];
		}
		return sum;
	}
	//�������ƽ��ֵ��
	public int getAvg(int[] arr){
		int sum = getSum(arr);
		int avg = sum / arr.length;
		return avg;
	}
	//���������еĸ���Ԫ�أ�
	public void print(int[] arr){
		for(int i = 0; i < arr.length -1; i++){
			System.out.print(arr[i] + ",");
		}
		System.out.println(arr[arr.length - 1]);
	}
	//����ķ�ת��
	public void reverse(int[] arr){
		for(int i = 0, j = arr.length - 1; i < j; i++, j--){
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}
	//����ĸ��ƣ�
	public int[] copy(int[] arr){
		int[] arr1 = new int[arr.length];
		for(int i = 0; i < arr.length; i++){
			arr1[i] = arr[i];
		}
		return arr1;
	}
	//ʹ��ð�����򷨶�������д�С���������
	public int[] sort(int[] arr){
		boolean flag = true;
		for(int i = 0; i < arr.length && flag; i++){
			flag = false;
			for(int j = 0; j < arr.length - 1 - i; j++){
				if(arr[j] > arr[j+1]){
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp; 
					flag = true;
				}
			}
		}
		return arr;
	}
	//ʹ��ֱ��ѡ�����򷨶�������дӴ�С������
	public int[] sort1(int[] arr){
		for(int i = 0; i < arr.length; i++){
			for(int j = i + 1; j < arr.length; j++){
				if(arr[i] < arr[j]){
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr;
	}
	
	//�ӷ�
	public int add(int a, int b){
		return a + b;
	}
}
