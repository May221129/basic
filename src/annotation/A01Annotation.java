package annotation;
/**
 * 注解Annotation-详细笔记。
 * 
 * 1. 作用：
 * 	① 可以在不改变原有逻辑的情况下, 在源文件中嵌入一些补充信息.
 * 	② Annotation 能被用来为程序元素(类, 方法, 成员变量等) 设置元数据
 * 
 * 2. 使用：Annotation可以像修饰符一样被使用, 
 * 	可用于修饰 包、类、构造器、方法、 成员变量、参数、局部变量的声明, 
 * 	这些信息被保存在 Annotation 的 “name=value” 对中。
 * 
 * 3. JDK内置的基本注解类型（3个）：
 * 	@Override: 限定重写父类方法, 该注释只能用于方法
 * 	@Deprecated: 用于表示某个程序元素(类, 方法等)已过时，生命周期为RUNTIME
 * 	@SuppressWarnings: 抑制编译器警告
 * 
 * 4. 元注解：对注解进行注解（4个）
 * 	① @Retention(RetentionPolicy.*)：指定某个Annotation可以保留多长时间,
 * 	  @Rentention 包含一个 RetentionPolicy 类型的成员变量, 
 * 	     使用 @Rentention 时必须为该 value 成员变量指定值：
 * 		 RetentionPolicy.SOURCE: 编译器直接丢弃这种策略的注释
 * 		 ==>默认值: RetentionPolicy.CLASS: 编译器将把注释记录在 class 文件中. 当运行 Java 程序时, JVM 不会保留注解。
 * 		 ==>可通过反射获取的注释:RetentionPolicy.RUNTIME:编译器将把注释记录在 class 文件中. 当运行Java程序时, JVM 会保留注释. 
 * 	② @Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})：
 * 	     指定被修饰的Annotation能用来修饰哪些元素（类？属性？方法？构造器？……）。如果不写(),代表全部。
 * 	  @Target 也包含一个名为 value 的成员变量.
 * 	③ @Documented：用于指定被该元 Annotation 修饰的 Annotation 类将被 javadoc 工具提取成文档.
 * 	     注意：定义为Documented的注解必须设置Retention值为RUNTIME。
 * 	④ Inherited：被它修饰的 Annotation 将具有继承性.
 * 	     如果某个类使用了被 @Inherited 修饰的 Annotation, 则其子类将自动具有该注解
 */

/**
 * A01Annotation类：了解 JDK 内置的基本注解类型（3个）：
 * 	@Override: 限定重写父类方法, 该注释只能用于方法
 * 	@Deprecated: 用于表示某个程序元素(类, 方法等)已过时
 * 	@SuppressWarnings: 抑制编译器警告
 */
public class A01Annotation {
	public static void main(String[] args) {
		Student s = new Student();
		s.eat();//过时了的方法，已经还是可以调用的。
	}
}
class Student extends Person{
	@Override//表示下面的方法重写了
	public void sleep(){
		System.out.println("学生睡觉");
	}
	@Deprecated//表示下面这个方法过时了
	public void eat(){
		System.out.println("学生吃饭");
	}
}
class Person{
	@SuppressWarnings("unused")//表示抑制编译器警告
	private String name;
	private Integer age;
	public void sleep(){
		System.out.println("睡觉");
	}
	public void eat(){
		System.out.println("吃饭");
	}
}