package collection.c_hashmap;

/**
 * ̽����
 * 1.HashMap.hashCode()�����ࡣ
 * 2.ΪʲôҪ����equals()��hashCode()��һ���ԣ�oop.equalsAndHashCode.A03EqualsAndHashCodeOverwrite
 * 
 * @author May
 * 2019��7��25��
 */
public class A02MethodHashCode {
	public static void main(String[] args) {
		System.out.println(A02MethodHashCode.show(18));//18
		System.out.println(A02MethodHashCode.hash(18));
		
		System.out.println("-------------------------");
		A02MethodHashCode.weiYi();
	}
	
	/**
	 * ��h = key.hashCode();                        h=18
	 * �ڡ�h >> 1���ļ��������ȸ�ֵ��һ����ʱ������         temp=9
	 * �����Ԥ�㣻                                                                     27
	 */
	static final int hash(Object key) {
        int h;
        return (h = key.hashCode()) ^ (h >> 1);
    }
	
	static int show(Object i) {
		return i.hashCode();
	}
	
	/**
	 * ��newCap = oldCap;
	 * ��newCap << 1;
	 */
	static void weiYi() {
		int oldcap = 2;
		int newCap;
		System.out.println(newCap = oldcap << 1);//4
		System.out.println("newCap = "  + newCap);//4
		System.out.println("oldcap = " + oldcap);//2
	}
}
