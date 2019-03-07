package collection.c_treemap;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import org.junit.Test;

/**
 * ���ԣ�
 * һ��TreeMap����Ȼ����
 * ����TreeMap�Ķ�������
 * �������������Ȼ�����ַǶ���������Զ������put��TreeMap���������ģ�
 * 
 * TreeMap��������ӽ�Map�е�Ԫ�ص�key��ָ�����Խ��������磺keyΪPerson���󣬰���Person�����name����ֵ�������򣩣�Ҫ��key������ͬһ����Ķ���
 * ����������Ȼ���򣬻��Ƕ���������compareTo()����ȥ�Զ��尴��������Խ�������ʱ��������1.����.compareTo(����2.����)�������CompareToʵ������
 * 	String������������͵İ�װ������д��CompareTo()������������Ķ��������еĴ�����ʾ����
 * 
 * 1.TreeMap��ʲô��
 * 		TreeMap��һ�ú��������һ���Ž�TreeMap�е�Ԫ�ػ�ռһ�����ڵ㣻�ڶ���Ԫ�طŽ�TreeMapǰ���Ⱥ͵�һ��Ԫ�ؽ��бȽϣ�
 * 	�ڶ���Ԫ������С��ŵ�һ��Ԫ�ص����½ǣ����ϴ���ŵ�һ��Ԫ�ص����½ǡ������ÿһ��Ԫ�طŽ������ظ����϶�����ֱ��Ԫ�ط�
 * 	������ĩ��Ҷ�ӽڵ�����·������·����������̾���һ�������Ĺ��̡�
 *  	�������̿��ܳ���һ�ֱȽϼ��˵�����������ս���һ�������磺TreeMap����put��ȥһ��5����putһ��6,������5����ڵ�
 * 	�����½ǣ���putһ��7��������6�ڵ�����½ǣ���put��8���������ս������һ��5-6-7-8����һ����������������������
 * 	��ʧȥ�˺�������ٲ��ҵ����ƣ�������Ĳ����ٶ�Ϊlog2 N����Ϊ�˱����������������������������ת����ת��Ŀ������������
 * 	����������ԡ���
 * 	����ϸ�˽���������ɿ���https://baijiahao.baidu.com/s?id=1561409115312135&wfr=spider&for=pc��
 * 
 * 2.TreeMap��ô��������ģ�
 *   TreeMap��Ԫ�ص���������putʱ��Ԫ�ؽ��бȽϵģ��ȽϵĹ������£�
 *   	2.1  ���put��ȥ��Ԫ����ʵ����Comparable�ӿڣ���Person����ΪԪ�ط���TreeMap��������д��compareTo()������
 *   		��ôTreeMap��put��Ԫ��ʱ���ͻ����Ԫ�ص�compareTo()��������Ԫ�ؽ������򣻣�������Ȼ����
 *   	2.2  ���put��ȥ��Ԫ��û��ʵ��Comparable�ӿڣ�����Comparator�����������Ϊ�ֲ�����������Comparatorʵ���й涨��
 *   		put��TreeMap�е�Ԫ�ص����ԵıȽϹ��򣬲���Comparator��ʵ������Treeman�Ĺ��캯����Ϊ��Σ���ʱ����TreeMap
 *   		��put�������͵�Ԫ��ʱ���ͻ��Զ�����Comparator����Ƚ�����Ԫ�ؽ��бȽϡ���������������
 *   	2.3 String���װ�඼��ʵ��Comparable�ӿڡ�����дCompareTo()�������������put��ȥ������Щ���͵�Ԫ�أ����
 *   		��������д��CompareTo()�����������򡣣��Ƚ�Ԫ��ת��Unicode�ַ����Ķ�����ֵ�����������һ��Ԫ�صĶ�����ֵ��
 *   		�ڶ���Ԫ�صĶ�����ֵ�����Ϊ������˵����һ��Ԫ�صĶ�����ֵ���ڵڶ���Ԫ�صģ�С�Ķ�����ֵ��ǰ�棬�����ǵڶ���Ԫ����
 *   		��һ��Ԫ��ǰ�棻���Ϊ�������ǵ�һ��Ԫ�����ڵڶ���Ԫ�ص�ǰ�棻���Ϊ����˵������Ԫ����ͬ��Ĭ���ǰ���Unicode�ַ�
 *   		���Ķ�����ֵ�Ĵ�С����˳��������򡣣�
 *   	2.4  ���put��ȥ�����Զ��������͵�Ԫ��(��User��)����û��ʵ��Comparable�ӿڣ�Ҳû����TreeMap���캯���������
 *   		����Comparator�Ƚ��������룺TreeMap�ǽ�User���͵�Ԫ�����hashCode��������hashCodeֵ��������ġ�
 *   		��testTreeMap3()���ԡ�
 *   
 * 3.String����ôͨ��CompareTo()������������ģ�ͨ������ֱ��˵��
 * 	public int compareTo(String anotherString) {
 * 		
 * 		// 3.1 ��ȡ�����ַ����ĳ��ȣ�ȡ�̵��Ǹ����������������ѭ��������
 *       int len1 = value.length;
 *       int len2 = anotherString.value.length;
 *       int lim = Math.min(len1, len2);
 *       
 *       // 3.2 �������ַ����ֱ�ֵ��v1��v2������char���飺
 *       char v1[] = value;
 *       char v2[] = anotherString.value;
 *
 *		// 3.3 ��v1��v2������char�����е�Ԫ�أ���һ���бȶԣ�
 *       int k = 0;
 *       while (k < lim) {
 *           char c1 = v1[k];
 *           char c2 = v2[k];
 *   		// ��ȡ��������Ԫ�ز���ͬ��������Ԫ��ֱ�ӽ������(ʵ���ǽ�����Ԫ�ص�Unicode������ֵ���������)��
 *   		// �����Ϊ������˵��c1�Ķ�����ֵ����c2��,ֵС����ǰ�棬����c2����c1ǰ�棻
 *   		// �����Ϊ������˵��c1�Ķ�����ֵС��c2�ģ�����c1����c2��ǰ�棻
 *   		// �����Ϊ0��˵��c1=c2���������ǲ�����ֵ�������������Ϊ��"c1 != c2"�Ż�"return c1 - c2".
 *           if (c1 != c2) {
 *               return c1 - c2;
 *           }
 *           k++;
 *       }
 *       // 3.4 ���ͨ��3.3����v1��v2�ַ������������һ�ȶԹ���ÿ��Ԫ�ض���ȣ���ֱ�ӽ������ַ������������
 *       // ������"�Ұ���"��"�Ұ��㰡"�������򣬶̵��Ǹ��ַ�������ǰ�棬��Ϊ���Ķ�����ֵ��С�ڳ��ַ�����
 *       return len1 - len2;
 *   }
 *   
 * TreeMap�����ַ��
 * https://www.cnblogs.com/hxsyl/p/3331095.html
 * http://www.jb51.net/article/90537.htm
 */
