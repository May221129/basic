package classLoader.a03tallclassdependlowclass;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

import classLoader.a02myclassloader.A02MyClassLoaderExtendsURLClassLoader;

/**
 * 1.���ԣ�
 * 		package a03tallclassdependlowclass.a01bean;
		public class A1 extends B1{
			static{
				System.out.println(A1.class.getName());
			}
		}
		
		package a03tallclassdependlowclass.a01bean;
		public class A2 {
			static{
				System.out.println(A2.class.getName());
			}
		}
		
		package a03tallclassdependlowclass.a01bean;
		public class B1 extends A2 {
			static {
				System.out.println(B1.class.getName());
			}
		}
		
 * 2.ǰ�᣺
 * 	���Զ���������ļ���·����Q:\\ClassLoaderTest��
 * 	��main()�������ڵ��࣬����AppClassLoader���м��صġ�
 * 	�ۼ�����A�ĸ���������AppClassLoader��
 * 	��A���ص��ࣺA1��A2;      AppClassLoader��B1
 * 	��A1��û����������B1��B1��Ҳû����������A2,�����Ƕ�û�д�����������ĳ�ʼ����
 * 
 * 3.�������н������cmd����������Ӧ�á���
 * 	Q:\mystudy\workplace\javase\bin>java a03tallclassdependlowclass.A01MyClassLoaderNotInit
	Exception in thread "main" java.lang.NoClassDefFoundError: a03tallclassdependlowclass/a01bean/A2
        at java.lang.ClassLoader.defineClass1(Native Method)
        at java.lang.ClassLoader.defineClass(Unknown Source)
        at java.security.SecureClassLoader.defineClass(Unknown Source)
        at java.net.URLClassLoader.defineClass(Unknown Source)
        at java.net.URLClassLoader.access$100(Unknown Source)
        at java.net.URLClassLoader$1.run(Unknown Source)
        at java.net.URLClassLoader$1.run(Unknown Source)
        at java.security.AccessController.doPrivileged(Native Method)
        at java.net.URLClassLoader.findClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        at sun.misc.Launcher$AppClassLoader.loadClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        at java.lang.ClassLoader.defineClass1(Native Method)
        at java.lang.ClassLoader.defineClass(Unknown Source)
        at java.security.SecureClassLoader.defineClass(Unknown Source)
        at java.net.URLClassLoader.defineClass(Unknown Source)
        at java.net.URLClassLoader.access$100(Unknown Source)
        at java.net.URLClassLoader$1.run(Unknown Source)
        at java.net.URLClassLoader$1.run(Unknown Source)
        at java.security.AccessController.doPrivileged(Native Method)
        at java.net.URLClassLoader.findClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        at a03tallclassdependlowclass.A01MyClassLoaderNotInit.main(A01MyClassLoaderNotInit.java:65)
Caused by: java.lang.ClassNotFoundException: a03tallclassdependlowclass.a01bean.A2
        at java.net.URLClassLoader.findClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        at sun.misc.Launcher$AppClassLoader.loadClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        ... 25 more
 * 
 * 4.���ۣ�
 * 	4.1 ����һ���࣬��������и��࣬���ȼ��ظ��࣬�ټ����丸�ֻࣨ�м����˸��࣬�Ż�֪�����и��ࣩ����debugʱ���Թ۲쵽��
 * 	4.2 ����һ����ʱ��ѭ��ί�л��ƣ�A1 extends B1���ȼ���A1��ִ��A.loadClass()����������ѭ��
 * 		ί�л��ƣ�����ô������B1ʱ��ͬ��ִ��"A������.loadClass()"����Ȼ������������ѭ��ί�л��ƣ���
 * 		����B1��ʱ�������и���A2��������AppClassLoader�ɹ�������B1��ͬ����AppClassLoaderȥ
 * 		����A2��Ҫ��ѭ��ί�л��ƣ�AppClassLoader��ExtClassLoader��BootstrapClassLoader
 * 		���޷�����A2(��ΪA2����A������������صģ���A����������ײ�ļ��������Ҽ���˳����ѭ��ί�л��ƣ�
 * 		������������Ͽ��Ƿ��ܼ��صģ����������¿��Ӽ������Ƿ��ܼ��أ�����A2�޷��ɹ����ص��ڴ�)����debugʱ���Թ۲쵽��
 * 		==>�ܶ���֮������ֻ����ѭ��ί�л�����������ߣ�����������ȥί�и��ײ�ļ����������ء�
 * 		ע���ٸ�ί�л��ƾ���������̼���Q:\mystudy\studynotes\jvm_gc_classloader\classloader\ClassLoader_�ҵ����.docx �е�1.4˫��ί�С�
 * 		   ����A������B��������и��࣬������������������࣬�ȵȡ�
 * 
 * 5.�ر�ע�⣡����
 * 	��eclipse�ܲ��Ի᲻׼ȷ����Ϊ���Ұ�a03tallclassdependlowclass.a01bean���е�A1,A2��ɾ������B1�ᱨ��
 * 	��ʱ��eclipse��͵͵��Objcet�����Ǹ������A2�����������ļ̳й�ϵ�����ˡ�Ҳ�͵����˽���ǲ���ȷ�ġ���������Ҫ��cmd��������ֱ�����д��롣
 */
public class A01MyClassLoaderNotInit {
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException {
		File file = new File("Q:\\ClassLoaderTest");
		URL[] urls = new URL[]{file.toURI().toURL()};
		A02MyClassLoaderExtendsURLClassLoader loader = new A02MyClassLoaderExtendsURLClassLoader(urls);
		//ע�⣬B1 extends A2����ΪA2����A���������صģ�����û��classPath�У�����class B1��ᱨ��
		//Ϊ������Ŀû�б����źţ��Ҿ���ʱ��B1��ע�͵��ˡ����Ҫ�ܸ�main����Ҫ��B1��ע��ȥ����
		Class clazz = loader.loadClass("a03tallclassdependlowclass.a01bean.A1");
	}
}
