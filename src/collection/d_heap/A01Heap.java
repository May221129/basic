package collection.d_heap;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.junit.Test;

/**
 * �ѣ�����һ���������ȫ�����������д󶥶Ѻ�С���ѡ��ѵ�ʵ���ࣺPriorityQueue�����ȼ����У�
 * 1.��ȫ��������Ҫ�󣬸ö�������ǰn-1�������������n��Ҳ���밴�մ����ҵ�˳������
 * 2.����Ѿ���һ���������ȫ������,���������������� ��ȫ�������� �� ÿ���ڵ�ֵ��С�ڻ���������ӽڵ㡣
 * 3�������ɾ���Ĳ���:http://www.cnblogs.com/vamei/archive/2013/03/20/2966612.html
 * 		�������һ������"�ϸ�"����: �Ȳ������һ��λ��, Ȼ�󲻶ϸ����ڵ�Ƚ�.
 * 		ɾ�������Ƚ����һ��Ԫ����Ϊ��һ��Ԫ��, Ȼ�󲻶���"�³�"����.	
 * 4.�����ǲ����������ɾ������, ����O(log N)
 * 		�����ɾ�����ǻ�ȡ���Ԫ�ػ�����СԪ��.  (�󶥶�  С����)
 * 		ɾ����ʱ���ǿ���ֱ���õ����/СԪ�ص�. �������ĵ����ѽṹ�����첽����, �о�Ҳ����~
 * 5.��ȫ���������ֵ����ݽṹ������һά������ģ��
 * 		���ڶѴ��߼��Ͽ���һ����ȫ����������˿��԰��ղ��������˳��Ԫ�ط���һά�����С�
 * 		ע��Ϊ�˷��㣬�����Ԫ�ش�Ŵ����� 1 ����ʼ������0����������������žͺ����׵��ҵ�ĳ����� i ��˫�׽��(i/2)�����ӽ��(���ӣ�2i���Һ��ӣ�2i+1)
 * 6.���´���ע���:
 * 		��1����λ1λ���ڳ˳�2.   ÿ�ζ����, �Ƴ�2λ
 * 		��2����λ����ĵȼ��ܵ�, ��+����.  �뾡�������Ű���һ����
 * 		��3����while(...)�еĴ��벻��Խ��
 * 		��4���߽�����, �ر��Ƕ�������������, �߽�������Ϊ��Ҫ.
 * 		��5��������bug��. ��ʱ������.
 */
public class A01Heap {
    
    private final int[] heap;
    
    private final int BEGIN_INDEX = 1;//������ĵ�1λ��ʼ.  ����д���� ���һ��Ԫ�صĵ��±���n�Ļ�, ��ô����������������2n��2n+1 
    
    private int lastIndex;
    
    
    public A01Heap() {
	heap = new int[10000];
    }
    
    //������ȫ0���������
    public void offer(int c) {
	//�жϳ�������
	if(lastIndex >= heap.length - 1) return;
	//�Ȳ��뵽���һ���ڵ�.
	heap[++lastIndex] = c;
	//�ϸ�����
	up();
    }
    //�õ���Ԫ���±�, ֱ�ӳ�2 ���Ǹ�Ԫ�ص��±���.
    //�ϸ�����
    private void up() {
	if(lastIndex == BEGIN_INDEX)return;//��һ�β���
	int parentIndex = lastIndex >> 1;
	int curIndex = lastIndex;
	while(parentIndex > 0) {
	    if(heap[curIndex] < heap[parentIndex]) {
		swap(curIndex, parentIndex);
		curIndex = parentIndex;
		parentIndex = curIndex >> 1;
	    }else {
		break;
	    }
	}
    }
    
    private void swap(int curIndex, int parentIndex) {
	int tmp = heap[parentIndex];
	heap[parentIndex] = heap[curIndex];
	heap[curIndex] = tmp;
    }
    
    public int poll() {
	if(lastIndex == 0)return 0;
	
	final int result = heap[BEGIN_INDEX];//ֱ���õ������.
	
	//�����һ��Ԫ�ط��ڵ�һ��Ԫ�ص�λ����.
	heap[BEGIN_INDEX] = heap[lastIndex];
	heap[lastIndex] = 0;
	//�³�
	down();
	
	--lastIndex;
	
	return result;
    }
    
    //�õ���Ԫ�ص��±�,  ���� 2n,  ���� 2n+1
    //�³�
    private void down() {
	int leftIndex, rightIndex, curIndex = BEGIN_INDEX;
	leftIndex = curIndex << 1;
	rightIndex = (curIndex << 1) + 1;//ǧǧ����ע��, ��λ����ļ���ܵ�. Ҫ������
	while(heap[leftIndex] != 0 || heap[rightIndex] != 0) {// ����Խ�粻�ᱨ�쳣.
	    int lower = getSmallerIndex(leftIndex, rightIndex);
	    if(heap[curIndex] > heap[lower]) {
		swap(curIndex, lower);
		curIndex = lower;
		leftIndex = (curIndex << 1) > heap.length-1 ? 0 : (curIndex << 1);//��ֹԽ�� 
		rightIndex = ((curIndex << 1) + 1) > 99 ? heap.length-1 : ((curIndex << 1) + 1);//��ֹԽ�� 
	    }else {
		break;
	    }
	}
    }
    
    //�õ������ӽڵ��н�С�Ľڵ���±�.
    private int getSmallerIndex(int left, int right) {
	if(heap[left] != 0 && heap[right] != 0) {
	    return heap[left] > heap[right] ? right : left;
	}else {
	    return heap[left] == 0 ? right : left;
	}
    }
    
    @Test
    public void test() {
	A01Heap heap = new A01Heap();
	IntStream.generate(()->ThreadLocalRandom.current().nextInt(1, 100000))
		.limit(10000)
		.forEach((x)->heap.offer(x));
	
	for(int i = 1; i < 9999; i++) {
	    System.out.println(heap.poll());
	}
    }
}
