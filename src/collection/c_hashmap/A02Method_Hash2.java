package collection.c_hashmap;

/**
 * ̽����
 * 	1.HashMap.hash(Object key)Դ�������
 * 	2.ΪʲôҪ����equals()��hashCode()��һ���ԣ�oop.equalsAndHashCode.A03EqualsAndHashCodeOverwrite
 * 
 * @author May
 * 2019��7��30��
 */
public class A02Method_Hash2 {
	/**
	 * �÷������߼���ͨ��λ�������¼��� hash��ΪʲôҪ������������ֱ����key�� hashCode()���������� hashֵ��
	 * ���������������ô���
	 * 	����key��hashֵ�ĸ�λ�������λ���ݽ�������Դ˼Ӵ��λ���ݵ�����ԣ�������ø�λ���ݲ��뵽�����С�
	 * 	��ͨ����λ��������㣬������ hash ��ø����ӣ�����Ӱ�� hash �ķֲ��ԡ�
	 * 	��ϸ�𰸼����ͣ�https://segmentfault.com/a/1190000012926722#articleHeader4�ġ�3.2 ���ҡ���
	 */
	/**
	static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    */
}
