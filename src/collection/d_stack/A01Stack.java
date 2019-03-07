package collection.d_stack;

import java.util.Stack;

import org.junit.Test;

/**
 * 栈：先进后出。实现类：Stack。
 */
public class A01Stack {
	@Test
	public void testStack(){
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < 10; i++){
			stack.push(i);//添加
		}
		System.out.println(stack.peek());//瞥一眼栈的头（最后进的，也就是最先出的那个），但不弹出
		for(Integer element : stack){
			System.out.println(element);
		}
		System.out.println("===================");
		
		System.out.println(stack.pop());//拿到栈的头，并弹出。
		for(Integer element : stack){
			System.out.println(element);
		}
		System.out.println("===================");
		
		System.out.println(stack.size());//栈的长度
		System.out.println(stack.empty());//该栈是否为空
	}
}
