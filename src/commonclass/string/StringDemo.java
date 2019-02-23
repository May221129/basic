package commonclass.string;
//String�ೣ�õķ�������ϰ�������⣬Ҫ�����գ�����
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringDemo {
	public static void main(String[] args) {
//		����myTrim()������
		String s1 = "   ����Ҫѧ��Java���õ���      ";
		String s2 = "   ";
		String s3 = myTrim(s1);
		System.out.println(s3);
		String s4 = myTrim(s2);
		System.out.println(s4);
		
		System.out.println();
//		����myReverseString() �� myReverseString1()������(�����������)
		String s5 = "ABCDEFG";
		String s6 = myReverseString(s5, 2, 5);
		System.out.println(s6);
		String s7 = myReverseString1(s5, 2, 5);
		System.out.println(s7);
		
		System.out.println();
//		����getnumber()������
		String s8 = "ABCGHJABCHIHRABCKHO";
		String s9 = "AB";
		int i = getnumber(s8, s9);
		System.out.println(i);
		
		System.out.println();
//		����getMaxSubString()������
		String s10 = "ABCHCKHO";
		List<String> l = getMaxSubString(s8, s10);
		System.out.println(l);
		
		System.out.println();
//		����sort()������
		String s11 = sort(s8);
		System.out.println(s11);
	}
//	���ַ����е��ַ�������Ȼ˳�������
	public static String sort(String s){
		char[] c = s.toCharArray();
		Arrays.sort(c);
		return new String(c);
	}
	
	//��ȡ�����ַ����������ͬ�Ӵ�-����һ:
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
//	��ȡ�����ַ����������ͬ�Ӵ�-������-LCS(��ӡһ����ά���飬��¼start��end��max):
	
//	��ȡһ���ַ�������һ���ַ����г��ֵĵĴ���,�ж�s2��s1�г��ֵĴ�����
//	�磺��ȡ"AB"��"ABCGHJABCHIHRABCKHO"�г��ֵĴ���
	public static int getnumber(String s1, String s2){
		int count = 0;
		int len = 0;
		while((len = s1.indexOf(s2)) != -1){
			count++;
			s1 = s1.substring(len + s2.length());
		}
		return count;
	}
	
//  ��һ���ַ����е�ָ�����ֽ��з�ת���磺��"ABCDEFG"��תΪ"ABFEDCG"����������
//	˼·�����ַ�����Ϊ�����֣���ǰ�治��Ҫ��ת�Ĳ��֣����м���Ҫ��ת�Ĳ��֣��ۺ��治��Ҫ��ת�Ĳ��֡�
	public static String myReverseString1(String s, int start, int end){
//		��һ���֣�
		String str = s.substring(0, start);
//		�ڶ����֣�
		for(int i = end; i >= start; i--){
			str += s.charAt(i);
		}
//		�������֣�
		str += s.substring(end + 1);
		return str;
	}
	
//  ��һ���ַ����е�ָ�����ֽ��з�ת���磺��"ABCDEFG"��תΪ"ABFEDCG"������һ��
//	˼·�����ַ�����ת���ַ����飬�ٶ��ַ�������з�ת��
	public static String myReverseString(String s, int start, int end){
		char[] c = s.toCharArray();//�ַ���-->����
		return reverseString(c,start,end);
	}
	public static String reverseString(char[] c, int start, int end){
		for(int i = start, j = end; i < j; i++, j--){
			char temp = c[i];
			c[i] = c[j];
			c[j] = temp;
		}
		return new String(c);//����-->�ַ���
	}
	
	
//	ģ��һ��trim������ȥ���ַ������˵Ŀո�
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
