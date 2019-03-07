package classLoader.a04loadclassandinitclass;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import classLoader.a02myclassloader.A02MyClassLoaderExtendsURLClassLoader;

/**
 * 1.���ԣ���ļ��غ���ĳ�ʼ���Ĺ�ϵ
 * 	package a04loadclassandinitclass;
	public class A extends a04loadclassandinitclass.B{
		static{
			System.out.println(A.class.getName() + "�ұ���ʼ����!");
		}
		public A(){
			System.out.println("ִ��A�Ĺ��캯����");
		}
		public void test(){
			System.out.println("Aʵ����test()ִ�гɹ���");
		}
	}
	
	package a04loadclassandinitclass;
	public class B {
		static{
			System.out.println(B.class.getName() + "�ұ���ʼ����!");
		}
		public B(){
			System.out.println("ִ��B�Ĺ��캯����");
		}
	}
 * 
 * 2.���н����
 * 	a04loadclassandinitclass.B�ұ���ʼ����!
	a04loadclassandinitclass.A�ұ���ʼ����!
	ִ��B�Ĺ��캯����
	ִ��A�Ĺ��캯����
	Aʵ����test()ִ�гɹ���
	A�ĸ��ࣺclass a04loadclassandinitclass.B
	
 * 3.���ۣ�
 * 	���������òŻ�������ĳ�ʼ�������ؾͲ��ᣩ��
 * 	����ĳ�ʼ���õļ����������Ǽ��ظ���ʱ�õ��Ǹ���������
 */
public class A01WhichloaderIsUsedToInitializeTheClass {
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		File file = new File("Q:\\ClassLoaderTest");
		URL[] urls = new URL[]{file.toURI().toURL()};
		A02MyClassLoaderExtendsURLClassLoader myClassLoader = new A02MyClassLoaderExtendsURLClassLoader(urls);
		Class<?> clazz = myClassLoader.loadClass("a04loadclassandinitclass.A");
		
		Object instance = clazz.newInstance();
		Method method = clazz.getDeclaredMethod("test", null);
		method.invoke(instance, null);
		
		System.out.println("A�ĸ��ࣺ" + clazz.getSuperclass());
	}
}
