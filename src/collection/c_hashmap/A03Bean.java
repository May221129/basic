package collection.c_hashmap;

/**
 * ����HashMap������ת�������ʱ����������һ���ࡣ
 * ������ȥ��дhashCode()����һ�������ܵõ�ͬ���Ĺ�ϣֵ���Ӷ�ʵ�ֹ�ϣ��ײ��
 * ͬʱҲ��дequals()������
 * 
 * @author May
 * 2019��8��2��
 */
public class A03Bean {
	protected int number;
	
	public A03Bean(int number) {
		this.number = number;
	}
	
	/**
	 * ��дhashCode()������ֻҪ��4�ı������������Ĺ�ϣֵ������0.
	 */
	@Override
	public int hashCode() {
		return number % 4;
	}
	
	/**
	 * Ҳ������дequals()��������������ϣ��ͻ��ʱ����Ҫ����equals()�����Ƚ����������ʵ�������Ƿ���ͬ��
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		A03Bean other = (A03Bean) obj;
		if (number != other.number)
			return false;
		return true;
	}
}
