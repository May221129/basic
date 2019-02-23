package oop.constructor;

//复习题：巩固构造器、this()的使用
public class Customer {
	private String firstName;
	private String lastName;
	private Account account;
	
	public Customer(String f,String l){
		firstName = f;
		lastName = l;
	}
	
	public String getFirstName(){
		return firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public Account getAccnount(){
		return account;
	}
	public void setAccount(Account account){
		this.account = account;
	}

}
