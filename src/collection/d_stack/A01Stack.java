package collection.d_stack;

import java.util.Stack;

import org.junit.Test;

/**
 * ջ���Ƚ������ʵ���ࣺStack��
 */
public class A01Stack {
	@Test
	public void testStack(){
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < 10; i++){
			stack.push(i);//���
		}
		System.out.println(stack.peek());//Ƴһ��ջ��ͷ�������ģ�Ҳ�������ȳ����Ǹ�������������
		for(Integer element : stack){
			System.out.println(element);
		}
		System.out.println("===================");
		
		System.out.println(stack.pop());//�õ�ջ��ͷ����������
		for(Integer element : stack){
			System.out.println(element);
		}
		System.out.println("===================");
		
		System.out.println(stack.size());//ջ�ĳ���
		System.out.println(stack.empty());//��ջ�Ƿ�Ϊ��
	}
}
