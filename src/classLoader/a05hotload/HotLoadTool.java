package classLoader.a05hotload;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * һ��ʵ���ȼ��صļ�������---------------------------------------------------------------------------------------
 * 	1 һ������������඼���������Զ���ļ��������ȼ��ؼ�ȻҪ���Լ�����������¼��أ��͵�ʵ��һ���Զ����������
 * 		�ü�������Ҫ�����ṩ���صĹ��ܣ������ȼ��ء�
 * 	2 ����ж�һ�����Ѿ����Ķ��ˣ�
 * 		�𣺼� Class<?> loadClass(String name)�е�lastModified��
 * 	3 ��һ�����иĶ���ʱ����μ����µ��������û�иĶ�ʱ������λ�ȡ��֮ǰ�Ѿ����غõ��ࡣ
 * 		�𣺼�  ����3.
 * 	4 ���¼�����һ������Ϣ���ڴ棬�Ḳ�ǵ�֮ǰ���ص��Ƿ���������ᣬ��ʹ��ʱ��ôѡ��
 * 		�𣺲��Ḳ�ǣ����¼����˼��Σ��ڴ��оͻ��м��ݡ�ͨ��ǰ����ص�����Ϣ������ʵ������������ԭ���Ƿ�����Ϣ��������ģ�
 * 		����newһ���µ�ʵ�������������Ƿ�����Ϣ�����������ȼ��ص�ʱ������д������Ҫע�⣬һ��ʵ������Ѿ�û�����ˣ�
 * 		��Ҫ������ʱ�����գ������ɴ������һ���������ڴ����ж������Ϣ�����ϵ�����Ϣ��ʵҲ��û�����˵ģ�ûʵ���ˣ���
 * 	5 Ϊʲô��Ҫ���ȼ��ص��࣬���ܷŵ�classPath����APPClassLoader�����ء�
 * 		�𣺢١���AppClassLoader���ȼ��ء�����һ����������ܿ��̣���Ҫ����û���κ�ʵ��+����û������������+���ظ���ļ�����ҲҪ�����գ������������գ�
 * 		��ζ�Ÿü��������ص������඼Ҫ�����գ�+������Ҫ������Щ����ʱ���ѵģ�����һ����һ�������ص��ڴ��У�
 * 		�������Ա����ա����Ų��ϵ����¼���A��AppClassLoader�Ļ����оͻ���ںܶ��A������Ϣ���ⲻ����
 * 	  	��ÿ�����������и����ڷš��Լ����ؽ������ࡱ�Ļ��棬AppClassLoader������A���Զ���������ټ���A��
 * 		���ԵĻ����ж�����A������Ϣ����������Ϣ����û��������й������Բ�����
 *
 * ����HotLoadToolȥʵ������ȼ��ع��ܵ�Ҫ��-------------------------------------------------------------------------
 * 	1.��"�ȼ���һ����"������ܳ��HotLoadTool���û�ֻ��Ҫ�ṩpath��ȫ������HotLoadTool�ͻ᷵�ظ������µ�Class����
		public HotLoadTool(URL[] urls){...}//����path������
		Class<?> loadClass(String name)//��ȫ����������
 * 	2.Map<ȫ������Class> clazzs;  Map<ȫ������.class�ļ������һ���޸�ʱ��> fileLastModified;
 * 	3.loadClass(){//�ȼ��ع��ܵ�ʵ��
 *  	//�����Ƿ񱻼��ع�(ͨ���Ƚ�.class�ļ���lastModified���ж�)��
 *  		//���ع���.class�ļ�û���Ĺ���
 *  			//ֱ�Ӵ�map�л�ȡClass
 *  	//û���صģ�����ع���.class�ļ����Ĺ���
 *  		//newһ���µ�ClassLoader���м���
 *   }
 */
public class HotLoadTool {
	
	private static Map<String, Class> clazzs = new HashMap<>();//Map<ȫ������Class>
	private static Map<String, Long> fileLastModified = new HashMap<>();//Map<ȫ������.class�ļ������һ���޸�ʱ��>
	private String path;
	
	public HotLoadTool(String path) {
		this.path= path;
	}
	
	public Class<?> loadClass(String name) throws ClassNotFoundException, MalformedURLException  {
		File file = new File(getFilePath(path, name));
		//ע������� file.getPath():
		long lastModified = new File(this.getFileName(file.getPath())).lastModified();//.class�ļ������һ���޸�ʱ��
		
		if(clazzs.containsKey(name) && fileLastModified.get(name) == lastModified){
			System.out.println("---------- �Ѿ������ع�����.clazz�ļ�û�б��޸Ĺ� ------------");
			return clazzs.get(name);
		}
		
		System.out.println("---------- ���ܣ����Ѿ������ع�����.clazz�ļ����޸Ĺ�����û�б����ع� -----------");
		Class<?> clazz = new HotLoadClassLoader02(this.getURLs(path)).loadClass(name);
		clazzs.put(name, clazz);
		fileLastModified.put(name, lastModified);
		return clazz;
	}
	
	/**
	 * ���û��ṩ��path��ת���Զ��������HotLoadClassLoader02����Ҫ��url
	 */
	public URL[] getURLs(String path) throws MalformedURLException{
		File file = new File(path);
		return new URL[]{file.toURI().toURL()};
	}
	
	public String getFilePath(String path, String name){
		return path + "\\" + name;
	}
	
	private String getFileName(String name) {
		String newName = name.replace(".", "\\");
		return newName + ".java";
	}
}
