package reflection.a02fieldmethodinvoke;

@MyAnnotation(value = "atllm")
public class A02Person extends Creature<String> implements Comparable,A01MyInterface{
	public String name;
	private int age;
	int id;
	//������ʱ����������һ���ղεĹ�������
	public A02Person() {
		super();
//		System.out.println("��������������");
	}
	public A02Person(String name) {
		super();
		this.name = name;
	}
	private A02Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@MyAnnotation(value = "abc123")
	public void show(){
		System.out.println("����һ���ˣ�");
	}
	
	private Integer display(String nation,Integer i) throws Exception{
		System.out.println("�ҵĹ����ǣ�" + nation);
		return i;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	@Override
	public int compareTo(Object o) {
		return 0;
	}
	
	public static void info(){
		System.out.println("�й��ˣ�");
	}
	
	class Bird{
		
	}
}
