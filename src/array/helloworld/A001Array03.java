package array.helloworld;
//数组一旦初始化，其长度是不可变的。
public class A001Array03 {
	public static void main(String[] args){
		
		int[] i = new int[]{1,2,3,4,5};
		
		int[] j = new int[10];
		
		for(int a = 0; a < i.length; a++){
			j[a] = i[a];
		}
		
		j[5] = 6;
		j[6] = 7;
		j[7] = 8;
		
		for(int a = 0; a < j.length; a++){
			System.out.println(j[a]);
		}
		
	}

}
