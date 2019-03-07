package annotation;


import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target
 * 括号里有写的就是可以用注解来修饰的，
 * 如果什么都不写，代表全部，哪里都可以用该注解。
 */
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})

/**
 * @Retention():元注解，表明注解的生命周期
 * RetentionPolicy.CLASS:编译时保留，运行时抛弃。
 */
@Retention(RetentionPolicy.CLASS)
/**
 * 自定义一个注解类
 * （仿照@Override，@Deprecate，@SuppressWarnings等写好的注解来写）
 */
public @interface A02MyAnnotation {
	
	/**
	 * 初始化注解:
	 * 用 default来修饰:默认的value值；
	 * 凡是写 value() 的，不论是用什么类型来修饰，在使用该注解时，可以不加 value="..." , 如：@A02MyAnnotation("atllm")
	 * 凡是 value()以外的，在使用该注释时，都需要表明value值，如：@A02MyAnnotation(name="atllm")
	 */
//	String value() default "我是自定义的注解";//注解有默认值，使用时：@A02MyAnnotation
	
//	int value();//使用时：@A02MyAnnotation(123)
	
//	String value1();//使用时：@A02MyAnnotation(value1="atllm")
	String name();//使用时：@A02MyAnnotation(name="atllm")
//	int age();//使用时：@A02MyAnnotation(age=26)
}
