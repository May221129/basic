package array.helloworld;
/**
 * 数组的反转：冒泡排序
 */
public class ArrayReverse {
	public static void main(String[] args){
		
		int[] arr = new int[]{77,22,33,44,11,55};
		
		/*
		//数据的反转：
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
		
		//使用冒泡排序使数组中的数值实现从小到大排序：
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
