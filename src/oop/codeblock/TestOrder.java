package oop.codeblock;
//��ĳ�Ա֮�ģ���ʼ����(�������)��
public class TestOrder {
	public static void main(String[] args) {
		Order o1 = new Order();
		System.out.println(o1);
		
		System.out.println("----------------------------------");
		Order o2 = new Order(121,"������");
		o2.setOrderId(123);
		System.out.println(o2);
		System.out.println(o2.getOrderDesc());
		
	}
}
class Order{
//	���ԣ�
	private int orderId;
	private String orderName;
	private static String orderDesc = "����order��";
//	��̬����飺
	static{
		orderDesc = "����order��";
		System.out.println("���Ǿ�̬�����");	
	}
//	�Ǿ�̬����飺
	{
		orderId = 11121;
		orderName = "������";
		System.out.println("���ǷǾ�̬�����");
	}
//	��������
	public Order(){
		super();
		System.out.println("���ǿղι�����");
	}
	public Order(int orderId, String orderName){
		this.orderId = orderId;
		this.orderName = orderName;
		System.out.println("������������εĹ�����");
	}
//	������
	public String getOrderDesc(){
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc){
		this.orderDesc = orderDesc;
	}
	public int getOrderId(){
		return orderId;
	}
	public String getOrderName(){
		return orderName;
	}
	public void setOrderId(int orderId){
		this.orderId = orderId;
	}
	public void setOrderName(String orderName){
		this.orderName = orderName;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderName=" + orderName + "]";
	}
}