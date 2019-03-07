package array.helloworld;
//数组：
public class A001Array01 {
	public static void main(String[] args) {
		int[] arr = new int[]{11,22,33,44,55};
		int max = 0;
		int min = 0;
		int sum = 0;
		int mean;
		for(int i = 0; i < arr.length; i++){
			sum += arr[i];
		}
		for(int i = 0; i < arr.length; i++){
			max = (max > arr[i])? max : arr[i];
		}
		
		/*
		for(int i = 0; i < arr.length - 1; i++){
			arr[i + 1] = (arr[i] < arr[i+1])? arr[i] : arr[i+1];
		}
		*/
		
		min = arr[0];	
		for(int i = 0; i < arr.length - 1; i++){
			min = (arr[i] < min)? arr[i] : min;
		}
		
		mean = sum/5;
		
		System.out.println("该数组的最大值为：" + max);
		System.out.println("该数组的最小值为：" + min);
		System.out.println("该数组的总和为：" + sum);
		System.out.println("该数组的平均值为：" + mean);
		
		//依据数组arr 复制一个数组arr2，并修改arr2数组:
		int [] arr2 = new int[arr.length];
		for(int i = 0; i < arr.length; i++){
			if(i % 2 == 0){
				arr2[i] = i;
			}else{
				arr2[i] = arr[i];
			}
			System.out.print( arr2[i] + "\t");
		}
	}

}
