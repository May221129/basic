package classLoader.a02myclassloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * URLClassLoader��ExtClassLoader��AppClassLoader�ĸ��࣬�����Ѿ�ʵ�ֵúܺ��ˣ�
 * 	���������Ҫ�Զ�һ�����������ֻ��Ҫ�̳�URLClassLoader��Ȼ�����Զ���ļ������е��ø���
 * 	�Ĺ�������URL�������༴�ɡ�
 * 
 * �ص㣺�������������Ĳ�ͬ��������������ص���/��Դ���ڵ�·����ͬ��
 */
public class A02MyClassLoaderExtendsURLClassLoader extends URLClassLoader{

	public A02MyClassLoaderExtendsURLClassLoader(URL[] urls) {
		super(urls);
	}
}
