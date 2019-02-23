package commonclass.string;
//实现字符串的反转：你有几种方法？
public class TestString1 {
	public static void main(String[] args) {
//		测试方法一：
		String s = "李光荣，我爱你！";
		String s1 = reverse(s);
		System.out.println(s1);
//		测试方法二：
		String s2 = "12345";
		String s3 = reverse1(s2);
		System.out.println(s3);
//		测试方法三：
		String s4 = "明天就是六一儿童节啦！";
		String s5 = reverse2(s4);
		System.out.println(s5);
	}
//	方法一:
//	先转为StringBuffer，再调用StringBuffer的reverse()方法，最后再转回为String类型：
	public static String reverse(String s){
		StringBuffer sb = new StringBuffer(s);
		sb.reverse();
		String str = sb.toString();
		return str;
	}
//	方法二:
//	先转为字符数组，对字符数组进行反转，再转回为字符串
	public static String reverse1(String s){
		char[] c = s.toCharArray();
		for(int i = 0, j = c.length - 1; i < j; i++, j--){
			char temp = c[i];
			c[i] = c[j];
			c[j] = temp;
		}
		return new String(c);
	}
//	方法三：
//	先创建一个空的StringBuffer对象，再把String中的字符从后往前，一个个添加到StringBuffer中
	public static String reverse2(String s){
		StringBuffer sb = new StringBuffer();
		for(int i = s.length() - 1; i >= 0; i--){
			sb.append(s.charAt(i));//将String中的字符取出，添加到StringBuffer中
		}
		return sb.toString();
	}
//	还有其他的方法:
	
}
