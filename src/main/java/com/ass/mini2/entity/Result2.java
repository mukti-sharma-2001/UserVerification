package com.ass.mini2.entity;

import java.util.List;

public class Result2 {
	private String name;
	private List<String> countryId;
	
	public Result2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Result2(String name, List<String> countryId) {
		super();
		this.name = name;
		this.countryId = countryId;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getCountryId() {
		return countryId;
	}

	public void setCountryId(List<String> countryId) {
		this.countryId = countryId;
	}

	@Override
	public String toString() {
		return "Result2 [name=" + name + ", countryId=" + countryId + "]";
	}
	
	
}
