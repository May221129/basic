package oop.extendstest;
//类的继承练习题-3
public class Student extends Person{
	
	long number;//数字
	int math;//数学
	int english;//英语
	int computer;//计算机
	
	public Student(String name,char sex, int age ,long k, int m, int e, int c){
//		super(name, sex, age)==>调用父类有三个参数的那个构造器
		super();//显式调用构造器，可写可不写
		this.name = name;
		this.sex = sex;
		this.age = age;
		number = k;
		math = m;
		english = e;
		computer = c;
	}
	public double aver(){//平均值，没给详细的说明书，所以自己随便写
		return number/2;
	}
	public int max(){//最大值,没给详细的说明书，所以自己随便写
		return math;
	}
	public int min(){//最小值,没给详细的说明书，所以自己随便写
		return math;
	}
	public void eat(){//父类Person中也有这个方法，这里写了一个重名的方法，表示方法的重写。
		System.out.println("大口大口的吃饭");
	}
//	下面这个方法可以不写，因为在父类中也有，直接继承
//	public String toString(){//输出一些信息，是关于本类的信息，具体要求看说明书
//		return name;
//	}
}
