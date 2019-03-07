package collection.a_note;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.junit.Test;

/**
 * ���ԣ��ֱ���ArrayList��LinkedList����10�򼶱��������ʱ��Ϊ���٣�
 * �ص㣺1.�õ�ѭ����ʽ(for��Iterator��)�� 2.ʹ��ʲô����(get(i)��ȡ�±�ķ�ʽ��next()?)
 * 
 * ����1��ΪʲôLinkedList����ʱ��ô������ArrayList�����ָ����������
 * �𣺡���Ҫ���������ϵ�get(i)�����ĵײ�����֪��
 * 	ArrayList��ֱ��ͨ�����������д洢����LinkedList����ʹ������ģ��ָ�룬��ʵ������ķ�ʽ��
 *  ==>ArrayList��ʹ���±�ķ�ʽѭ��������ʱ��������ã�ͨ���±����ֱ��ȡ���ݣ��ٶ���졣
 *  ==>linkedList�����Ƚ����ϵĵ�һ��Ԫ�صĽڵ�����һ��Ԫ�صĽڵ��Ϊ��Ա������
 * 	�ٽ�index�ͼ����м���Ǹ�����Ԫ���±���бȽϣ�
 * 	���indexС����ӵ�һ��Ԫ�صĽڵ�һֱnext��ȥ��ֱ���ҵ�index�Ǹ�Ԫ��Ϊֹ��
 * 	���index��������һ��Ԫ�صĽڵ�һֱprev������ֱ���ҵ�index�Ǹ�Ԫ��Ϊֹ��
 * 	�������ļ���ܴ��ʱ��ÿ��get(index)���Ǵӵ�һ��Ԫ�ؽڵ㿪ʼ�����ߴ����һ��Ԫ�ؽڵ������һֱ�ҵ�index�Ǹ�Ԫ�ء�indexԽ��next��prevҪ��Խ�ࡣ
 * 
 * ����2����Ȼʹ����ͨ��ѭ������ȡget(i),��ʹLinkedList����Ч�ʺ�������ô�����������LinkedList�ı���Ч���أ�
 * ��ʹ�õ�������ʽ��
 * 	LinkedList��ʹ�õ���������ʱ��Ч����ߣ�������ֱ��ͨ��LinkedList��ָ����б�����
 * 	ArrayList��ʹ�õ�����ʱ����ΪҪͨ��ArrayList������ָ�룬���Ч�ʾͻ�����±귽ʽ�������ǿ���LinkedList��
 * 
 * �ܽ᣺�ڽ���list����ʱ������Ƕ�ArrayList���б������Ƽ�ʹ���±귽ʽ�������LinkedList���Ƽ�ʹ�õ�������ʽ��
 */
public class B01ArrayList_LinkedList {
	
	/**
	 * 1.ʹ�û�ȡ�±�ķ�ʽ����ͨforѭ����
	 * ArrayList��1���룻
	 * LinkedList��6000�������ң�
	 */
	@Test
	public void testArrayListAndLinkedList(){
		
		int count = 10000*10;
		ArrayList<Integer> array = new ArrayList<Integer>(count);
		LinkedList<Integer> linked = new LinkedList<Integer>();
		for(int i = 0; i < count; i++){
			array.add(i);
			linked.add(i);
		}
		//����ArrayList��
		long startA = System.currentTimeMillis();
		for(int i = 0; i < array.size(); i++){
			array.get(i);
		}
		long endA = System.currentTimeMillis();
		System.out.println("A:" + (endA - startA));
		
		//����LinkedList��
		long startL = System.currentTimeMillis();
		for(int i = 0; i < linked.size(); i++){
			linked.get(i);
		}
		long endL = System.currentTimeMillis();
		System.out.println("L:" + (endL - startL));
	}
	
	/**
	 * 2.ʹ��next()������Iterator��������
	 * ArrayList��5���룻
	 * LinkedList��90+���룻
	 */
	@Test
	public void testIterator(){
		
		int count = 10000*1000;
		ArrayList<Integer> array = new ArrayList<Integer>(count);
		LinkedList<Integer> linked = new LinkedList<Integer>();
		for(int i = 0; i < count; i++){
			array.add(i);
			linked.add(i);
		}
		
		//����ArrayList��
		long startA = System.currentTimeMillis();
		Iterator<Integer> ai = array.iterator();
		while(ai.hasNext()){
			ai.next();
		}
		long endA = System.currentTimeMillis();
		System.out.println("A:" + (endA - startA));
		
		long startL = System.currentTimeMillis();
		Iterator<Integer> li = linked.iterator();
		while(li.hasNext()){
			li.next();
		}
		long endL = System.currentTimeMillis();
		System.out.println("L:" + (endL - startL));
	}
}
