package collection.c_hashmap;

/**
 * 探究：
 * 	1.HashMap.hash(Object key)源码解析。
 * 	2.为什么要保持equals()和hashCode()的一致性：oop.equalsAndHashCode.A03EqualsAndHashCodeOverwrite
 * 
 * @author May
 * 2019年7月30日
 */
public class A02Method_Hash2 {
	/**
	 * 该方法的逻辑：通过位运算重新计算 hash。为什么要这样做？而不直接用key的 hashCode()方法产生的 hash值？
	 * 答：这样做有两个好处：
	 * 	①让key的hash值的高位数据与低位数据进行异或，以此加大低位数据的随机性，变相的让高位数据参与到计算中。
	 * 	②通过移位和异或运算，可以让 hash 变得更复杂，进而影响 hash 的分布性。
	 * 	详细答案见博客：https://segmentfault.com/a/1190000012926722#articleHeader4的“3.2 查找”。
	 */
	/**
	static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    */
}
