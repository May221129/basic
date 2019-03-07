package classLoader.a02myclassloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 1.����̳���ClassLoader,����д��findClass(String name)������
 * 	
 * 2.�����Ǽ̳�ClassLoader���Ǽ̳�URLClassLoader���Զ����������
 * 		�ٿ���ͨ��"path + ȫ����"����.class�ļ���
 * 		�ڲ���ͨ��"path + ȫ����"����jar���е�.class�ļ�����Ϊjar����һ��Ŀ¼�ṹ����Ҫ������
 * 	==��	ע��
 * 		��path:������ָ���ļ�����  ==��  new A01MyClassLoaderExtendsClassLoader("Q:/ClassLoaderTest");
 * 			loadClass("a02myclassloader.bean.Bean")�е�"a02myclassloader.bean.Bean"��ȫ������
 * 			���У��������Ǽ������´�������ṹ��Ŀ¼�ṹ��
 * 		��buildPath����ָ��·���µ�jar�����뵽classPath�С�
 * 
 * 3.�ص㣺�������������Ĳ�ͬ��������������ص���/��Դ���ڵ�·����ͬ��
 * 
 * 4.�Զ��������ʱѡ��˭����������Ҫ���أ���ί�л�����java���������õģ�����������ơ�
 * 		�磺��BootstrapClassLoader��ΪMyClassLoader�ĸ�����������ô����MyClassLoader���ص����У�������õ�ԭ������Ext
 * 		���ص��࣬���ﶼ�޷�ʹ���ˣ���ΪMyClassLoaderLoaderί��Bootstrapȥ���أ�Bootstrap���ز��������ص�MyClassLoader
 * 		Ҳ���ز�����������ǣ�Ҫô���õ�Ext���ص��࣬ҪôMyClassLoader�Լ�ȥʵ������ࡣ
 */
public class A01Test {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		A01MyClassLoaderExtendsClassLoader classLoader = new A01MyClassLoaderExtendsClassLoader("Q:/ClassLoaderTest");
		try {
			Class clazz = classLoader.loadClass("a02myclassloader.bean.Bean");
			if(clazz != null){
				try {
					Object bean = clazz.newInstance();
					Method method = clazz.getDeclaredMethod("test", null);
					method.invoke(bean, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
