package reflection.a02fieldmethodinvoke;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.junit.Test;

public class TestOthers {
	
	/**
	 * test7()����ȡע�����valueֵ�����ַ���
	 * �õ�������������
	 *  1. Class<T> clazz.getAnnotation(Annotation.class);
	 *  2. Class<T> clazz.getAnnotations();
	 */
	@Test
	public void test7(){
		
       /**	
        * ����һ��A02Person����������� ͨ��ע�������������� ֱ���ҵ�ע�����֪��ע���������ʱ����ʹ�ã�
        */
		MyAnnotation a2 = A02Person.class.getAnnotation(MyAnnotation.class);
		System.out.println(a2);//�����@a02fieldmethodinvoke.MyAnnotation(value=atllm)
		a2.value();
		System.out.println(a2.value());
		
		System.out.println("......................................");
		
		/**
		 * ��������ͨ��A02Person����������� �õ�A02Person�������ע�ͣ�
		 */
		Annotation[] annotations = A02Person.class.getAnnotations();
		for(Annotation annotation : annotations){
//			����Ҫ����ǿת�������޷�����value()
			MyAnnotation a1 = (MyAnnotation)annotation;
			System.out.println(a1);//�����@a02fieldmethodinvoke.MyAnnotation(value=atllm)
			a1.value();
			System.out.println(a1.value());
		}
		
		System.out.println("......................................");
		
		/**
		 * ��������
		 * A02Person����������� ͨ��ע�������������� ֱ���ҵ�ע�����
		 * 
		 * method.invoke(Object obj, Object... args)
		 * 	obj�����д��в��� args ��method����,����ֵ��Object.
		 */
		MyAnnotation a3 = A02Person.class.getAnnotation(MyAnnotation.class);
		if(a3 != null){
//			a3 �ķ����������飺
            Method[] methods = a3.annotationType().getDeclaredMethods(); 
            //���������������飺
            for(Method method : methods ){
            	//�жϵ�ǰ�ķ�������ķ���Ȩ�ޣ�����ǲ��ܷ��ʵģ��͸�Ϊ���Է��ʣ�
                if(!method.isAccessible()){
                	method.setAccessible(true);
                }
                try {
                	//a3�����д��в���null��method����,����ֵ��Object:
                	method.invoke(a3, null);
                    System.out.println(method.invoke(a3, null));  
                } catch (IllegalAccessException e) {  
                    e.printStackTrace();  
                } catch (IllegalArgumentException e) {  
                    e.printStackTrace();  
                } catch (InvocationTargetException e) {  
                    e.printStackTrace();  
                }  
            }  
        }
	}

		
	//6.��ȡע�⣨�����ע���Ǹ�����
	@Test
	public void test6(){
		Class clazz = A02Person.class;
		Annotation[] anns = clazz.getAnnotations();
		System.out.println(anns.toString());
		for(Annotation a : anns){
			System.out.println(a);//@a02fieldmethodinvoke.MyAnnotation(value=atllm)
		}
	}
	
	//5.��ȡ���ڵİ�
	@Test
	public void test5(){
		Class clazz = A02Person.class;
		Package pack = clazz.getPackage();
		System.out.println(pack);
	}
	
	//4.��ȡʵ�ֵĽӿ�
	@Test
	public void test4(){
		Class clazz = A02Person.class;
		Class[] interfaces = clazz.getInterfaces();
		for(Class i : interfaces){
			System.out.println(i);
		}
	}
	
	//3*.��ȡ����ķ���
	@Test
	public void test3(){
		Class clazz = A02Person.class;
		Type type1 = clazz.getGenericSuperclass();
		
		ParameterizedType param = (ParameterizedType)type1;
		Type[] ars = param.getActualTypeArguments();
		
		System.out.println(((Class)ars[0]).getName());
	}
	
	//2.��ȡ�����͵ĸ���
	@Test
	public void test2(){
		Class clazz = A02Person.class;
		Type type1 = clazz.getGenericSuperclass();
		System.out.println(type1);
	}
	
	//1.��ȡ����ʱ��ĸ���
	@Test
	public void test1(){
		Class clazz = A02Person.class;
		Class superClass = clazz.getSuperclass();
		System.out.println(superClass);
	}
}
