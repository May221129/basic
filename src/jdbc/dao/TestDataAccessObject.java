package jdbc.dao;

import java.util.Date;
import java.util.List;
import org.junit.Test;

import jdbc.jdbctools.Student;

/**
 * ����DataAccessObject����д��ͨ�÷�����
 */
public class TestDataAccessObject {
	
	DataAccessObject dao = new DataAccessObject();
	
	@Test
	public void testUpdate() {
		String sql = "INSERT INTO customers(name,birthday,email) VALUES(?,?,?)";
		dao.update(sql, "ĳ��",new Date(System.currentTimeMillis()),"1212@qq.com");
	}

	@Test
	public void testGet() {
		
		String sql = "SELECT flow_id FlowID, Type, id_card IDCard, exam_card ExamCard,student_name StudentName, Location, Grade FROM examstudent WHERE flow_id = ?";
		
		Student student = dao.get(Student.class, sql, 22);
		
		System.out.println(student);
	}
	
	@Test
	public void testGet2() {
		
		String sql = "SELECT flow_id FlowID, Type, id_card IDCard, exam_card ExamCard,student_name StudentName, Location, Grade FROM examstudent WHERE flow_id = ?";
		
		Student student = dao.get2(Student.class, sql, 22);
		
		System.out.println(student);
	}

	@Test
	public void testGetForList() {
		
//		��ѯ���м�¼�����Բ�дWHERE������
		String sql = "SELECT flow_id FlowID, Type, id_card IDCard, exam_card ExamCard,student_name StudentName, Location, Grade FROM examstudent";
		
		List<Student> student = dao.getForList(Student.class, sql);
		System.out.println(student);
	}

	@Test
	public void testGetForValue() {
		
		String sql = "SELECT exam_card FROM examstudent WHERE flow_id = ?";
		
		String examCard = dao.getForValue(sql, 23);
		System.out.println(examCard);
		
//		���һ��ͳ����Ϣ-��߷֣�
		sql = "SELECT max(grade) FROM examstudent";
		int grade = dao.getForValue(sql);
		System.out.println(grade);
	}
}
