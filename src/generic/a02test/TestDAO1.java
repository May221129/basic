package generic.a02test;
//����DAO��Ķ��󣬷ֱ������sava��get��update��list��delete����������User����ʹ��Junit��Ԫ��������в��ԡ�

import java.util.HashMap;

import org.junit.Test;

public class TestDAO1 {
	@Test
	public void test1(){
		DAO<User> dao = new DAO<User>();
		dao.map = new HashMap<String,User>();
		dao.sava("1", new User(1001, 13, "����"));
		dao.sava("2", new User(1002, 13, "��"));
		dao.sava("3", new User(1003, 14, "������"));
		System.out.println(dao.map);
		
		System.out.println();
		System.out.println(dao.get("2"));
		
		System.out.println();
		dao.update("2", new User(1005, 15, "����Ҷ"));
		System.out.println(dao.map);
		
		System.out.println();
		System.out.println(dao.list());
		
		System.out.println();
		dao.delete("3");
		System.out.println(dao.map);
	}
}
