package array.helloworld;
//数组：添加元素
public class A001Array02 {
	public static void main(String[] args){
		
		TestPritimive d = new TestPritimive();
		
		for(int i = 0; i < d.t.length; i++){
			System.out.println(d.t[i]);
		}
		
		d.t[0] = true;
		d.t[1] = true;
		d.t[2] = true;
		
		for(int i = 0; i < d.t.length; i++){
			System.out.println(d.t[i]);
		}
	}
}
class TestPritimive{
	boolean[] t = new boolean[3];
}