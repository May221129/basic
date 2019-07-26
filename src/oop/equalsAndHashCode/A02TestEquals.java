package oop.equals;

/**
 * equals的练习题-2
 * 1.比较自己重写的equals()方法 和 系统提供的equals()方法的重写；
 * 2.初步了解"=="和equals()方法的区别；
 */
public class A02TestEquals {
	public static void main(String[] args) {
		
		Order o1 = new Order();
		Order o2 = new Order();
		System.out.println(o1.equals(o2));
		System.out.println(o1 == o2);
		
		Order o3 = new Order(33,"说话命令");
		Order o4 = new Order(44,"睡觉命令");
		System.out.println(o3.equals(o4));
		
		Order o5 = new Order(44,"睡觉命令");
		System.out.println(o4.equals(o5));
	}
}
class Order{
//	属性：
	protected int orderId;
	protected String orderName;
//	构造器：
	public Order(){
		super();
	}
	public Order(int orderId, String orderName){
		this.orderId = orderId;
		this.orderName = orderName;
	}
//	方法：
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
//	public boolean equals(Object obj){//比较两个Order对象的属性是否完全相同，相同则返回true。
//		if(this == obj){
//			return true;
//		}
//		if(obj instanceof Order){
//			Order o = (Order)obj;
//			return this.orderId == o.orderId && this.orderName.equals(o.orderName);//注意：String类型的，用equals进行比较，而不是==。
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