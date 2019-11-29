package thread.b01JavaConcurrencyInPractic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 4.4.1 Ҫ���һ��"��û�������"����ԭ�Ӳ�������ʵ�ַ�ʽ������չ��Ĺ���
 * ListHelper<E>��ʧ�ܵģ�ListHelper2<E>�ǳɹ��ġ�
 * 
 * @author May
 * 2019��11��28��
 */
public class A04AddAtomicMethod {
	
}

/**
 * ��ͨ�� Collections.synchronizedList(new ArrayList<>()); �õ���list���̰߳�ȫ�ļ��ϣ���list��ȫ�ֶ�����list������Ϊ����
 * ��putIfAbsent(E e) ������ʹ����synchronized����ͬ��������ʹ�õ����ǵ�ǰ��ListHelper���󣬶���list����
 * �ٺ͢ڸ�����û��ʹ��ͬһ���������ԣ������"��û�������"����ԭ�Ӳ���ʧ�ܣ����ɿ��ܳ����̰߳�ȫ���⡣
 * 
 * @author May
 * 2019��11��28��
 */
class ListHelper<E>{
	private List<E> list = Collections.synchronizedList(new ArrayList<>());
	public synchronized boolean putIfAbsent(E e) {
		boolean absent = !list.contains(e);
		if(absent) {
			list.add(e);
		}
		return absent;
	}
}

/**
 * ��ͨ�� Collections.synchronizedList(new ArrayList<>()); �õ���list���̰߳�ȫ�ļ��ϣ���list��ȫ�ֶ�����list������Ϊ����
 * ��putIfAbsent(E e) ������ʹ����synchronized��this����ָ��ʹ�õ�����list����
 * �ٺ͢�ʹ�õ���ͬһ���������ԣ����"��û�������"����ԭ�Ӳ����ɹ���
 * 
 * @author May
 * 2019��11��28��
 */
class ListHelper2<E>{
	private List<E> list = Collections.synchronizedList(new ArrayList<>());
	public boolean putIfAbsent(E e) {
		synchronized(list) {
			boolean absent = !list.contains(e);
			if(absent) {
				list.add(e);
			}
			return absent;
		}
	}
}