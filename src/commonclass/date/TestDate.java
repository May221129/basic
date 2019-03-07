package commonclass.date;
import java.text.ParseException;
//日期类：
//java.text.SimpleDateFormat()类易于国际化
//格式化：日期-->本文（字符串），使用SimpleDateFormat里的format()方法;
//解析：文本-->日期；用parse()方法
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.Test;

public class TestDate {
	
	/**
	 * 时间戳-算某个程序的运行 用时。
	 * System.currentTimeMillis()
	 */
	@Test
	public void testTime(){
		long start = System.currentTimeMillis();
		for(int i = 0; i < 10; i++){
			System.out.println(i);
		}
		long end = System.currentTimeMillis();
		System.out.println("用时:" + (end - start));
	}
	
	@Test
	public void test4(){
//		日历：Calendar：
		Calendar c = Calendar.getInstance();//实例化
		int i = c.get(Calendar.DAY_OF_MONTH);//今天是这个月的第几天
		System.out.println(i);
		
		c.add(Calendar.DAY_OF_MONTH, 5);//在Calendar.DAY_OF_MONTH日期上加5天(如果要减5天，直接写：-5即可)
		int i2 = c.get(Calendar.DAY_OF_MONTH);
		System.out.println(i2);
		
		c.set(Calendar.DAY_OF_MONTH, 10);//重新设置为这个月的10号
		Date d = c.getTime();
		System.out.println(d);
	}
	
//	文本-->日期的转换的运用：
//	“三天打渔两天晒网”  1990-01-01  XXXX-XX-XX 打渔？晒网？
//	返回date1与date2之间的天数,date1早于date2。【下面这种是比较巧妙的方法来算的】
	public int getDays(String date1,String date2) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = sdf.parse(date1);
		Date d2 = sdf.parse(date2);
		long milliTime = d2.getTime()-d1.getTime();
		return (int)milliTime/1000/3600/24 + 1;//这里为什么要+1天？
	}
	@Test
	public void test3() throws ParseException{
		String str1 = "1990-01-01";
		String str2 = "1990-01-06";
		int dates = getDays(str1,str2);
		
		if(dates % 5 == 0 || dates % 5 == 4){
			System.out.println("晒网");
		}else{
			System.out.println("打渔");
		}
	}
	
	@Test
	public void test2() throws ParseException{
//		1、格式化一：
		SimpleDateFormat sdf = new SimpleDateFormat();
		String s = sdf.format(new Date());//17-5-27 下午9:20
		System.out.println(s);
//		2、格式化二：
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
		String s1 = sdf1.format(new Date());//2017-05-27 09:20:57
		System.out.println(s1);
//		3、解析：
		SimpleDateFormat sdf2 = new SimpleDateFormat();//默认格式的
		Date d1 = sdf2.parse("17-5-27 下午9:20");//sdf2调用方法时，入参也要是默认格式的==>格式要匹配
		System.out.println(d1);//Sat May 27 21:20:00 GMT+08:00 2017
		
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");//自定义格式的
		Date d2 = sdf3.parse("2017-05-27 09:20:57");//sdf3调用方法时，入参也要格式匹配
		System.out.println(d2);//Sat May 27 09:20:57 GMT+08:00 2017
	}
	
	@Test
	public void test1(){
//		创建一个Date的实例：
		Date d1 = new Date();
		System.out.println(d1);//打印当前时间：Sat May 27 18:26:42 GMT+08:00 2017
		System.out.println(d1.getTime());//返回一个long型的数组：1495880802473
		Date d2 = new Date(1495880802473L);//将long型数组转为时间
		System.out.println(d2);
		
//		java.sql.Date d2 = new java.sql.Date(27484L);
//		System.out.println(d2);//1970-01-01
	}
}
