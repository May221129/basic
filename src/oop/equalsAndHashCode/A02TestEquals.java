package oop.equals;

/**
 * equals����ϰ��-2
 * 1.�Ƚ��Լ���д��equals()���� �� ϵͳ�ṩ��equals()��������д��
 * 2.�����˽�"=="��equals()����������
 */
public class A02TestEquals {
	public static void main(String[] args) {
		
		Order o1 = new Order();
		Order o2 = new Order();
		System.out.println(o1.equals(o2));
		System.out.println(o1 == o2);
		
		Order o3 = new Order(33,"˵������");
		Order o4 = new Order(44,"˯������");
		System.out.println(o3.equals(o4));
		
		Order o5 = new Order(44,"˯������");
		System.out.println(o4.equals(o5));
	}
}
class Order{
//	���ԣ�
	protected int orderId;
	protected String orderName;
//	��������
	public Order(){
		super();
	}
	public Order(int orderId, String orderName){
		this.orderId = orderId;
		this.orderName = orderName;
	}
//	������
	public int getOrderId(){
		return orderId;
	}	
	public void setOrderId(int orderId){
		this.orderId = orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
//	public boolean equals(Object obj){//�Ƚ�����Order����������Ƿ���ȫ��ͬ����ͬ�򷵻�true��
//		if(this == obj){
//			return true;
//		}
//		if(obj instanceof Order){
//			Order o = (Order)obj;
//			return this.orderId == o.orderId && this.orderName.equals(o.orderName);//ע�⣺String���͵ģ���equals���бȽϣ�������==��
//		}else{
//			return false;
//		}
//	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderName == null) {
			if (other.orderName != null)
				return false;
		} else if (!orderName.equals(other.orderName))
			return false;
		if (orderId != other.orderId)
			return false;
		return true;
	}
}