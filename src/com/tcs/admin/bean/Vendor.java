package com.tcs.admin.bean;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="vendor")
public class Vendor {

	/*
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="rich_seq")
	@SequenceGenerator(
			name="rich_seq",
			sequenceName="serial",
			allocationSize =1
			)*/
	
	@Id
	@Column(name="vendor_id")
    private int vendorId;

	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="address")
	private String address;
	
	@Column(name="email")
	private String email;
	
	@Column(name="contact_number")
	private String contactNumber;
	
/*	@Column(name="dob")
	private Date dob;
*/	
	@Column(name="vendor_name")
	private String vendorName;
	
	@Column(name="status")
	private String status;

	
	
	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address
				+ ", email=" + email + ", contactNumber=" + contactNumber
				+ ", vendorName=" + vendorName + ", status=" + status + "]";
	}

	
	/*public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address
				+ ", email=" + email + ", contactNumber=" + contactNumber
				+ ", dob=" + dob + ", vendorName=" + vendorName + ", status="
				+ status + "]";
	}
	
*/	
	
	
	
	
}
