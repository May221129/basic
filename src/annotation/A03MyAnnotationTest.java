package annotation;

/**
 * 自定义注解类 A02MyAnnotation 的使用：
 */
@A02MyAnnotation(name="atllm")//可以用来注释类
public class A03MyAnnotationTest {
	@A02MyAnnotation(name="atllm")//可以用来注解属性，里面可写东西，也可以不写
	private String name;
	private Integer age;
	@A02MyAnnotation(name="atllm")//可以注解方法
	public void sleep(){
		System.out.println("睡觉");
	}
	public void eat(){
		System.out.println("吃饭");
	}
}
