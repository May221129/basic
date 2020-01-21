package string.a01String;

import org.junit.Test;

/**
 * http://silencegg.iteye.com/blog/2069267
 * 
 * 1:�����ַ�����Ϊ��ʡ�ڴ�
 * 
 * 2:jdk1.6�ľ����ַ�������permgen����(permgen�й̶���С,������intern�ַ���,�±���), 
 * 		�Դ�jdk1.7�����ַ����Ͱ�ҵ��˶���. �Ͳ����±�����.
 * 
 * 3:jdkʵ�־����ַ��������ݽṹ��һ������hashmap�Ľṹ.  ֻ����Ͱ��С����С��-XX:StringTableSize.
 * 		�ɲ���-XX:StringTableSize����. jdk1.7�е�Ĭ�ϴ�С��1009, jdk1.8�е�Ĭ�ϴ�С��25-50k.
 * 		ע�����ֵ���Ϊ����, �����hashMap��ע����һ����.
 * 
 * 4:��Ϊ���ֵ����������, �������Ҫ�õ��ܶ��intern�ַ���, Ч�ʻ�һֱ������ȥ. ÿ��Ͱ��ŵ�Ԫ��Խ��Խ��.
 * 		���Ծ���Ҫʵ���������-XX:StringTableSize������Ч��
 * 		ע�⻹����������, ����Ǻ����ᵼ��hash��ͻ���ʱ���, ����Ч��.
 * 5:String  intern   StringBuilder  StringBuilder��ע���
 * 		https://www.cnblogs.com/huaxingtianxia/p/6021960.html
 * 		������ʹ����ǶԵ�,���Ƕ���һЩϸ���ǲ��Ե�. �ɼ�����Ĳ���:testString1 testString2
 * 
 * 6:������;��ǶԵ����������.http://blog.csdn.net/raintungli/article/details/38595573
 * 		��intern�������ܽ�:
 * 			���������ֵͬ��Unicode�ַ����Ѿ������StringTable�У���ô�÷�������StringTable�������ַ����ĵ�ַ��
 * 			�����StringTable��û����ֵͬ���ַ��������Լ��ĵ�ַע�ᵽStringTable��
 * 		ע��:String a = new String("aaa");
 * 			��һ�䷴����һ��ʵ���Ͽ�����������:
 * 				String test = "aaa";
 * 				String a = new String(test);
 * 			��Ϊtest�Ѿ���StrinTable����, a������һ����������:sout(a == a.intern) ��false;
 * 			
 * 			��ʱ�����ǻ�һ�ַ�ʽ:
 * 				char[] test = {'a','a','a'};  
 *				String s1 = new String(test);  
 *				System.out.println(s1 == s1.intern());//true
 *				��Ϊtestֻ��һ��chat����, û��StringTable��, s1ֻ�Ƕ��е�һ������, ����s1.intern()��ʱ��ᷢ��Stringtable��ûֵ.
 *				���Խ��Լ������÷ŵ���Stringtable��, ��true.
 *
 *		����һ��testString1��testString2
 *			testString1:
 *				�õ�a3��ʱ����Ȼ����StringBuilder��toString����,Ҳ����new��һ��String. ����ǰ��û��һ����������"ab".
 *				����StringTable�ǿյ�. ��a4="ab"��ʱ��, ��ʱ��StringTable�ų�����һ����"ab"������.
 *				����.��һ��Ϊfalse.
 *			testString2:
 *				������a3.intern(), ������ʱ��StringTable�е�"ab"���þ���a3.
 *				��ʱ����ִ��String a4 = "ab";  �ͻ���StringTable���ҵ�"ab", Ȼ��a3��ֵ����a4. ����true.
 * 7����ʵӦ���Լ�����Ҳ����ʵ�������Ļ���.
 * 		������hashMap.  �в�����WeakHashMap��. ����˵�ڴ�������intern���ı�.��֪��Ϊʲô. 
 * 		Ӧ������Ϊ�Լ�������String������ж��������.
 * 
 * 8:String a = "aaa";
 * 	����ֻ�Ǽ򵥵Ĵ��ַ��������õ�һ���ַ���������.
 * 	�������û�еĻ�, ��ô�ͻ��ڳ�������һ��.
 * @author lgr
 *
 */
public class A03InternString {
	/**
	 * String a3 = a1 + a2;
	 * �ײ㣺
	 * 	���Զ�����һ��StringBuilder����Ȼ������append()��"a"��"b"��������toString�����õ�"ab"�ַ�����
	 * 	��"ab"�ַ������Ƿ����ַ��������أ����Ƿ��ڶ��С�
	 */
	@Test
	public void testString1(){
		String a1 = "a";
		String a2 = "b";
		String a3 = a1 + a2;//!!!
		String a4 = "ab";
		System.out.println(a3 == a4);//false
	}
	
	/**
	 * System.out.println(a3 == a4);//true
	 * ���������������ô�ó����ģ�����
	 * a3.intern()�����ַ�����������û��"ab"�ַ������������Դ�ʱa3�Ὣͨ����String a3 = a1 + a2;���õ���"ab"���Ƶ��ַ����������С�
	 */
	@Test
	public void testString2(){
		String a1 = "a";
		String a2 = "b";
		String a3 = a1 + a2;
		System.out.println(a3 == a3.intern());//true
		
		String a4 = "ab";
		System.out.println(a3 == a4);//true
		System.out.println(a3 == a4.intern());//true
	}
	
	@Test
	public void testString3(){
		char[] test = {'k','v','i','l','l'};  
		String s1 = new String(test); 
		System.out.println(s1 == s1.intern());//true
	}
	
	/**
	 * ע��"aaa"����intern����һ������aaa����ֱ�����ɵ��ַ����������С�
	 * @author lgr
	 */
	@Test
	public void testString4(){
		char[] test = {'a','a','a'};  
		String s1 = new String(test);  
		System.out.println(s1 == "aaa");//false
	}
	
	/**
	   * ʵ��֤������δ������������ֱ�����, �ǻ�������ӽ��(cccddd)��intern�ַ�����
	   *     ͬʱҲ��ֱ�����ccc, ddd���ַ����� ��testString7���Եõ��Ľ����
	   *     
	   * ����������������ַ�����ӣ� ��������˵�� ����ccc����ddd���� ����stringBuilder���� stringBuilder��toString֮��Ķ���
	   *     �����������ӽ��(eeefff)��intern�ַ�����  ��testString7�������ý����
	   */
	  @Test
	  public void testString5() {
	      String str1 = "ccc" + "ddd";
	      
	      String str2 = new String(str1);
	      
	      System.out.println(str2.intern() == str1);//true
	      
	  }
	  
	  /**
	   * �Ա�testString5.  ������testString5
	   */
	  @Test
	  public void testString6() {
	      String s1 = "eee";
	      String s2 = "fff";
	      String s3 = s1 + s2;
	      String s4 = new String(s3);
	      
	      
	      System.out.println(s4.intern() == s3);//false
	  }
	  
	  /**
	     * �Ա�testString5.  ������testString5
	     */
	  @Test
	  public void testString7() {
	      String s1 = "ggg" + "hhh";
	      
	      String s2 = new String(new char[] {'g', 'g', 'g'});
	      
	      System.out.println(s2.intern() == s2);//true
	  }

}
