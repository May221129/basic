package array.helloworld;

public class TestArrayMethod1 {
	public static void main(String[] args) {
		
		int[] arr = new int[]{90,2,8,6,40,23,5};
		
		TestArrayMethod au = new TestArrayMethod();
		
		//求数组中最大值：
		au.getMin(new int[]{1,1,1,1});
		
		int a = 1;
		int b = 3;
		au.add(a,b);
		
		au.add(1, 3);
		
		
		int max = au.getMax(arr);
		System.out.println("最大值为：" + max);
		
		//求数组中最小值：
		int min = au.getMin(arr);
		System.out.println("最小值为：" + min);
		
		//求数组的总和：
		int sum = au.getSum(arr);
		System.out.println("数组的和为：" + sum);
		
		//求数组的平均值：
		int avg = au.getAvg(arr);
		System.out.println("平均值为：" + avg);
		
		//遍历数组中的各个元素：
		System.out.println("输出数组中的所有元素：");
		au.print(arr);
		//System.out.println(arr);在这里输出的是引用（是个地址）
		
		//数组的反转：
		System.out.println("数组的反转：");
		au.reverse(arr);
		au.print(arr);
		
		//数组的复制：
		System.out.println("数组的复制：");
		int[] arr1 = au.copy(arr);
		au.print(arr1);
		
		//使用冒泡排序法对数组进行从小到大的排序：
		System.out.println("将数组从小到大排序：");
		int[] arr2 = au.sort(arr);
		au.print(arr2);
		
		
		//使用直接选择排序法对数组进行从大到小的排序：
		System.out.println("将数组从大到小排序：");
		int[] arr3 = au.sort1(arr);
		au.print(arr3);
	}
}
