package oop.maintest;
//main知识的练习
//目的：通过"Run As ==> Run Configurations… ==> Arguments"或DOS命令行与程序之间的进行交互，体会main这个主方法的使用
public class A01Main {
	public static void main(String[] args) {
		for(int i = 0; i < args.length; i++){
			System.out.println(args[i]);
		}
	}
}
