package oop.codeblock;
//类的成员之四：初始化块(即代码块)：
public class TestOrder {
	public static void main(String[] args) {
		Order o1 = new Order();
		System.out.println(o1);
		
		System.out.println("----------------------------------");
		Order o2 = new Order(121,"爱老婆");
		o2.setOrderId(123);
		System.out.println(o2);
		System.out.println(o2.getOrderDesc());
		
	}
}
class Order{
//	属性：
	private int orderId;
	private String orderName;
	private static String orderDesc = "我是order吗？";
//	静态代码块：
	static{
		orderDesc = "我是order！";
		System.out.println("我是静态代码块");	
	}
//	非静态代码块：
	{
		orderId = 11121;
		orderName = "疼老婆";
		System.out.println("我是非静态代码块");
	}
//	构造器：
	public Order(){
		super();
		System.out.println("我是空参构造器");
	}
	public Order(int orderId, String orderName){
		this.orderId = orderId;
		this.orderName = orderName;
		System.out.println("我是有两个入参的构造器");
	}
//	方法：
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