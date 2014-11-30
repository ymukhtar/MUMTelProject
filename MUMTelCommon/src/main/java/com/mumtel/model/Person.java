package com.mumtel.model;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

/**
 * 
 * @author yasirmukhtar
 * @author aTariq
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8270340077867540975L;
	
	@GeneratedValue
	@Id
	private long personID;
	@Pattern(regexp="([a-zA-Z]{3,30}\\s*)+",message="first name should be valid!")
	private String firstName;
	@Pattern(regexp="([a-zA-Z]{3,30}\\s*)+",message="last name should be valid!")
	private String lastName;
	@Email
	private String emailAddress;
	@Embedded
	private Address address;
	
	public Person(){
		
	}
	public Person(String firstName, String lastName,
			String emailAddress) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}
	
	
	public long getPersonID() {
		return personID;
	}
	public void setPersonID(long personID) {
		this.personID = personID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAddress="
				+ emailAddress + "]";
	}
	
	
	
}