package com.ass.mini2.entity;

public class Result {
	private String gender;
	private String name;
	private String first;
	private String nat;
	private String dob;
	private Integer age;
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Result(String gender, String name, String first, String nat,String dob,Integer age) {
		super();
		this.gender = gender;
		this.name = name;
		this.first = first;
		this.nat = nat;
		this.dob=dob;
		this.age=age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getNat() {
		return nat;
	}
	public void setNat(String nat) {
		this.nat = nat;
	}
	
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Result [gender=" + gender + ", name=" + name + ", first=" + first + ", nat=" + nat + ", dob=" + dob
				+ ", age=" + age + "]";
	}
	
	
	
	
	
}
