package com.ass.mini2.entity;

public class Result3 {
	private String name;
	private String gender;
	public Result3() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Result3(String name, String gender) {
		super();
		this.name = name;
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Result3 [name=" + name + ", gender=" + gender + "]";
	}
	
}
