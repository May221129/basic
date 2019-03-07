package array.whiletest;
//标签label的使用：
public class TestLabel {
	public static void main(String[] args) {
		label:for(int j = 0; j < 10; j++){
			for(int i = 1; i < 10; i++){
				if(i % 4 == 0){
					continue label;
				}
				System.out.println(i);
			}
			System.out.println("bbbbb");
		}
		System.out.println("aaaa");
	}
}
