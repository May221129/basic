package exception.a02runtimeexception_compileexception;
/**
 * 编译时异常和运行时异常的运行。
 */
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class A05RuntimeExceptionOrCompileTimeException {

	public int update() {
		
		//通过某个方法，获得了一条更新某张数据表的记录的SQL语句。
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
//			connection = ...
//			preparedStatement = ...
			
			//执行更新操作：
			preparedStatement.executeUpdate();
			
		/**
		 * 讨论点：如果拿到的SQL语句的语法是正确的，但是没有成功执行这条SQL语句(如：提供的表名是错的，在数据库中找不到对应的表)，此时是该抛编译时异常，还是抛运行时异常让程序停止？
		 * 1.编译时异常：要么try-catch住，让程序不受影响，能够继续运行下去；
		 * 	要么throws逐层往上抛，Tomcat在某个地方会统一处理，try-catch住（不然最终到虚拟机，会让整个项目奔溃）。
		 * 2.运行时异常：一旦在抛出运行时异常，程序就会终止。
		 * 3.这里的SQL语句没有更新成功，如果用编译时异常，就不会让程序停止，也不会抛出异常告知客户你的更新没执行成功；
		 * 	如果用运行时异常，因为SQL语句都没执行成功，程序也就没必要运行下去了，直接抛出异常告知客户。
		 * 4.所以：用编译时异常还是用运行时异常，是根据实际需求来的。
		 */
		} catch (Exception e) {
			e.printStackTrace();//这里只是把错误信息打印到控制台而已，仅凭这行代码，客户是无法得知错误的。
			throw new RuntimeException(e);//这行代码是将错误信息告知客户，且程序会停止。
		} finally {
			//关闭...
		}
		return 0;//返回更新的记录条数
	}
}
