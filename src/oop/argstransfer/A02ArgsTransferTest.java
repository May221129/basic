package oop.argstransfer;
//方法中的值传递:通过调用方法的形式，来交换两个数的值
public class A02ArgsTransferTest {
	public static void main(String[] args) {
		A02ArgsTransferTest f = new A02ArgsTransferTest();
		DataSwap ds = new DataSwap();
		f.Swap(ds);
	}
	public void Swap(DataSwap ds){ //创建一个交换两个数的值的方法
		int temp = ds.i;
		ds.i = ds.j;
		ds.j = temp;
		System.out.println("ds.i:" + ds.i + "\t" + "ds.j:" + ds.j);
	}
}
class DataSwap{ //创建两个数
	int i = 2;
	int j = 3;
}
