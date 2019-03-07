package classLoader.a05hotload;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

/**
 * 1.ʲô���ȼ��أ���������֮���Ѿ���������A�������ڳ��������ڼ䣬��A���Ķ��ˣ���Ҫ���¶�A���м��ء�
 * 
 * 2.�ȼ������̣�
 * 		�ٳ���������main()�൱��һ��while(true)����ͣ����ѭ��������Ҫ�õ�����ص��ڴ棻 
 * 		��Ĭ����AppClassLoader����classPath�е��ࣨ��ѭ��ί�л��ƣ���
 * 		�۳��������ڼ䣬����Ҫ���ȼ��ص����Դ�ļ��ڳ���֮�⣨��NotePad�༭���У����޸ģ������±����.class�ļ���
 * 			���ŵ��Զ���Ŀ������ȼ��صļ�������path�£�
 * 		�ܳ���Գ��֪����Щ���ǻᾭ�����Ķ�����Ҫ�����ȼ��صģ����Ǿͽ���Щ��Ϊ�Ʊ��ࣩ���ڳ����У���Ҫ�õ���Щ�Ʊ���ĵط�(����Ҫʵ����һ���Ʊ���)��
 * 			�Ͱ���2.���еķ������ж��Ʊ����Ƿ���Ҫ���¼��ء�
 * 		���Ʊ�����Ҫͳһ���Զ�����������м��ء�
 * 	
 * 	3.�ȼ��ص�ʹ�ó�����ʲôʱ�������ȼ��أ�ʲôʱ�����ã���
 * 		��粻��ǿ������"���ȼ��ص���"��������ȼ��ص����ʵ��һֱ���ڸ��ɴ�������յ���������ܳ��ֺܶ����⡣
 * 		�磺
			ǰ�᣺��A�౻���������Σ�����ֱ���oldA��newA��ʾǰ�����μ��ص��ڴ��е�A�Ĵ�Class���󡣢�a��A��ʵ����
			a instanceof(A){
				A aa = (A)a;
				aa.show();//���show()������newA������ӵķ�����oldA�в�û���������������ִ�������aa.show()�����
			}
 * 	
 * 	4.����ȼ������ľ���ʵ�֣�a05hotload.HotLoadTool.HotLoadTool(String path)
 */
public class HotLoadTest {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, InterruptedException, MalformedURLException{
		
		HotLoadTool hotLoadTool = new HotLoadTool("Q:\\ClassLoaderTest");
		
		Class<?> clazz;
		try {
			clazz = hotLoadTool.loadClass("a05hotload.Bean");
			clazz.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		TimeUnit.SECONDS.sleep(30);
		
		Class<?> clazz2;
		try {
			clazz2 = hotLoadTool.loadClass("a05hotload.Bean");
			clazz2.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
