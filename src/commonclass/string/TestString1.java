package commonclass.string;
//ʵ���ַ����ķ�ת�����м��ַ�����
public class TestString1 {
	public static void main(String[] args) {
//		���Է���һ��
		String s = "����٣��Ұ��㣡";
		String s1 = reverse(s);
		System.out.println(s1);
//		���Է�������
		String s2 = "12345";
		String s3 = reverse1(s2);
		System.out.println(s3);
//		���Է�������
		String s4 = "���������һ��ͯ������";
		String s5 = reverse2(s4);
		System.out.println(s5);
	}
//	����һ:
//	��תΪStringBuffer���ٵ���StringBuffer��reverse()�����������ת��ΪString���ͣ�
	public static String reverse(String s){
		StringBuffer sb = new StringBuffer(s);
		sb.reverse();
		String str = sb.toString();
		return str;
	}
//	������:
//	��תΪ�ַ����飬���ַ�������з�ת����ת��Ϊ�ַ���
	public static String reverse1(String s){
		char[] c = s.toCharArray();
		for(int i = 0, j = c.length - 1; i < j; i++, j--){
			char temp = c[i];
			c[i] = c[j];
			c[j] = temp;
		}
		return new String(c);
	}
//	��������
//	�ȴ���һ���յ�StringBuffer�����ٰ�String�е��ַ��Ӻ���ǰ��һ������ӵ�StringBuffer��
	public static String reverse2(String s){
		StringBuffer sb = new StringBuffer();
		for(int i = s.length() - 1; i >= 0; i--){
			sb.append(s.charAt(i));//��String�е��ַ�ȡ������ӵ�StringBuffer��
		}
		return sb.toString();
	}
//	���������ķ���:
	
}
