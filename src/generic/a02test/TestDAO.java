package generic.a02test;
//����DAO��Ķ��󣬷ֱ������sava��get��update��list��delete����������User����ʹ��Junit��Ԫ��������в��ԡ�
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class TestDAO {
	@Test
	public void testDAO(){
		
		DAO<User> dao = new DAO<User>();
		
		dao.map = new HashMap<String,User>();//ע�⣺�����Ҳ��ᣡ����
		
		System.out.println("��ʼ��dao.map���ϣ� " + dao.map);
		
		System.out.println();
		dao.sava("0", new User(1000,26,"�����"));//����sava()����
		dao.sava("1", new User(1001,23,"�����"));
		System.out.println(dao.map);
		
		System.out.println();
		User u = dao.get("0");//����get()����
		System.out.println(u);
		
		System.out.println();
		dao.update("1", new User(1003,26,"����÷"));//����update()����
		System.out.println(dao.map);
		
		System.out.println();
		List<User> list = dao.list();//����list()����
		System.out.println(list);
		
		System.out.println();
		dao.delete("1");//����delete()����
		System.out.println(dao.map);
	}
}
