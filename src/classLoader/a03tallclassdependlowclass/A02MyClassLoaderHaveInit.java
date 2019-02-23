package classLoader.a03tallclassdependlowclass;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import classLoader.a02myclassloader.A02MyClassLoaderExtendsURLClassLoader;

/**
 * 1.���ԣ�
 * 		package a03tallclassdependlowclass.a02bean;
		public class A1 extends B1{
			static{
				System.out.println(A1.class.getName() + " --> �ҳ�ʼ���ɹ���");
			}
			public A1(){
				System.out.println("ִ��A1�Ĺ�����");
			}
		}
		
		package a03tallclassdependlowclass.a02bean;
		public class B1 extends A2 {
			static {
				System.out.println(B1.class.getName() + " --> �ҳ�ʼ���ɹ���");
			}
			public B1(){
				System.out.println("ִ��B1�Ĺ�����");
			}
		}
		
		package a03tallclassdependlowclass.a02bean;
		public class A2 {
			static{
				System.out.println(A2.class.getName() + " --> �ҳ�ʼ���ɹ���");
			}
			public A2(){
				System.out.println("ִ��A2�Ĺ�����");
			}
		}
 * 	
 * 2.ǰ�᣺
 * 	���Զ���������ļ���·����Q:\\ClassLoaderTest��
 * 	��main()�������ڵ��࣬����AppClassLoader���м��صġ�
 * 	�ۼ�����A�ĸ���������AppClassLoader��
 * 	��A���ص��ࣺA1��A2;             AppClassLoader��B1
 * 
 * 3.���н����cmd���������С���
 * 	Q:\mystudy\workplace\javase\bin>java a03tallclassdependlowclass.A02MyClassLoaderHaveInit
 * 	Exception in thread "main" java.lang.NoClassDefFoundError: a03tallclassdependlowclass/a02bean/A2
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
        at a03tallclassdependlowclass.A02MyClassLoaderHaveInit.main(A02MyClassLoaderHaveInit.java:60)
Caused by: java.lang.ClassNotFoundException: a03tallclassdependlowclass.a02bean.A2
        at java.net.URLClassLoader.findClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        at sun.misc.Launcher$AppClassLoader.loadClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        ... 25 more
        
 * 3.���ۣ�
 * 	3.1  ����һ���࣬�����������౻��ʼ����ֻ���������ø���Ż���������ĳ�ʼ����
 *  3.2 ����������˳������ѭ��ί�л��ƣ�������������أ����A01MyClassLoaderNotInit��4.2����
 *  	���ǳ�ʼ��һ����ȴ��Ҫ�ȳ�ʼ���丸�࣬A2����ʧ�ܣ�Ҳ����ζ��A2�಻�ܱ���ʼ�����Ӷ�����B1���޷���ɳ�ʼ�������յ���A1��ĳ�ʼ��Ҳʧ���ˡ�
 *  	A1��û�����ĳ�ʼ������Ȼ����ͨ������newInstance��
 *  
 * 4.��cmd���������г���
 * 	java -Djava.ext.dirs=""  -cp D:\jars\commons\commons-beanutils-1.9.3,abcdefg.jar  -Xmx1g -Xms1g -Xmn -Xss a03tallclassdependlowclass.A02MyClassLoaderHaveInit
 * 	���ͣ�java������      -Djava.ext.dirs=""������ϵͳ����      
 * 		-cp������Ŀ�����ĸ���jar������eclipse��buildPath����Щjar������������jar����·������Windows����";"���Ž��зָ linux��":"��
 * 		-Xmx1g -Xms1g -Xmn -Xss ��������������������ѡ���������������ȣ����ڴ����
 * 		a03tallclassdependlowclass.A02MyClassLoaderHaveInit��ȫ����
 * 	���ʣ�eclipse�����г���ʱ����eclipse���������ú���Ĭ�ϵ�����·����..../bin/ȫ����������classPath��·��Ҳ����ȷ��
 * 		���Լ���cmd��������ʱ��AppClassLoader��ô���ص����mian()�����ģ�
 * 		��jdk�汾�Ƚϵ͵�ʱ������Ҫ������ϵͳ�����ú�classPath�����ã�.;����jdk�汾�ߵİ��������ú��ˣ�
 * 		����"java a03tallclassdependlowclass.A02MyClassLoaderHaveInit"�������java classPaht.
 * 		a03tallclassdependlowclass.A02MyClassLoaderHaveInit = . = classPaht
 * 	
 * 5.�ر�ע�⣡����
 * 	��eclipse�ܲ��Ի᲻׼ȷ����Ϊ���Ұ�a03tallclassdependlowclass.a01bean���е�A1,A2��ɾ������B1�ᱨ��
 * 	��ʱ��eclipse��͵͵��Objcet�����Ǹ������A2�����������ļ̳й�ϵ�����ˡ�Ҳ�͵����˽���ǲ���ȷ�ġ���������Ҫ��cmd��������ֱ�����д��롣
 */
public class A02MyClassLoaderHaveInit {
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException {
		File file = new File("Q:\\ClassLoaderTest");
		URL[] urls = new URL[]{file.toURI().toURL()};
		A02MyClassLoaderExtendsURLClassLoader loader = new A02MyClassLoaderExtendsURLClassLoader(urls);
		Class clazz = loader.loadClass("a03tallclassdependlowclass.a02bean.A1");
		
		//��A1��ĸ�����˭��
		System.out.println("A1�ĸ��� --�� " + clazz.getSuperclass());//class a03tallclassdependlowclass.a02bean.B1
		
		//��A1���үү��˭������Ӧ����A2�ģ����ǽ���������ϣ���Object��
		System.out.println("A1��үү --�� " + clazz.getSuperclass().getSuperclass());//class java.lang.Object
		
		//ͨ�����䣬��ȡ��A1��ʵ����
		Object instance = clazz.newInstance();
		System.out.println(instance.toString());
		Method method = clazz.getDeclaredMethod("test", null);
		method.invoke(clazz, null);
	}
}
