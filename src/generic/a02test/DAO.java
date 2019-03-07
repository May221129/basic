package generic.a02test;
//泛型类的练习题：DAO

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAO<T> {
	
	Map<String,T> map;//这里我不会写！！！
//==>上面这个是在实例化的时候，再初始化
//	Map<String,T> map = new HashMap<String,T>();
//==>这是在这里就写死了
	
	public void sava(String id, T entity){//保存T类型的对象到Map成员变量中
		map.put(id, entity);
	}
	public T get(String id){//从map中获取id对应的对象
		return map.get(id);
	}
	public void update(String id, T entity){//替换map中的key为id的内容，改为entity对象
		map.put(id, entity);
	}
//==>注意：下面这个方法我不会写！
	public List<T> list(){//返回map中存放的所有T对象
		List<T> list = new ArrayList<T>();
		for(String s :map.keySet()){
			list.add(map.get(s));
		}
		return list;
	}
	public void delete(String id){//删除指定的id对象
		map.remove(id);
	}
}
