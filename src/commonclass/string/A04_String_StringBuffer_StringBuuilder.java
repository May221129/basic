package commonclass.string;

import org.junit.Test;

/**
 * 
 * һ��String��StringBuffer��StringBuilder������֮��ıȽϣ�
 * 		��ƪ����˵�ú���ϸ�ˣ�https://www.cnblogs.com/huaxingtianxia/p/6021960.html
 * 
 * �����й�String������4��͵�5���У����漰��String Pool�е�HashMap��������ַ��������ﲢδ�����˽⡣��
 * 	1.��String�����������жϣ�ʹ��equals()�������������� == ��
 * 	2.String�ǳ����������һ��������Ͼ��޷��ı䣻��ʹ�� + ƴ���ַ���ʱ���������µ�String���󣬶�������ԭ�е�String������׷�����ݣ�
 * 	3.String Pool(�ַ�����)��
 * 	4.String s = "aaa"; (��������ֵ��ʽ��ֵ)
 * 		��1������String Pool�е�HashMap�����Ƿ����"aaa"�ַ����ļ�¼��
 * 			��������ڣ�
 * 				��2�����ڶ��д���һ��"aaa"����
 *				��3������HashMap���д���һ��"aaa"��¼��keyΪͨ��"aaa"�ַ�����Unicodeֵ�����Ψһ���(������ôͨ��Unicode�ַ�
 *					����Ӧ��Unicodeֵ��������Ψһֵ����û�����˽�)��valueΪ���е�"aaa"��������ã�
 *				��4��Ȼ�󽫶������"aaa"����ĵ�ַ�����������������ñ���s������s��ָ����е�"aaa"�ַ�������
 * 			������ڣ�
 * 				��2����ֱ�ӽ�HashMap����"aaa"�ַ�����¼��valueֵ��ָ����Ǹ����е�"aaa"���󸳸�s���á�(��ΪHashMap�м�Ȼ����"aaa"
 * 					��¼���ǡ�aaa"��¼��valueֵ��һ����ָ����е�"aaa"�������ԼȲ�������HashMap���д���"aaa"��¼��Ҳ�����ڶ�
 * 					�д���"aaa"������)
 * 	5.String s = new String("aaa");
 * 		��1������String Pool�е�HashMap�����Ƿ����"aaa"�ַ����ļ�¼��
 *			������ڣ�
 *				��2�����ڶ��д���һ��"aaa"�ַ�������Ȼ���������ĵ�ַ����s���ã�����s��ָ���˶��д��������"aaa"�ַ��������ˡ�
 * 			��������ڣ�
 * 				��2�����ڶ��д���һ��"aaa"����
 *				��3������HashMap���д���һ��"aaa"��¼��keyΪͨ��"aaa"�ַ�����Unicodeֵ�����Ψһ�����valueΪͨ����2��������
 *					���е�"aaa"��������ã�
 *				��4�����ڶ��д���һ��"aaa"�ַ�������Ȼ���������ĵ�ַ����s���ã�����s��ָ���˶��еڶ��δ��������"aaa"�ַ��������ˡ�
 * 
 * �����й�String�ľ����ַ�����
 * 	1 String Pool��ʵ��������ʱ�������д�����һ�������ַ�����
 * 	2 String s = "haha"; s���Ǿ����ַ��������ã�������ַ������е�ͬ�����ø�ֵ��s;
 * 	3   �� new String("haha"); ���ڶ��д���һ��String���󣬻����ٷ���ȥ������ʱ�������д���һ�������ַ�����
 * 	4 String s = new String ("haha"); s.intern(); ��intern()�����õ����ַ����ľ����ַ����Ķ�������ã�
 * 		�������ʱ��������û�и��ַ������Ǿ��½�һ��������Ѿ����ˣ���ֱ�Ӱ����ô���s.
 */
public class A04_String_StringBuffer_StringBuuilder {
	
	/**
	 * 1.�Ƚ�����ֱ��������String
	 * 2.�� == ���Ƚ�����String�����Ƿ���ȣ�
	 * 3.��equals()�������Ƚ�����String�����Ƿ���ȣ�
	 */
	@Test
	public void testString(){
		String i = "��";
		String j = "��";
		System.out.println(i == j);//true
		System.out.println(i.equals(j));//true
		
		String z = new String("��");
		System.out.println(i == z);//false
		System.out.println(i.equals(z));//true
	}
	
	/**
	 * �����漰������˵���й�String�����֪ʶ��������û��͸
	 * 	˵���ַ����Ƿ���String Pool�еġ���һ˵������׼ȷ��String Pool��һ��HashMap���ݽṹ�ı�
	 * 		1. "String a = "��";"��һ�д���ĵײ㣺
	 * 			a�Ǿ����ַ��������ã�����ַ�����������"��"��ͬ�����ø�ֵ��a;
	 * 			�������ʱ�ַ�����������û�С�������һ�����ַ�ʱ�����½�һ�����ٽ����ø�ֵ��a;
	 * 			��"��"�ַ�������Unicodeֵ����ŵ�String Pool��Ͱ�е�ĳ��λ�á���
	 * 		2. "String b = "Ƥ";"Ҳ�� 1 ��ͬ��
	 * 		3. "String c = a + b;":
	 * 			�� StringBuilder temp = new StringBuilder(a)��
     *			�� temp.append(b);
     *			�� c = temp.toString();
	 */
	@Test
	public void testAddString(){
		String a = "��";
		String b = "Ƥ";
		
		String c = a + b;
		System.out.println(c);
		
		String d = b + a;
		System.out.println(d);
		System.out.println(c == d);//false
		
		String e = new String("��Ƥ");
		System.out.println(c == e);//false
		System.out.println(c.equals(e));//true
	}
	
	/**
	 * �ۼ�
	 */
	@Test
	public void testStringBuffered(){
		
		/**
		 *  String������String������"+"�����Ƚ� ��
		 * 	�ֲ�����s��ss��ŵ���������ͬ�ľ����ַ�������ĵ�ַ��Ȼ���ͨ����������������ɡ�+���ӡ���
         *		1��StringBuilder temp = new StringBuilder(s)��
         *		2��temp.append(s);
         *		3��ss = temp.toString();
		 */
		String s = "hello";
		String ss = "world";
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++){
			ss = s + ss;
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);//298
		
		System.out.println("----------------------------------");
		
		String s1 = "hello";
		StringBuffer sb1 = new StringBuffer(100000);
		startTime = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++){
			sb1.append(s1);
		}
		endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);//0
		
		System.out.println("----------------------------------");
		
		String s2 = "hello";
		StringBuffer sb2 = new StringBuffer();
		startTime = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++){
			sb2.append(s2);
		}
		endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);//1
	}
	
	@Test
	public void testAddString2(){
		
		String a = "hello";
		String b = "world";
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++){
			String c = a + b;
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);//4
		
		System.out.println("-------------------------------");
		
		/**
		 * �ַ������
		 * "hello"+"world"�ڱ���׶ξ��Ѿ������������γ���һ���ַ�������"helloworld"����ָ����еľ����ַ�������
		 * 	����ʱֻ��Ҫ��"helloworld"ָ��ľ����ַ��������ַȡ��1W�Σ�����ھֲ�����str�У����Բ���Ҫʲôʱ�䡣
		 */
		String str = "";
		startTime = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++){
			str = "hello" + "world";
		}
		endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);//0
	}
}
