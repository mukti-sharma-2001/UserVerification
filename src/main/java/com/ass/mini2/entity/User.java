package com.ass.mini2.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int user_id;
	private String name;
	private Integer age;
	private String gender;
	private String DOB;
	private String verificationStatus;
	private Date dateCreated;
	private Date dateUpdated;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String name, Integer age, String gender, String DOB, String verificationStatus) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.DOB = DOB;
		this.verificationStatus = verificationStatus;
		this.dateCreated = new Date();
		this.dateUpdated = new Date();
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getVerificationStatus() {
		return verificationStatus;
	}
	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", DOB=" + DOB
				+ ", verificationStatus=" + verificationStatus + ", dateCreated=" + dateCreated + ", dateUpdated="
				+ dateUpdated + "]";
	}
}
