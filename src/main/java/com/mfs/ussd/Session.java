package com.mfs.ussd;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Session {
	private @Id @GeneratedValue Long id;
	private String Name;
	private String phoneNumber;
	
	Session() {}
	
	  Session(String name, String phoneNumber) {
	    this.setName(name);
	    this.setPhoneNumber(phoneNumber);
	  }

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
