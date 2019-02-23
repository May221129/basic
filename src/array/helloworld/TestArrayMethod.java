package array.helloworld;
/**
 * 数组中常见的运算，转化为可被引用的方法：
 */
public class TestArrayMethod {
	//求数组中最大值：
	public int getMax(int[] arr){
		int max = arr[0];
		for(int i = 1; i < arr.length-1; i++){
			if(max < arr[i]){
				max = arr[i];
			}
		}
		return max;
	}
	//求数组中最小值：
	public int getMin(int[] arr){
		int min = arr[0];
		for(int i = 1; i < arr.length-1; i++){
			if(min > arr[i]){
				min = arr[i];
			}
		}
		return min;
	}
	//求数组的总和：
	public int getSum(int[] arr){
		int sum = 0;
		for(int i = 0; i < arr.length; i++){
			sum += arr[i];
		}
		return sum;
	}
	//求数组的平均值：
	public int getAvg(int[] arr){
		int sum = getSum(arr);
		int avg = sum / arr.length;
		return avg;
	}
	//遍历数组中的各个元素：
	public void print(int[] arr){
		for(int i = 0; i < arr.length -1; i++){
			System.out.print(arr[i] + ",");
		}
		System.out.println(arr[arr.length - 1]);
	}
	//数组的反转：
	public void reverse(int[] arr){
		for(int i = 0, j = arr.length - 1; i < j; i++, j--){
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}
	//数组的复制：
	public int[] copy(int[] arr){
		int[] arr1 = new int[arr.length];
		for(int i = 0; i < arr.length; i++){
			arr1[i] = arr[i];
		}
		return arr1;
	}
	//使用冒泡排序法对数组进行从小到大的排序：
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
	//使用直接选择排序法对数组进行从大到小的排序：
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
	
	//加法
	public int add(int a, int b){
		return a + b;
	}
}
