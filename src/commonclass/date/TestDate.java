package commonclass.date;
import java.text.ParseException;
//�����ࣺ
//java.text.SimpleDateFormat()�����ڹ��ʻ�
//��ʽ��������-->���ģ��ַ�������ʹ��SimpleDateFormat���format()����;
//�������ı�-->���ڣ���parse()����
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.Test;

public class TestDate {
	
	/**
	 * ʱ���-��ĳ����������� ��ʱ��
	 * System.currentTimeMillis()
	 */
	@Test
	public void testTime(){
		long start = System.currentTimeMillis();
		for(int i = 0; i < 10; i++){
			System.out.println(i);
		}
		long end = System.currentTimeMillis();
		System.out.println("��ʱ:" + (end - start));
	}
	
	@Test
	public void test4(){
//		������Calendar��
		Calendar c = Calendar.getInstance();//ʵ����
		int i = c.get(Calendar.DAY_OF_MONTH);//����������µĵڼ���
		System.out.println(i);
		
		c.add(Calendar.DAY_OF_MONTH, 5);//��Calendar.DAY_OF_MONTH�����ϼ�5��(���Ҫ��5�죬ֱ��д��-5����)
		int i2 = c.get(Calendar.DAY_OF_MONTH);
		System.out.println(i2);
		
		c.set(Calendar.DAY_OF_MONTH, 10);//��������Ϊ����µ�10��
		Date d = c.getTime();
		System.out.println(d);
	}
	
//	�ı�-->���ڵ�ת�������ã�
//	�������������ɹ����  1990-01-01  XXXX-XX-XX ���棿ɹ����
//	����date1��date2֮�������,date1����date2�������������ǱȽ�����ķ�������ġ�
	public int getDays(String date1,String date2) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = sdf.parse(date1);
		Date d2 = sdf.parse(date2);
		long milliTime = d2.getTime()-d1.getTime();
		return (int)milliTime/1000/3600/24 + 1;//����ΪʲôҪ+1�죿
	}
	@Test
	public void test3() throws ParseException{
		String str1 = "1990-01-01";
		String str2 = "1990-01-06";
		int dates = getDays(str1,str2);
		
		if(dates % 5 == 0 || dates % 5 == 4){
			System.out.println("ɹ��");
		}else{
			System.out.println("����");
		}
	}
	
	@Test
	public void test2() throws ParseException{
//		1����ʽ��һ��
		SimpleDateFormat sdf = new SimpleDateFormat();
		String s = sdf.format(new Date());//17-5-27 ����9:20
		System.out.println(s);
//		2����ʽ������
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
		String s1 = sdf1.format(new Date());//2017-05-27 09:20:57
		System.out.println(s1);
//		3��������
		SimpleDateFormat sdf2 = new SimpleDateFormat();//Ĭ�ϸ�ʽ��
		Date d1 = sdf2.parse("17-5-27 ����9:20");//sdf2���÷���ʱ�����ҲҪ��Ĭ�ϸ�ʽ��==>��ʽҪƥ��
		System.out.println(d1);//Sat May 27 21:20:00 GMT+08:00 2017
		
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");//�Զ����ʽ��
		Date d2 = sdf3.parse("2017-05-27 09:20:57");//sdf3���÷���ʱ�����ҲҪ��ʽƥ��
		System.out.println(d2);//Sat May 27 09:20:57 GMT+08:00 2017
	}
	
	@Test
	public void test1(){
//		����һ��Date��ʵ����
		Date d1 = new Date();
		System.out.println(d1);//��ӡ��ǰʱ�䣺Sat May 27 18:26:42 GMT+08:00 2017
		System.out.println(d1.getTime());//����һ��long�͵����飺1495880802473
		Date d2 = new Date(1495880802473L);//��long������תΪʱ��
		System.out.println(d2);
		
//		java.sql.Date d2 = new java.sql.Date(27484L);
//		System.out.println(d2);//1970-01-01
	}
}
