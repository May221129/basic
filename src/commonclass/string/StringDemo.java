package commonclass.string;
//String类常用的方法的练习：面试题，要求掌握！！！
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringDemo {
	public static void main(String[] args) {
//		测试myTrim()方法：
		String s1 = "   今天要学完Java常用的类      ";
		String s2 = "   ";
		String s3 = myTrim(s1);
		System.out.println(s3);
		String s4 = myTrim(s2);
		System.out.println(s4);
		
		System.out.println();
//		测试myReverseString() 和 myReverseString1()方法：(两个方法差不多)
		String s5 = "ABCDEFG";
		String s6 = myReverseString(s5, 2, 5);
		System.out.println(s6);
		String s7 = myReverseString1(s5, 2, 5);
		System.out.println(s7);
		
		System.out.println();
//		测试getnumber()方法：
		String s8 = "ABCGHJABCHIHRABCKHO";
		String s9 = "AB";
		int i = getnumber(s8, s9);
		System.out.println(i);
		
		System.out.println();
//		测试getMaxSubString()方法：
		String s10 = "ABCHCKHO";
		List<String> l = getMaxSubString(s8, s10);
		System.out.println(l);
		
		System.out.println();
//		测试sort()方法：
		String s11 = sort(s8);
		System.out.println(s11);
	}
//	对字符串中的字符进行自然顺序的排序：
	public static String sort(String s){
		char[] c = s.toCharArray();
		Arrays.sort(c);
		return new String(c);
	}
	
	//获取两个字符串中最大相同子串-方法一:
	public static List<String> getMaxSubString(String str1,String str2){
		String maxStr = (str1.length() > str2.length())? str1 : str2;
		String minStr = (str1.length() < str2.length())? str1 : str2;
		int len = minStr.length();
		List<String> list = new ArrayList<>();
		for(int i = 0;i < len;i++){
			for(int x = 0,y = len - i;y <= len;x++,y++){
				String str = minStr.substring(x, y);
				if(maxStr.contains(str)){
					list.add(str);
				}
			}
			if(list.size() != 0){
				return list;
			}
		}
		return null;
	}
//	获取两个字符串中最大相同子串-方法二-LCS(打印一个二维数组，记录start、end和max):
	
//	获取一个字符串在另一个字符串中出现的的次数,判断s2在s1中出现的次数：
//	如：获取"AB"在"ABCGHJABCHIHRABCKHO"中出现的次数
	public static int getnumber(String s1, String s2){
		int count = 0;
		int len = 0;
		while((len = s1.indexOf(s2)) != -1){
			count++;
			s1 = s1.substring(len + s2.length());
		}
		return count;
	}
	
//  将一个字符串中的指定部分进行反转，如：将"ABCDEFG"反转为"ABFEDCG"。方法二：
//	思路：将字符串分为三部分：①前面不需要反转的部分，②中间需要反转的部分，③后面不需要反转的部分。
	public static String myReverseString1(String s, int start, int end){
//		第一部分：
		String str = s.substring(0, start);
//		第二部分：
		for(int i = end; i >= start; i--){
			str += s.charAt(i);
		}
//		第三部分：
		str += s.substring(end + 1);
		return str;
	}
	
//  将一个字符串中的指定部分进行反转，如：将"ABCDEFG"反转为"ABFEDCG"。方法一：
//	思路：将字符串先转成字符数组，再对字符数组进行反转。
	public static String myReverseString(String s, int start, int end){
		char[] c = s.toCharArray();//字符串-->数组
		return reverseString(c,start,end);
	}
	public static String reverseString(char[] c, int start, int end){
		for(int i = start, j = end; i < j; i++, j--){
			char temp = c[i];
			c[i] = c[j];
			c[j] = temp;
		}
		return new String(c);//数组-->字符串
	}
	
	
//	模拟一个trim方法，去除字符串两端的空格：
	public static String myTrim(String s){
		int start = 0;
		int end = s.length() - 1;
		while(start < end && s.charAt(start) == ' '){
			start++;
		}
		while(start < end && s.charAt(end) == ' '){
			end--;
		}
		return s.substring(start, end+1);
	}
}