public class TestTreeMap {
	/**
	 * һ����������
	 * 	1. ���������ʵ�֣����ı���Ҫ��������������ṹ��
	 * 		1.1 ����������Comparator��Ϊ�ֲ���������Comparatorʵ����ʵ��compare()������
	 * 		1.2 ��compare()�����У��Զ��巽����ε����Ե��������
	 * 		1.3 ����TreeMap���󣬲���TreeMap�Ĺ��캯���д����Զ�������������Comparator���������
	 * 		1.4 ÿ��put��TreeMap��һ������(�ö���Ϊ��Comparator�������Զ���������������͵Ķ���)��
	 * 			TreeMap�����Զ����õ�Comparator�����compare()��������֤put��ȥ�Ķ���������ġ�
	 * 	2. ���ʣ� ��Ҫ��������Զ������У��Ƿ���Ҫ��дequals()������hashCode()������Ϊʲô��
	 * 		TreeMap���ǰ���hashCode����ţ����ǰ���ʵ�ֵ�Comparable�ӿڵ�compareTo()�������洢�ģ�
	 * 		ֻҪcompareTo�ķ��ؽ��Ϊ0�ͱ�ʾ����������ȣ���ô�ʹ治��ȥ�������󣬺�put�ľͰ�ǰ��ĸ��ǵ�����
	 * 		�����Ƕ�������дequasls��hashCode��������ֻ��Ҫʵ��Comparable�ӿ�����дcomparareTo����
	 * 		�����ˣ��������ǲ��ܱ�֤��Ӧ���в����õ�HashMap�����Ա������õ�ϰ�ߣ������Ƕ�����һ������֮��ϰ���Ե�
	 * 		��дequals��hashCode������
	 */
	@Test
	public void testTreeMap1(){
		Comparator<Object> com = new Comparator<Object>(){
			public int compare(Object o1, Object o2) {
				if(o1 instanceof Student && o2 instanceof Student){
					Student s1 = (Student)o1;
					Student s2 = (Student)o2;
					//�������д����compareTo()��������ʵ��String��CompareTo()����
					int i = s1.getName().compareTo(s2.getName());
					if(i == 0){
						//�������д����compareTo()��������ʵ��Integer��װ���CompareTo()����
						return s1.getAge().compareTo(s2.getAge());
					}else{
						return i;
					}
				}
				return 0;
			}
		};
		TreeMap<Student, Integer> t = new TreeMap<Student, Integer>(com);
		t.put(new Student("A", 26), 66);
		t.put(new Student("A", 21), 90);
		t.put(new Student("C", 26), 80);
		t.put(new Student("C", 23), 70);
		t.put(new Student("E", 23), 91);
		Set<Entry<Student, Integer>> s = t.entrySet();
		for(Object obj : s){
			Entry<Student, Integer> entry = (Entry)obj;
			System.out.println(entry);
		}
	}
	
