package com.mfs.ussd;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Person {
	private @Id @GeneratedValue Long id;
	private String Name;
	private String IdNumber;
	private String PhoneNumber;
	
	  Person() {}
	
	  Person(String name, String idNumber) {
	    this.setName(name);
	    this.setIdNumber(idNumber);
	  }

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getIdNumber() {
		return IdNumber;
	}

	public void setIdNumber(String idNumber) {
		IdNumber = idNumber;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
}
