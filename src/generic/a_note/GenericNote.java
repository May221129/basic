package generic.a_note;

/**
 Ҫ��ȷ���;��ǲ�֪�������͵�����, ���Բ������෺��,���Ƿ�������, ����Ҫ���䷽���ľ���ʵ����ǿת��ĳһ����������.

1. ʲôʱ��ָ�����ͣ�
	1.1 new����������ʱ��
		Order<String> o1 = new Order<String>();
	1.2 ĳ������ȥ�̳���������ʱ��
		public class BookDaoImplement extends BaseDao<Book>{}
		
2. ��ķ��� VS �����ķ���
	2.1 ��ķ��ͣ�public interface Dao<T> {}
	2.2 �����ķ��ͣ�
		<V> �����÷�����һ�������ķ��ͣ������෺�͡�
		���һ:	public <A> List<A> getContext(A a){//�����˸÷�����һ����ȷ�����͵�����A, ��������ͺͷ���ֵList�ķ���������һ����.
					return new ArrayList<A>();
   				}
   				����public <A> List<A> getContext(A a)ʱ,��:List<ɵñ> list = g.getContext(ɵñ��ʵ��); ��֤��κͷ���ֵList�����Ͷ���ɵñ����.
   		�����ķ��Ϳ��Բ����κεط�ȥָ��������ͣ����ø÷���ʱ���������䷵��ֵ���ͼ�Ϊ�������á�
   		������� public <B> B getBb();//�÷�����������ʾ�÷�����һ����ȷ�����͵ķ���ֵ.
				���ø÷�����ʱ��Ϳ���  int a = getBb() ����  double d = getBb();
 	      		����ʱ���ܻ����, ���Ǳ����ʱ��Ϳ��Բ���ǿת.
      	 		�䷽����ʵ������:return (B)new Object(); ע��Ҫǿת��(B)

 */
public class GenericNote {

}
