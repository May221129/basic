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
 * ��������д�ľ��ǿ�����ע�������εģ�
 * ���ʲô����д������ȫ�������ﶼ�����ø�ע�⡣
 */
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})

/**
 * @Retention():Ԫע�⣬����ע�����������
 * RetentionPolicy.CLASS:����ʱ����������ʱ������
 */
@Retention(RetentionPolicy.CLASS)
/**
 * �Զ���һ��ע����
 * ������@Override��@Deprecate��@SuppressWarnings��д�õ�ע����д��
 */
public @interface A02MyAnnotation {
	
	/**
	 * ��ʼ��ע��:
	 * �� default������:Ĭ�ϵ�valueֵ��
	 * ����д value() �ģ���������ʲô���������Σ���ʹ�ø�ע��ʱ�����Բ��� value="..." , �磺@A02MyAnnotation("atllm")
	 * ���� value()����ģ���ʹ�ø�ע��ʱ������Ҫ����valueֵ���磺@A02MyAnnotation(name="atllm")
	 */
//	String value() default "�����Զ����ע��";//ע����Ĭ��ֵ��ʹ��ʱ��@A02MyAnnotation
	
//	int value();//ʹ��ʱ��@A02MyAnnotation(123)
	
//	String value1();//ʹ��ʱ��@A02MyAnnotation(value1="atllm")
	String name();//ʹ��ʱ��@A02MyAnnotation(name="atllm")
//	int age();//ʹ��ʱ��@A02MyAnnotation(age=26)
}
