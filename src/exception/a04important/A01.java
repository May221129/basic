package exception.a04important;

import java.util.ArrayList;
import org.junit.Test;

public class A01 {
	
	/**
	 * test01()：遍历数组，通过try-catch停止循环（非正规手段来解决数组边界问题）：72毫秒。
	 * test02()：通过正常的增强型for循环来遍历数组:24毫秒。
	 * 结论：不要为了正常的控制流而使用异常，不然效率可能会更低，且有可能影响正常的bug处理。
	 */
	@Test
	public void test01(){
		long start = System.currentTimeMillis();
		try{
			int i = 0;
			while(true){
				System.out.println(getArray().get(i));
				i++;
			}
		}catch(IndexOutOfBoundsException e){
			
		}finally{
			long end = System.currentTimeMillis();
			System.out.println("end - start = " + (end - start));//72
		}
	}
	@Test
	public void test02(){
		ArrayList<Integer> arrayList = getArray();
		long start = System.currentTimeMillis();
		for(Integer element : arrayList){
			System.out.println(element);
		}
		long end = System.currentTimeMillis();
		System.out.println("end - start = " + (end - start));//24
	}
	
	public ArrayList<Integer> getArray(){
		ArrayList<Integer> array = new ArrayList<>();
		for(int i = 0; i < 1000; i++){
			array.add(i);
		}
		return array;
	}
}
