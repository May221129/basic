package oop.argstransfer;
//�����е�ֵ����:ͨ�����÷�������ʽ����������������ֵ
public class A02ArgsTransferTest {
	public static void main(String[] args) {
		A02ArgsTransferTest f = new A02ArgsTransferTest();
		DataSwap ds = new DataSwap();
		f.Swap(ds);
	}
	public void Swap(DataSwap ds){ //����һ��������������ֵ�ķ���
		int temp = ds.i;
		ds.i = ds.j;
		ds.j = temp;
		System.out.println("ds.i:" + ds.i + "\t" + "ds.j:" + ds.j);
	}
}
class DataSwap{ //����������
	int i = 2;
	int j = 3;
}
