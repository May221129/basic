package annotation;

/**
 * �Զ���ע���� A02MyAnnotation ��ʹ�ã�
 */
@A02MyAnnotation(name="atllm")//��������ע����
public class A03MyAnnotationTest {
	@A02MyAnnotation(name="atllm")//��������ע�����ԣ������д������Ҳ���Բ�д
	private String name;
	private Integer age;
	@A02MyAnnotation(name="atllm")//����ע�ⷽ��
	public void sleep(){
		System.out.println("˯��");
	}
	public void eat(){
		System.out.println("�Է�");
	}
}
