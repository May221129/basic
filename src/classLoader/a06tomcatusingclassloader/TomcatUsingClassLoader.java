package classLoader.a06tomcatusingclassloader;

/**
 * ̽����tomcat������ػ��ƣ�
	1.tomcat�Լ�ʵ�ֵļ����࣬���������
		��JSPLoader���������Jsp��ÿһ��Jsp�ļ�����Ӧһ��Jsp��������
		��WebAppClassLoader�����ؼ��ص���WebӦ����classesĿ¼�Լ�libĿ¼����⣬ֻ�ܵ�ǰӦ��ʹ�á�
		��SharedClassLoader�����ؼ���/sharedĿ¼����⣬�ɱ����е�webӦ��ʹ�ã���tomcat����ʹ�á�
		��CatalinaCLassLoader���������/serverĿ¼����⣬ֻ�ܱ�tomcatʹ�á�
		��CommonCLassLoader���������/commonĿ¼����⣬�����ŵ����ɱ�tomcat�Լ����е�Ӧ��ʹ�á�
		ע��CommonClassLoader�ĸ�����������APPClassLoader�ˡ�
	
	2.tomcat�����еļ���������˳��
		��Ĭ���ǲ���ѭ��ί�л��Ƶģ���������"...\apache-tomcat-�汾��\conf\Context.xml"�ļ���������<Loader delegate = "true">������ʱ�ļ���˳��BootstrapClassLoader-ExtClassLoader-APPClassLoader-WebAppClassLoader��
		�ڿ����˸�ί�л��ƺ�ļ���˳���ǣ�BootstrapClassLoader-ExtClassLoader-APPClassLoader-CommonClassLoader-WebAppClassLoader��
		���ߵ�����CommonClassLoader��Ϊʲô��
			��CommonClassLoader���ص���tomcat����tomcat�����и���Ӧ�ù�ͬ��Ҫ��jar����
			�����һ��jar����WebAPPClassLoader���ص�ClassPath����һ�ݣ���CommonClassLoader���ص�"...\apache-tomcat-�汾��\lib"��Ҳ��һ�ݣ���һ���ѡ����ClassPath�µģ����ƻ��ߣ�������lib�д�ҹ��õģ�����е���ͽ�ԭ��
			����Ϊ���еľͽ�ԭ������Ҫ��WebAPPClassLoader�ȼ��ء�
	
	3.tomcat��������⣺
		https://blog.csdn.net/qq_34212276/article/details/78367127
		ֻ��Ҫ������������Դ���й�����������������Ĵ����ɣ����´��룺�����־����ˡ������Դ�����Ϊ������⡣
 */
public class TomcatUsingClassLoader {
	
}
