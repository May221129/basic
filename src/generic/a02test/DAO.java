package generic.a02test;
//���������ϰ�⣺DAO

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAO<T> {
	
	Map<String,T> map;//�����Ҳ���д������
//==>�����������ʵ������ʱ���ٳ�ʼ��
//	Map<String,T> map = new HashMap<String,T>();
//==>�����������д����
	
	public void sava(String id, T entity){//����T���͵Ķ���Map��Ա������
		map.put(id, entity);
	}
	public T get(String id){//��map�л�ȡid��Ӧ�Ķ���
		return map.get(id);
	}
	public void update(String id, T entity){//�滻map�е�keyΪid�����ݣ���Ϊentity����
		map.put(id, entity);
	}
//==>ע�⣺������������Ҳ���д��
	public List<T> list(){//����map�д�ŵ�����T����
		List<T> list = new ArrayList<T>();
		for(String s :map.keySet()){
			list.add(map.get(s));
		}
		return list;
	}
	public void delete(String id){//ɾ��ָ����id����
		map.remove(id);
	}
}
