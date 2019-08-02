package collection.c_hashmap;

/**
 * ̽����HashMap.hash()�����У������ִ��˳��
 * 
 * @author May
 * 2019��7��25��
 */
public class A02Method_Hash {
	public static void main(String[] args) {
		System.out.println(A02Method_Hash.show(18));//18
		System.out.println(A02Method_Hash.hash(18));
		
		System.out.println("-------------------------");
		A02Method_Hash.weiYi();
	}
	
	/**
	 * ��h = key.hashCode();                        h=18
	 * �ڡ�h >> 1���ļ��������ȸ�ֵ��һ����ʱ������         temp=9
	 * �����Ԥ�㣻                                                                     27
	 */
	static final int hash(Object key) {//��hash()��������Ϊ�˷�����Ը�д�˵ģ�����HashMap��Դ�롣
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
