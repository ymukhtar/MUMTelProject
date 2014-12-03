package com.mumtel.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
/**
 * 
 * @author ymukhtar
 * @author aTariq
 *
 */
@Embeddable
public class Address implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7531141046395118359L;
	//@Pattern(regexp="(?<=start)[\\d]+[A-Za-z0-9\\s,\\.]+?[\\d\\-]+|(?<=start)[A-Za-z0-9\\s,\\.]+?(?=end)",message="Street should be valid!")
	@NotEmpty
	private String streetNo;
	//@Pattern(regexp="^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$",message="city should be valid!")
	private String city;
//	@Pattern(regexp="^A[ABELKPRSZ]|BC|C[AOT]|D[EC]|F[LM]|G[ALMU]|HI|I[ADLN]|K[SY]|LA|M[ABDEHINOPSTX]|N[CDEFHJKMSUVWY]|O[HKNRT]|P[AERW]|QC|RI|S[CDN]|T[NX]|U[ST]|V[AIT]|W[AIKVY]|YT$",message="state should be valid!")
	private String state;

	private String zip;
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Address(String streetNo, String city, String state, String zip) {
		super();
		this.streetNo = streetNo;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public String getStreetNo() {
		return streetNo;
	}
	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "Address [streetNo=" + streetNo + ", city=" + city + ", state="
				+ state + ", zip=" + zip + "]";
	}
}