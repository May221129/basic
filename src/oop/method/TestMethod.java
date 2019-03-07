package oop.method;
//练习题：方法的调用。方法里面嵌套方法。
public class TestMethod {
	public static void main(String[] args) {
		Car c = new Car();
		//调用方法：
		//c.setName("宝马");
		//c.getName("宝马");
		//c.setWheel(4);
		//c.getWheel(4);
		//c.info();
		
		//调用属性：
		c.name =  "宝马";
		c.wheel = 4;
		c.info();
		
		Factory f = new Factory();
		
		//调用方法一：
		Car c2 = f.produceCar();
		f.describeCar(c2);
		
		//调用方法二：
		Car c3 = f.produceCar("路虎", 4);
		f.describeCar(c3);
	}
}
class Factory{
	//制造车(下面这两个方法构成重载)
	//方法一
	public Car produceCar(){//返回值是 车 ，返回一辆车；方法名是制造车
		return new Car();
	}
	//方法二
	public Car produceCar(String name,int wheel){//返回值是车，返回一辆车；方法名是制造车；入参是 车的名字、轮胎数量
		Car c = new Car();
//	调用方法：
		c.setName(name);
		c.getName();
		c.setWheel(wheel);
		c.getWheel();
//	调用属性：
//		c.name = name;
//		c.wheel = wheel;
		
		return c;
	}
	//描述车的信息：
	public void describeCar(Car c){
		c.info();
	}
}

class Car{
	//属性
	String name;
	int wheel;
	//方法
	public void setName(String n){
		this.name = n;
	}
	public String getName(){
		//this.name = name;设置名字，获取名字写在一个方法里也是可以的，但是尽量将方法单一写开，模块化，这样便于以后某个方法独立的进行修改。
		return name;
	}
	public void setWheel(int wheel){
		this.wheel = wheel;
	}
	public int getWheel(){
		return wheel;
	}
	public void info(){
		System.out.println("name:" + name + ";" + "wheel:" + wheel);
	}
}