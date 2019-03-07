package generic.a02test;
//创建DAO类的对象，分别调用其sava，get，update，list，delete方法来操作User对象，使用Junit单元测试类进行测试。

import java.util.HashMap;

import org.junit.Test;

public class TestDAO1 {
	@Test
	public void test1(){
		DAO<User> dao = new DAO<User>();
		dao.map = new HashMap<String,User>();
		dao.sava("1", new User(1001, 13, "康康"));
		dao.sava("2", new User(1002, 13, "简"));
		dao.sava("3", new User(1003, 14, "玛利亚"));
		System.out.println(dao.map);
		
		System.out.println();
		System.out.println(dao.get("2"));
		
		System.out.println();
		dao.update("2", new User(1005, 15, "朱丽叶"));
		System.out.println(dao.map);
		
		System.out.println();
		System.out.println(dao.list());
		
		System.out.println();
		dao.delete("3");
		System.out.println(dao.map);
	}
}
