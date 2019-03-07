package classLoader.a01helloworld;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * 1.ǰ�᣺AppClassLoader��ExtClassLoader����ϵͳ�������������Ƕ���Launcher��ľ�̬�ڲ��ࡣ
 * 2.���⣺��λ�ȡ�������أ�
 * 3.��������ȡ��ExtClassLoader����ʽ�ؽ���ָ��Ϊ�Զ���������ĸ��ࡣ
 * 4.��ȡExtClassLoader��
 * 		private ClassLoader parent = new Launcher().getClassLoader().getParent();
 * 	�١�new Launcher().getClassLoader()���õ�AppClassLoader��
 * 	�ڡ�AppClassLoader.getParent()���õ�ExtClassLoader��
 * 	��ExtClassLoader��Launcher�ľ�̬�ڲ��࣬�������η���ȱʡ������ȱʡ�ķ�Χ�����޷�ֱ��new��ExtClassLoader��
 * 	ע����Ҫ��֤������˵�ģ�����ֱ�ӿ�Launcher���Դ�루���ѣ�һ���Ͷ�����
 */
public class A03GetExtClassLoader extends URLClassLoader {

	public A03GetExtClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}
}
