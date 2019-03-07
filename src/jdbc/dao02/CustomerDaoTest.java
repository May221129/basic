package jdbc.dao02;

import static org.junit.Assert.*;
import java.sql.Connection;
import org.junit.Test;

public class CustomerDaoTest {

	CustomerDao customerDao = new CustomerDao();
	
	@Test
	public void testBatch() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetForValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetForList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		Connection connection = null;
		
		try {
			connection = jdbc.jdbctools.JDBCTools.getConnection();
			String sql = "SELECT id, name customerName, " +
					"email, birth FROM customers " +
					"WHERE id = ?";
			Customer customer = customerDao.get(connection, sql, 5);
			System.out.println(customer); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbc.jdbctools.JDBCTools.release(null, null, connection);
		}
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

}
