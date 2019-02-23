package generic.a01helloworld;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义泛型类
 */
public class Order<E>{
//	属性：
	private String name;
	private Integer age;
	private E e;
	List<E> list = new ArrayList<E>();
//	构造器：
	public Order(){
		super();
	}
	public Order(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
//	泛型方法：
	public <T> T getT(T t){
		return t;
	}
	public <T> List<T> fromArrayToList(T[] t, List<T> list){
		for(T element : t){
			list.add(element);
		}
		return list;
	}
//	方法：
	/**
	 * 将E e属性，加入到list集合中。
	 */
	public void addE(){
		list.add(e);
	}
	public E getE(){
		return e;
	}
	public void setE(E e){
		this.e = e;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Order [name=" + name + ", age=" + age + ", e=" + e + "]";
	}
}
