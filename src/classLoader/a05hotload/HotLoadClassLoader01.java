package classLoader.a05hotload;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

/**
 * ������ȼ��ؼ�������Ȼûʵ�ֳɹ�������˼·�ǶԵġ�
 * ˼·��
 * 	loadClass(){
 * 		findLoadedClass();//����
 * 		parent.loadClass();//��ί��
 * 		findClass(){//��Ҫ�ȼ��ص��࣬��ֱ����������͸�ί�У�ֱ�ӽ��м��ء����ǣ�ʧ���ˣ���ΪdefineClass()����һ����ˣ�һ����ֻ�ᱻͬһ����������һ�Ρ�
 * 			defineClass();
 * 		}
 * 	}
 * 	�ټ������Ļ��棺��Ϊ�л���Ĵ��ڣ�һ����ֻ��ͬһ������������һ�Ρ�
 * 	�ڸ�ί�л��ƣ�������Ҫ�ȼ��ص��࣬��Object��String����Ҫ����ϵͳ���������ص���͵�������ί�л��ƽ��м��ء�
 */
public class HotLoadClassLoader01 extends URLClassLoader{
	/**
	 * ��Ҫ���ȼ��ص��඼�ŵ�hotClass�С�
	 * ���⣺�����ڳ��������ڼ䣬��A������Ҫ�ȼ��ص��࣬��ʱ��������hotClass����Ӹ��ࡣ
	 */
	private Set<String> hotClass = new HashSet<String>();
	
	{
		hotClass.add("a05hotload.Bean");
	}
	
	public HotLoadClassLoader01(URL[] urls) {
		super(urls);
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if(hotClass.contains(name)){
			return super.findClass(name);
		}
		return super.loadClass(name);
	}
}
