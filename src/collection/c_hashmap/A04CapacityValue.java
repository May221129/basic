package collection.c_hashmap;

/**
 * 探究2：为什么HahsMap的容量一定为2的幂？
 * 	答：一个元素放置到哪个桶中，是通过计算“元素的key的哈希值 % 桶数”得到的余数来确定的。
 * 		当容量一定是2^n时，h & (length - 1) == h % length，它俩是等价不等效的，
 * 		因为计算机不太擅长做取模运算，但却非常擅长做位运算，且效率极高，测试见：/important/src/Algorithm/BitAndModulus.java
 * 	
 * 	补充：求余运算：a / b = c …… R;  R = a - bc;  如：1 % 10 == 1 ，是因为 1 / 10 = 0,   余数 = 1 - 10*0 = 1
 * 	博客：https://blog.csdn.net/a_long_/article/details/51594159
 * 
 * @author May
 * 2019年7月26日
 */
public class A04CapacityValue {
	public static void main(String[] args) {
		int a = 1, b = 16;//2^4
		yu(a, b);
		wei(a, b);
		System.out.println("---------------------------");
		
		b = 5;//非2的次方
		yu(a, b);
		wei(a, b);
	}
	//取模运算：
	public static void yu(int hashCode, int capacity) {
		System.out.println(hashCode % capacity);
	}
	//位运算
	private static void wei(int hashCode, int capacity) {
		System.out.println(hashCode & (capacity-1));
	}
}
