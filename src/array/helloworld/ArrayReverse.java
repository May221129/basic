package array.helloworld;
/**
 * ����ķ�ת��ð������
 */
public class ArrayReverse {
	public static void main(String[] args){
		
		int[] arr = new int[]{77,22,33,44,11,55};
		
		/*
		//���ݵķ�ת��
		for(int x = 0, y = arr.length - 1; x < y; x++,y--){
			int temp = arr[x];
			arr[x] = arr[y];
			arr[y] = temp;
		}
		System.out.println(arr.length);
		for(int i = 0; i < arr.length; i++){
			System.out.println(arr[i]);
		}
		*/
		
		//ʹ��ð������ʹ�����е���ֵʵ�ִ�С��������
		boolean flag = true;
		for(int j = 0; j < arr.length && flag; j++){
			flag = false; 
			for(int i = 0; i < arr.length-1-j; i++){
				if(arr[i] > arr[i+1]){
					int temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
					flag = true;
				}
			}
		}
		for(int i = 0; i < arr.length; i++){
			System.out.println(arr[i]);
		}
	}
}
