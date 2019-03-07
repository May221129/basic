package exception.a02runtimeexception_compileexception;
/**
 * ����ʱ�쳣������ʱ�쳣�����С�
 */
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class A05RuntimeExceptionOrCompileTimeException {

	public int update() {
		
		//ͨ��ĳ�������������һ������ĳ�����ݱ�ļ�¼��SQL��䡣
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
//			connection = ...
//			preparedStatement = ...
			
			//ִ�и��²�����
			preparedStatement.executeUpdate();
			
		/**
		 * ���۵㣺����õ���SQL�����﷨����ȷ�ģ�����û�гɹ�ִ������SQL���(�磺�ṩ�ı����Ǵ�ģ������ݿ����Ҳ�����Ӧ�ı�)����ʱ�Ǹ��ױ���ʱ�쳣������������ʱ�쳣�ó���ֹͣ��
		 * 1.����ʱ�쳣��Ҫôtry-catchס���ó�����Ӱ�죬�ܹ�����������ȥ��
		 * 	Ҫôthrows��������ף�Tomcat��ĳ���ط���ͳһ����try-catchס����Ȼ���յ������������������Ŀ��������
		 * 2.����ʱ�쳣��һ�����׳�����ʱ�쳣������ͻ���ֹ��
		 * 3.�����SQL���û�и��³ɹ�������ñ���ʱ�쳣���Ͳ����ó���ֹͣ��Ҳ�����׳��쳣��֪�ͻ���ĸ���ûִ�гɹ���
		 * 	���������ʱ�쳣����ΪSQL��䶼ûִ�гɹ�������Ҳ��û��Ҫ������ȥ�ˣ�ֱ���׳��쳣��֪�ͻ���
		 * 4.���ԣ��ñ���ʱ�쳣����������ʱ�쳣���Ǹ���ʵ���������ġ�
		 */
		} catch (Exception e) {
			e.printStackTrace();//����ֻ�ǰѴ�����Ϣ��ӡ������̨���ѣ���ƾ���д��룬�ͻ����޷���֪����ġ�
			throw new RuntimeException(e);//���д����ǽ�������Ϣ��֪�ͻ����ҳ����ֹͣ��
		} finally {
			//�ر�...
		}
		return 0;//���ظ��µļ�¼����
	}
}
