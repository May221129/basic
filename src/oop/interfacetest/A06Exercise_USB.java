package oop.interfacetest;
/**
 * 接口的练习
 */
public class A06Exercise_USB {
	public static void main(String[] args) {
		Computer c = new Computer();
		c.doWork(new Printer());//匿名对象的方式，来调用接口的方法
		
		Flash f = new Flash();
		c.doWork(f);
//==>实现接口的匿名类的对象：方法一
//==>书写格式：接口+自创对象名 = new+接口(){方法体});
//==>调用：对象.方法(自创对象名);
		USB phone = new USB(){
			@Override
			public void start() {
				System.out.println("手机开始工作");	
			}
			@Override
			public void stop() {
				System.out.println("手机结束工作");	
			}
		};
		c.doWork(phone);
//==>实现接口的匿名类的对象：方法二
//==>书写格式：对象.方法(匿名对象(){方法体});
		c.doWork(new USB(){
			@Override
			public void start() {
				System.out.println("手机开始工作");	
			}
			@Override
			public void stop() {
				System.out.println("手机结束工作");	
			}
		});
	}
}

//下面的要注意，我并不熟练！！！
class Computer{
	 public void doWork(USB u){
		 u.start();
		 u.stop();
	 }
}
interface USB{
	public abstract void start();
	public abstract void stop();
}
class Flash implements USB{
	@Override
	public void start() {
		System.out.println("U盘开始工作");
	}
	@Override
	public void stop() {
		System.out.println("U盘停止工作");	
	}
}
class Printer implements USB{
	@Override
	public void start() {
		System.out.println("打印机开始工作");	
	}
	@Override
	public void stop() {
		System.out.println("打印机停止工作");	
	}
}