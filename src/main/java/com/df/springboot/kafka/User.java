package com.df.springboot.kafka;

public class User {
	
	private String name;
	private String dept;
	private Float salary;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", dept=" + dept + ", salary=" + salary + "]";
	}
	public User(String name, String dept, Float salary) {
		super();
		this.name = name;
		this.dept = dept;
		this.salary = salary;
	}
	
	public User() {
		
	}

}
