package oop.constructor;
//构造器中使用关键词this的练习：Girl Boy
public class Girl {
	private String name;
	public void setName(String i){
		name = i;
	}
	public String getName(){
		return name;
	}
	public void marry(Boy boy){//这里我不熟练：Boy类型的boy
		System.out.println("我要嫁给" + boy.getName());//Boy类型的boy的名字
		boy.marry(this);//谁调用这个方法，就娶谁，是动态的。
	}
}

class Boy{
	private String name;
	private int age;
	public void setName(String i){
		name = i;
	}
	public String getName(){
		return name;
	}
	public void setAge(int i){
		age = i;
	}
	public int getAge(){
		return age;
	}
	public void marry(Girl girl){//注意！！！
		System.out.println("我要娶" + girl.getName());
	}
	public void shout(){
		if(age >= 22){
			System.out.println("我到结婚年龄了");
		}else{
			System.out.println("我还没到婚龄！");
		}
	}
}
