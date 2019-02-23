package collection.e_collections;
/**
 * ��������(Colletion��Map)�Ĺ����ࣺColletions
 * ���ã�Collections.synchronizedList(list���͵ļ���)��ͨ����һ��������list���͵ļ��ϳ�Ϊ�̰߳�ȫ�ļ��ϡ�
 * ͬ������map��set����Ҳ��һ����
 * 
 * �Ѵ���ȥ�ļ��Ͻ��а�װ��һ�£�����ȥ�ļ�������Ϊ��Ա�����ġ���new�����ļ��ϣ�Ҳʵ���˺ʹ���ȥ�ļ��ϵ�����һ���Ľӿڣ��������Ƕ���ͬ���ķ�����
 * 
 * ��List���͵�a2Ϊ����
 * Ϊʲô������Collections.synchronizedList(a2);������֮�󣬷��صļ��Ͼ����̰߳�ȫ�����أ�
 * 1.synchronizedList()��������a2������SynchronizedRandomAccessList��SynchronizedList��
 * 	������SynchronizedRandomAccessList��SynchronizedList�Ķ���
 * 		public static <T> List<T> synchronizedList(List<T> list) {
        	return (list instanceof RandomAccess ?
                new SynchronizedRandomAccessList<>(list) :
                new SynchronizedList<>(list));
    	}
 * 2.list instanceof RandomAccess ?��������false��new��new SynchronizedList<>(list))��
 * 	SynchronizedList��a2��Ϊ��Ա������������SynchronizedList����˵������
 * 		final List<E> list;

        SynchronizedList(List<E> list) {
            super(list);
            this.list = list;
    	}
 * 3.��ΪSynchronizedListʵ����List�ӿڣ�����List�ﶨ��ķ���������Ҳ�����ˣ�
 * 		static class SynchronizedList<E>
        	extends SynchronizedCollection<E>
        	implements List<E> {...}
 * 4.���ǽ�a2�����з�����synchronized����ͬ����ʵ���̰߳�ȫ��
 * 		public void add(int index, E element) {
            synchronized (mutex) {list.add(index, element);}
        }
        public E remove(int index) {
            synchronized (mutex) {return list.remove(index);}
        }
        ...
 * 5.��Ϊ���еķ�������synchronizedͬ���ˣ�������󷵻صļ������̰߳�ȫ�ġ�
 * 6.ע��SynchronizedRandomAccessList��SynchronizedList������
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;

public class TestColletions {
	@Test
	public void testCollections1(){
		ArrayList al = new ArrayList();
		al.add("A");
		al.add(1);
		al.add("B");
		al.add(2);
		System.out.println("��������е�����Ԫ�أ�");
		System.out.println(al);
		System.out.println("����ǿforѭ���������ϣ�");
		for(Object o : al){
			System.out.println(o);
		}
		System.out.println("�õ�����Iterator���������е�����Ԫ�أ�");
		Iterator i = al.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
		}
		System.out.println("ʹ��Collections�������еĸ��ַ�����");
		Collections.reverse(al);//��תal�����е�Ԫ�ص�˳��
		System.out.println(al);
		Collections.shuffle(al);//��al����Ԫ�ؽ����������
		System.out.println(al);
//		Collections.sort(al);//����Ԫ�ص���Ȼ˳���al����Ԫ�ذ���������
//==>ע�⣺�����е�Ԫ�ر�����ͬһ���͵ģ������޷����бȽ�����
//		System.out.println(al);
		Collections.swap(al, 0, 1);//��ָ��al�����е�����Ԫ�ؽ��н���
		System.out.println(al);
		
		System.out.println("ʵ��ArrayList���ϵĸ��ƣ�");
//		ArrayList a2 = new ArrayList();//����ĸ��Ʒ�ʽ
//		Collections.copy(a2, al);
		List a2 = Arrays.asList(new Object[al.size()]);
//==>ע�⣬�½��ļ���Ϊʲôһ��Ҫ��List���ͣ�����ʹArrayList���ͣ�
		Collections.copy(a2, al);
		System.out.println(a2);
		
		System.out.println("�̰߳�ȫ����");
		Collections.synchronizedList(a2);//�����������󣬶��̲߳���ʱ���ǰ�ȫ���ˡ�
	}
}
