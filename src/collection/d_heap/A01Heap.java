package collection.d_heap;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.junit.Test;

/**
 * 堆，就是一个特殊的完全二叉树。堆有大顶堆和小顶堆。堆的实现类：PriorityQueue（优先级队列）
 * 1.完全二叉树的要求，该二叉树的前n-1层必须填满，第n层也必须按照从左到右的顺序被填满
 * 2.二叉堆就是一个特殊的完全二叉树,满足两个条件：① 完全二叉树； ② 每个节点值都小于或等于它的子节点。
 * 3：插入的删除的操作:http://www.cnblogs.com/vamei/archive/2013/03/20/2966612.html
 * 		插入就是一个不断"上浮"操作: 先插入最后一个位置, 然后不断跟父节点比较.
 * 		删除就是先将最后一个元素置为第一个元素, 然后不断做"下沉"操作.	
 * 4.不管是插入操作还是删除操作, 都是O(log N)
 * 		这里的删除就是获取最大元素或者最小元素.  (大顶堆  小顶堆)
 * 		删除的时候是可以直接拿到最大/小元素的. 接下来的调整堆结构做成异步化的, 感觉也不错~
 * 5.完全二叉树这种的数据结构可以用一维数组来模拟
 * 		由于堆从逻辑上看是一颗完全二叉树，因此可以按照层序遍历的顺序将元素放入一维数组中。
 * 		注意为了方便，数组的元素存放从索引 1 处开始（不是0）。采用数组来存放就很容易地找到某个结点 i 的双亲结点(i/2)，孩子结点(左孩子：2i，右孩子：2i+1)
 * 6.以下代码注意点:
 * 		（1）移位1位等于乘除2.   每次都搞错, 移成2位
 * 		（2）移位运算的等级很低, 比+还低.  请尽量用括号包裹一起来
 * 		（3）在while(...)中的代码不会越界
 * 		（4）边界问题, 特别是对于增长的数字, 边界问题尤为重要.
 * 		（5）还是有bug的. 暂时不管了.
 */
public class A01Heap {
    
    private final int[] heap;
    
    private final int BEGIN_INDEX = 1;//从数组的第1位开始.  方便写代码 如果一个元素的的下标是n的话, 那么它的左右子树就是2n和2n+1 
    
    private int lastIndex;
    
    
    public A01Heap() {
	heap = new int[10000];
    }
    
    //生成有全0的情况发生
    public void offer(int c) {
	//判断超出长度
	if(lastIndex >= heap.length - 1) return;
	//先插入到最后一个节点.
	heap[++lastIndex] = c;
	//上浮操作
	up();
    }
    //拿到子元素下标, 直接除2 就是父元素的下标了.
    //上浮操作
    private void up() {
	if(lastIndex == BEGIN_INDEX)return;//第一次插入
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
	
	final int result = heap[BEGIN_INDEX];//直接拿到结果了.
	
	//拿最后一个元素放在第一个元素的位子上.
	heap[BEGIN_INDEX] = heap[lastIndex];
	heap[lastIndex] = 0;
	//下沉
	down();
	
	--lastIndex;
	
	return result;
    }
    
    //拿到父元素的下标,  左子 2n,  右子 2n+1
    //下沉
    private void down() {
	int leftIndex, rightIndex, curIndex = BEGIN_INDEX;
	leftIndex = curIndex << 1;
	rightIndex = (curIndex << 1) + 1;//千千万万注意, 移位运算的级别很低. 要加括号
	while(heap[leftIndex] != 0 || heap[rightIndex] != 0) {// 这行越界不会报异常.
	    int lower = getSmallerIndex(leftIndex, rightIndex);
	    if(heap[curIndex] > heap[lower]) {
		swap(curIndex, lower);
		curIndex = lower;
		leftIndex = (curIndex << 1) > heap.length-1 ? 0 : (curIndex << 1);//防止越界 
		rightIndex = ((curIndex << 1) + 1) > 99 ? heap.length-1 : ((curIndex << 1) + 1);//防止越界 
	    }else {
		break;
	    }
	}
    }
    
    //得到左右子节点中较小的节点的下标.
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
