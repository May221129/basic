package generic.a02test;
//创建DAO类的对象，分别调用其sava，get，update，list，delete方法来操作User对象，使用Junit单元测试类进行测试。
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class TestDAO {
	@Test
	public void testDAO(){
		
		DAO<User> dao = new DAO<User>();
		
		dao.map = new HashMap<String,User>();//注意：这里我不会！！！
		
		System.out.println("初始的dao.map集合： " + dao.map);
		
		System.out.println();
		dao.sava("0", new User(1000,26,"李光荣"));//调用sava()方法
		dao.sava("1", new User(1001,23,"李光猪"));
		System.out.println(dao.map);
		
		System.out.println();
		User u = dao.get("0");//调用get()方法
		System.out.println(u);
		
		System.out.println();
		dao.update("1", new User(1003,26,"赖丽梅"));//调用update()方法
		System.out.println(dao.map);
		
		System.out.println();
		List<User> list = dao.list();//调用list()方法
		System.out.println(list);
		
		System.out.println();
		dao.delete("1");//调用delete()方法
		System.out.println(dao.map);
	}
}
