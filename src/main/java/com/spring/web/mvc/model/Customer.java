package com.spring.web.mvc.model;

public class Customer {

	String name;
	String email;
	String gender;
	String city;
	String mobile;
	String photo;
	
	public Customer(){
		
	}

	public Customer(String name, String email, String gender, String city, String mobile, String photo) {
		super();
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.city = city;
		this.mobile = mobile;
		this.photo = photo;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", email=" + email + ", gender=" + gender + ", city=" + city + ", mobile="
				+ mobile + ", photo=" + photo + "]";
	}


}
