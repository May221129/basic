package jdbc.test;

import java.sql.Date;

public class Customer {

	private Integer id;
	private String name;
	private Date birthday;
	private String email;
	
	public Customer(Integer id, String name, Date birthday, String email) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.email = email;
	}
	public Customer() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerName() {
		return name;
	}

	public void setCustomerName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirth(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", birthday=" + birthday
				+  ", email=" + email + "]";
	}
}