	/**
	 * ������Ȼ����
	 * �������Person�ࡿ
	 * ע�⣺
	 * 	�����ַ�������Ҳ�ǰ���ÿ�������ַ���Ӧ��Unicode������ֵ�Ĵ�С�������򣬶��ǰ���ÿ�������ַ�������ĸ��a~z����
	 * 	��ΪUnicode������룬¼�������ַ���ȥʱ����û�а����»��ֵ����͵�˳���ÿ�������ַ���������¼�롣
	 */
	@Test
	public void testTreeMap2(){
		TreeMap<Person, Integer> tm = new TreeMap<>();
		tm.put(new Person("D", 23), 70);
		tm.put(new Person("E", 26), 66);
		tm.put(new Person("A", 21), 90);
		tm.put(new Person("��", 23), 70);
		tm.put(new Person("��", 26), 66);
		tm.put(new Person("��", 21), 90);
	//	���������е�����entry�ԣ�
		Set<Entry<Person, Integer>> s = tm.entrySet();
		for(Object o : s){
			Entry<Person, Integer> e = (Entry)o;
			System.out.println(e);
		}
	}
	
	/**
	 * �������������Ȼ�����ַǶ���������Զ������put��TreeMap���������ģ�
	 * 	���뵽TreeMap�е�Ԫ�ص��࣬������ʵ����Comparable�ӿڣ�����TreeMap�Ĺ��췽���У����봫��Comparator�Ƚ�����
	 * 	����ᱨ��ClassCastException���쳣��
	 * 	String�Ͱ�װ�඼��ʵ��Comparable�ӿڲ�ʵ��CompareTo()������
	 */
	@Test
	public void testTreeMap3(){
		TreeMap<User,Integer> userTreeMap = new TreeMap<>();
		userTreeMap.put(new User("A",11), 1);
		userTreeMap.put(new User("C",33), 3);
		userTreeMap.put(new User("B",22), 2);
		
		Set<Entry<User,Integer>> userSet = userTreeMap.entrySet();
		for(Entry<User,Integer> userEntry: userSet){
			System.out.println(userEntry.getKey() + "==>" + userEntry.getValue());
		}
	}
	
	/**
	 * char���͵��ַ�������Ϊint���ͣ���Ϊchar���Ϳ��Ը���Unicode�ַ���תΪint���͡�
	 */
	@Test
	public void testChar(){
		/**
		 * char�ַ��͵�a,����תΪint���ͣ������������
		 */
		char a = 'A';
		int i = a;
		System.out.println(i);//65
		//��������Ƿ�����д����
		System.out.println(a + 0);//65
		//��������������д����
		System.out.println(a & 0xffff);//65
	}
}
