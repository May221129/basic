package classLoader.a_note;

/**
ClassLoader���ص�����
һ��JVM�����������ͣ�https://blog.csdn.net/briblue/article/details/54973413��
	1.����ϵͳ��������BootstrapClassLoader��ExtClassLoader��APPClassLoader��
		1.1 ��ί�л��ƣ�
		1.2 loadClass(){
			findLoadedClass();
			parent.loadCLass();
			findClass(){
				defineClass();
			}
		      }
	2.����������֮�䣬���Ĳ�ͬ���ڡ�������Ĳ�ͬ����

�����ȼ���
	1.ʲô���ȼ��أ�
	2.ʵ���ȼ��صĹؼ���
		��ÿ�ζ�Ҫ�õ����µı��ȼ������Class��
		��һ��������ֻ�ܼ���ͬһ����һ�Σ����Զ����Ѿ����ع��˵��࣬��Ҫnew���µļ����������¼��ء�
		��ʹ�ó����淶����粻��ǿ������"���ȼ��ص���"��������ȼ��ص����ʵ��һֱ���ڸ��ɴ�������յ���������ܳ������⡣
			�磺
			ǰ�᣺��A�౻���������Σ�����ֱ���oldA��newA��ʾǰ�����μ��ص��ڴ��е�A�Ĵ�Class���󡣢�a��A��ʵ����
			a instanceof(A){
				A aa = (A)a;
				aa.show();//���show()������newA������ӵķ�����oldA�в�û���������������ִ�������aa.show()�����
			}

����������������
	����������ʱ�ܹ������õ��𣿿�����
	�ڽ��������ͨ�������ļ�������

�ġ������ļ�������CurrentContextClassLoader

�塢����ж��������Ƿ���ͬһ���ࣺ"��������ͬ+ȫ����"��ͬ����ô����ͬһ���ࡣ

�塢tomcat������ػ��ƣ����ͣ�https://blog.csdn.net/qq_34212276/article/details/78367127��
	1.tomcat�Լ�ʵ�ֵļ����࣬���������
		��JSPLoader��ÿ��JSP�ļ�����һ���Լ���JSPLoader������˼�壬����JSP�ļ���
		��WebAppClassLoader����ǰӦ�õ�ClassPath��
		��SharedClassLoader������Ӧ�á�����Tomcat��
		��CatalinaCLassLoader����Tomcat��
		��CommonCLassLoader������Ӧ�ü�Tomcat��
		ע��CommonClassLoader�ĸ�����������APPClassLoader�ˡ�
	2.tomcat�����еļ���������˳��
		��Ĭ���ǲ���ѭ��ί�л��Ƶģ���������"...\apache-tomcat-�汾��\conf\Context.xml"�ļ���������<Loader delegate = "true">������ʱ�ļ���˳��BootstrapClassLoader-ExtClassLoader-APPClassLoader-WebAppClassLoader��
		�ڿ����˸�ί�л��ƺ�ļ���˳���ǣ�BootstrapClassLoader-ExtClassLoader-APPClassLoader-CommonClassLoader-WebAppClassLoader��
		���ߵ�����CommonClassLoader��Ϊʲô��
			��CommonClassLoader���ص���tomcat����tomcat�����и���Ӧ�ù�ͬ��Ҫ��jar����
			�����һ��jar����WebAPPClassLoader���ص�ClassPath����һ�ݣ���CommonClassLoader���ص�"...\apache-tomcat-�汾��\lib"��Ҳ��һ�ݣ���һ���ѡ����ClassPath�µģ����ƻ��ߣ�������lib�д�ҹ��õģ�����е���ͽ�ԭ��
			����Ϊ���еľͽ�ԭ������Ҫ��WebAPPClassLoader�ȼ��ء�
 */
public class ClassLoaderNote {

}
