package reflection.a01classtest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

//��λ�ȡClass��ʵ����3�ַ�������
//Ҫ�����գ�����
import org.junit.Test;

public class TestPerson1 {
	@Test
	public void test() throws Exception{
		
//		1����������ʱ�౾���.class���ԣ�
		Class clazz = Person.class;
		System.out.println(clazz.getName());
		
//		2��ͨ������ʱ��Ķ����ȡ��
		Person p = new Person();
		Class clazz1 = p.getClass();
		System.out.println(clazz1.getName());
		
//		3��ͨ��Class�ľ�̬��������ȡ��
//		ͨ���˷�������ᷴ��Ķ�̬�ԡ�
		String className = "teseclass.Person";
		Class clazz2 = Class.forName(className);
		System.out.println(clazz2.getName());
		
//		4��ͨ����ļ��������˽⼴�ɣ���
		ClassLoader classLoader = this.getClass().getClassLoader();
		Class clazz5 = classLoader.loadClass(className);
		System.out.println(clazz5.getName());
		
//		Ҫ���գ�
//		��ʽһ���ھ����һ�������ȡָ���ļ������ݣ�
		ClassLoader cl = this.getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream("a01teseclass\\hello.properties");
		Properties pro = new Properties();
		pro.load(is);
		String s = pro.getProperty("user");
		System.out.println(s);
		
		String s1 = pro.getProperty("password");
		System.out.println(s1);
		
//		��ʽ�����ڵ�ǰ��Ŀ�£���ȡָ���ļ������ݣ�
		FileInputStream fis = new FileInputStream(new File("hello.txt"));
		Properties pros = new Properties();
		pro.load(fis);
		String str = pros.getProperty("user");
		System.out.println(str);
	}
}
