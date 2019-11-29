package collection.a_note;

/**
 * �۵㣺�ڱ��������У�ʹ�ü��ϵ�remove��������ʹ���򱨴��׳��쳣������Ϊʲô��
 * 2���ص㣺1.�ײ�ʵ��-�õ����ĸ����ϣ� 2.�õ�������ѭ����ʽ��
 * 
 * �ص㣺Iterator�ɼ�������֮��ֻ��ʹ��һ�Ρ����룺A02forwhileiterator
 * 
 * ��1��Ϊʲô��Iterator��
 * �𣺼�����Ϊ����������洢Ԫ�ء��򡰹�����õ�һ����ԭ����������Iterator�ӿڡ�Itr�̳���Iterator�ӿڣ���Ϊ���ϵ��ڲ��࣬ר�����ڵ���Ԫ�ء�
 * 	���û�е��������õ�һ����̬��list��������instanceofȥ�ж����������ּ��ϣ����룺testList2()��testList1()
 * 
 * ��2����Iterator��������ʱ��ɾ��ĳһ��Ԫ�غ󣬻ᱨʲô��Ϊʲô��
 * �𣺴���ConcurrentModificationException�������޸��쳣��
 * ԭ����ArrayListΪ��
 * 	1.��ArrayList�ĸ���AbstractList�ж�����һ��int�͵����ԣ�modCount����¼��ArrayList�ṹ�Ա仯�Ĵ�����protected transient int modCount = 0;
 * 		��ArrayList�������漰�ṹ�仯�ķ����ж�����modCount��ֵ��������add()��remove()��addAll()��removeRange()��clear()��������Щ����ÿ����һ�Σ�modCount��ֵ�ͼ�1��
 * 	2.Itr����1����Ҳ������һ��int�͵����ԣ�expectedModCount�����������Itr���ʼ��ʱ������ArrayList�����modCount���Ե�ֵ��int expectedModCount = modCount;
 * 		ע���ڲ���Ա��ItrҲ��ArrayList���һ����Ա�������Է������е�AbstractList�����Ժͷ�����
 * 	3.��Itr.hasNext()�����У�public boolean hasNext() { return cursor != size(); }
 * 		������AbstractList��size()�������Ƚϵ�ǰ���λ���Ƿ�Խ�硣
 *  4.��Itr.next()�����У�ItrҲ�����˶�����AbstractList�е�get(int)���������ص�ǰ��괦��Ԫ�أ�
 *  	public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }
 *  5.ע�⣬�������next()�����е�����checkForComodification()���������ж��޸ĵ�ͬ����飺
 * 		final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
 * �ܽ᣺ͨ���������ɵ�����ʱ���Ὣ�����е�modCount��ֵ����������expectedModCount��
 * 	ֻ�Ǳ������ϣ������Լ��Ͻ�������ɾ�Ƚṹ�Ա仯����ʱ����������������ȵġ�
 * 	һ���ڱ������ϵĹ����У�ͨ�����ϵ�remove()����ɾһ��Ԫ��ʱ��remove�����л��޸ļ��ϵ�modCount����ȴ����ȥ�޸ĵ�������expectedModCount��
 * 	���Դ�ʱmodCount != expectedModCount���ͻᱨConcurrentModificationException����
 * 
 * ��ע��ArrayList��add()��remove()������������������ֻ��=�޸ļ��ϵ�modCount��ȴ����ȥ�޸ĵ�������expectedModCount��
 * 	Iterator��remove()������û��add()������Iterator��remove()������ɾ���˼��ϵ�Ԫ�غ󣬻Ὣ�����expectedModCount�ͼ����е�modCountͬ�����޸ġ�
 * 	
 * ��3.����ǿforѭ������������ʱɾ��ĳ��Ԫ���Ƿ���У�
 * �𣺲��У���Ϊ��ǿforѭ���ĵײ���ǵ�������
 * 
 * ��4.������ڱ����Ĺ�����ɾ��ĳ��Ԫ�غ󲻻ᱨ��
 * ���õ�������remove()������ɾ��Ԫ�أ��Ͳ��ᱨ��������testIterator2()
 * 	���ͨ����������remove()������ɾ��ĳһԪ�أ����������expectedModCount�ͼ����е�modCountͬ����expectedModCount = modCount;
 * 	public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
    }
 * 
 * ��5.�õ�������������ʱ���ü��ϵ�remove()���� ɾ�������ڶ���Ԫ�أ�ȴ���ᱨ��Ϊʲô�����룺testIterator1()
 * ��
 * 
 * ��6.��ͨѭ��������ɾ��һ��Ԫ��Ϊʲô�����������һ��Ԫ�أ�
 * ����Ϊɾ��һ��Ԫ�غ󣬻ᵼ�º�������Ԫ�ض���ǰ��һλ����i���ɻ��ߵ�����ɾ����Ԫ�ء��ĺ�һλȥ�����Ե��������ɾ����Ԫ�ء����Ǹ�Ԫ�ز��ᱻ����ˡ�
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class B02Iterator_Remove {
	
	/**
	 * ���û��ʹ�õ����������õ��ļ�����һ����̬ʱ:
	 * Ҳ�ǿ���ֱ�ӱ�����ѽ��
	 * ��ΪArrayList��ʵ����List�ӿڣ�get(index i)���������Ҳ��List�ӿ��ж���ķ�������ArrayListȥʵ���ˡ�����listҲ��ֱ��ʹ��get(index i)������
	 */
	@Test
	public void testList2(){
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
	}
	
	/**
	 * ���û��ʹ�õ����������õ��ļ�����һ����̬ʱ:
	 * ΪʲôҪ֪��list������˭��ʵ���أ�
	 * ��Ϊ��ͬ�ļ��ϣ����ݽṹ��ͬ�����Ե������Ǳ�����ʱ��û���öԷ�ʽ�������
	 * ����ArrayList�û���Ԫ���±�ķ�ʽ��������ܿ죻LinkedListû��������Ԫ���±꣬����ͨ��get(index)�������������ͨ����������Next()������������졣
	 */
	@Test
	public void testList(){
		
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		if(list instanceof ArrayList){
			ArrayList<Integer> al = (ArrayList<Integer>) list;
			for(int i = 0; i < al.size(); i++){
				al.get(i);
			}
		}if(list instanceof LinkedList){
			LinkedList<Integer> ll = (LinkedList<Integer>) list;
			for(int i = 0; i < ll.size(); i++){
				ll.get(i);
			}
		}
	}
	/**
	 * ��������õ�������������ʱ��ɾ��ĳһ��Ԫ�غ󣬲��ᱨ��
	 * ��Ϊ�õ��ǵ�������remove������
	 */
	@Test
	public void testIterator2(){
		
		ArrayList<Integer> age = new ArrayList<Integer>();
		age.add(1);
		age.add(2);
		age.add(3);
		age.add(4);
		age.add(5);
		
		Iterator<Integer> ai = age.iterator();
		while(ai.hasNext()){
			int i = ai.next();
			if(i == 3){
				ai.remove();
			}else{
				System.out.println(i);
			}
		}
		
//		Iterator�ɼ�������֮��ֻ��ʹ��һ�Σ�
//		age.add(6);
//		while(ai.hasNext()){
//			System.out.println(ai.next());
//		}
		
//		��һ�α�����age�󣬶�age������Ӳ�������Ҫ�ٴζ����age���Ͻ��б�������Ҫ��������һ��Iterator��
		age.add(6);
		age.add(7);
		Iterator<Integer> ai2 = age.iterator();
		while(ai2.hasNext()){
			System.out.println(ai2.next());
		}
	}
	
	/**
	 * ������Iterator����ʹ�õ�����ȥ�������ϵĹ����У����˼��ϵ�remove()����ɾ������ĳ��Ԫ��
	 * 
	 * ���⣺ɾ�������ڶ���Ԫ�أ�����ȴ�����׳��쳣��
	 * ����û���ͼ���ȽϺ���⡣
	 * 	1.�õ������������ϵ�1 2 3 ����û������ģ��ܹ����������
	 * 	2.��������4ʱ�����˼��ϵ�remove()�����ѡ�4������ɾ������4�������Ԫ��ȫ����ǰ��һλ��Ԫ�ء�5�����ˡ�4����λ�ã�
	 * 	3.����2�ɵã���ʱ����Ϊ{1,2,3,5}������ʱ��������ָ�뻹ָ��ԭ��Ԫ�ء�4������һ�У�Ҳ�������ڡ�5������һ�У���
	 * 	4.��ʱ������ͨ��hasNext()�����ж��Ƿ�����һ��Ԫ�أ�����false�����Բ���ִ��next()����������������
	 * 	5.��4����˵����Ϊʲô��ִ�е�������next()����������Ͳ����׳��쳣����Ϊnext()�����У����жϡ���������expectedModCount���͡����ϵ�modCount���Ƿ���ȡ�
	 * 		�ԡ�4������ɾ���󣬡����ϵ�modCount������1������������expectedModCount�����䣬����ʵ���������ǲ���ȵģ���ûִ��next()���������򲢲�֪����
	 */
	@Test
	public void testIterator1(){
		
		ArrayList<Integer> age = new ArrayList<Integer>();
		age.add(1);
		age.add(2);
		age.add(3);
		age.add(4);
		age.add(5);
		
		Iterator<Integer> ai = age.iterator();
		while(ai.hasNext()){//��������1 2 3
			int i = ai.next();
			if(i == 4){
				age.remove(i);
			}else{
				System.out.println(i);
			}
		}
	}
	
	/**
	 * ��ǿforѭ����
	 * ����Ĳ����������𡰲����޸��쳣����
	 */
	@Test
	public void testFor(){

		List<Integer> age = new ArrayList<Integer>();
		age.add(1);
		age.add(2);
		age.add(3);
		age.add(4);
		age.add(5);
		for(int newAge : age){
			System.out.println("ǰ�� " + newAge);
			if(newAge == 3){
				age.remove(newAge);
				System.out.println("�У� " + newAge);
			}
			System.out.println("��  " + newAge);
		}
	}
	
	/**
	 * ��ͨforѭ�����ڱ��������У�ͨ���ж�ɾ��ĳԪ�غ��ڸ�ɾ��Ԫ��֮�������Ԫ�ض�����ǰ�ƣ�Ԫ���±�Ҳ����֮�ı䡣
	 */
	@Test
	public void testCommonFor(){
		
		System.out.println("commonFor----------");
		List<String> name = new ArrayList<String>();
		name.add("A");
		name.add("B");
		name.add("C");
		name.add("D");
		name.add("E");
		
		for(int i = 0; i < name.size(); i++){
			if(i == 1){
				name.remove(i);//ɾ��һ��Ԫ�أ����º�������Ԫ�ض���ǰ��һλ��ACDE
			}else{
				System.out.println(name.get(i));
			}
		}
	}
}
