package oop.interfacetest;
/**
 * 接口的应用——工厂方法的设计模式（FactoryMethod）
 * 工厂一般是用来处理生产比较复杂的一个对象的，但是对外，它能够给出一个最终的结果，给到调用者。比如：需要早一个电脑的对象，但是电脑的制作过程非常复杂，这时候就让工厂去制造电脑，制造结果给到调用者。
 */
public class A01FactoryMethod {
	public static void main(String[] args) {
		
		IWorkFactory i1 = new StudentWorkFactory();
		i1.getWork().doWork();
		
		IWorkFactory i2 = new TeacherWorkFactory();
		i2.getWork().doWork();
		
		System.out.println("-------------");
		
		Work w1 = new StudentWork();
		w1.doWork();
		
		Work w2 = new TeacherWork();
		w2.doWork();
	}
}
interface IWorkFactory{//接口：IWorkFactory
	public abstract Work getWork();
}
//下面是有有个工厂，分别生产学生工厂和老师工厂：
class StudentWorkFactory implements IWorkFactory{
	@Override
	public Work getWork() {
		return new StudentWork();
	}
}
class TeacherWorkFactory implements IWorkFactory{
	@Override
	public Work getWork() {
		return new TeacherWork();
	}
}

interface Work {//接口：Work
	public abstract void doWork();
}
class StudentWork implements Work{
	@Override
	public void doWork() {
		System.out.println("学生的工作是学习");
	}
}
class TeacherWork implements Work{
	@Override
	public void doWork() {
		System.out.println("老师的工作是教书育人");
	}
}