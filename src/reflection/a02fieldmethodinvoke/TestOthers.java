package reflection.a02fieldmethodinvoke;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.junit.Test;

public class TestOthers {
	
	/**
	 * test7()：获取注解类的value值的三种方法
	 * 用到的两个方法：
	 *  1. Class<T> clazz.getAnnotation(Annotation.class);
	 *  2. Class<T> clazz.getAnnotations();
	 */
	@Test
	public void test7(){
		
       /**	
        * 方法一：A02Person的描述类对象 通过注解类的描述类对象 直接找到注解对象（知道注解类的名字时可以使用）
        */
		MyAnnotation a2 = A02Person.class.getAnnotation(MyAnnotation.class);
		System.out.println(a2);//结果：@a02fieldmethodinvoke.MyAnnotation(value=atllm)
		a2.value();
		System.out.println(a2.value());
		
		System.out.println("......................................");
		
		/**
		 * 方法二：通过A02Person的描述类对象 拿到A02Person类的所有注释：
		 */
		Annotation[] annotations = A02Person.class.getAnnotations();
		for(Annotation annotation : annotations){
//			这里要进行强转，否则无法调用value()
			MyAnnotation a1 = (MyAnnotation)annotation;
			System.out.println(a1);//输出：@a02fieldmethodinvoke.MyAnnotation(value=atllm)
			a1.value();
			System.out.println(a1.value());
		}
		
		System.out.println("......................................");
		
		/**
		 * 方法三：
		 * A02Person的描述类对象 通过注解类的描述类对象 直接找到注解对象
		 * 
		 * method.invoke(Object obj, Object... args)
		 * 	obj对象中带有参数 args 的method方法,返回值是Object.
		 */
		MyAnnotation a3 = A02Person.class.getAnnotation(MyAnnotation.class);
		if(a3 != null){
//			a3 的方法对象数组：
            Method[] methods = a3.annotationType().getDeclaredMethods(); 
            //遍历方法对象数组：
            for(Method method : methods ){
            	//判断当前的方法对象的访问权限，如果是不能访问的，就改为可以访问：
                if(!method.isAccessible()){
                	method.setAccessible(true);
                }
                try {
                	//a3对象中带有参数null的method方法,返回值是Object:
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

		
	//6.获取注解（这里的注解是个对象）
	@Test
	public void test6(){
		Class clazz = A02Person.class;
		Annotation[] anns = clazz.getAnnotations();
		System.out.println(anns.toString());
		for(Annotation a : anns){
			System.out.println(a);//@a02fieldmethodinvoke.MyAnnotation(value=atllm)
		}
	}
	
	//5.获取所在的包
	@Test
	public void test5(){
		Class clazz = A02Person.class;
		Package pack = clazz.getPackage();
		System.out.println(pack);
	}
	
	//4.获取实现的接口
	@Test
	public void test4(){
		Class clazz = A02Person.class;
		Class[] interfaces = clazz.getInterfaces();
		for(Class i : interfaces){
			System.out.println(i);
		}
	}
	
	//3*.获取父类的泛型
	@Test
	public void test3(){
		Class clazz = A02Person.class;
		Type type1 = clazz.getGenericSuperclass();
		
		ParameterizedType param = (ParameterizedType)type1;
		Type[] ars = param.getActualTypeArguments();
		
		System.out.println(((Class)ars[0]).getName());
	}
	
	//2.获取带泛型的父类
	@Test
	public void test2(){
		Class clazz = A02Person.class;
		Type type1 = clazz.getGenericSuperclass();
		System.out.println(type1);
	}
	
	//1.获取运行时类的父类
	@Test
	public void test1(){
		Class clazz = A02Person.class;
		Class superClass = clazz.getSuperclass();
		System.out.println(superClass);
	}
}
