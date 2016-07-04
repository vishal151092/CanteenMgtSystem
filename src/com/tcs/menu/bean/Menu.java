package com.tcs.menu.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu_list")
public class Menu {
	@Id
	@Column(name="serial_no")
	private int serialNo;
	
	@Column(name="item")
	private String item;
	
	@Column(name="vendor_id")
	private int vendorId;
	
	@Column(name="menu_type")
	private String type;
	
	@Column(name="category")
	private String category;
	
	@Column(name="sub_category")
	private String subCategory;
	
	@Column(name="price")
	private double price;
	
	@Column(name="days")
	private String day;
	
	@Column(name="selected_on")
	private Date timeStamp;
	


	public Menu() {
		super();
	}


	public Menu(String item, int vendorId, String type,
			String category, String subCategory, double price, String day,
			Date timeStamp) {
		super();
		
		this.item = item;
		this.vendorId = vendorId;
		this.type = type;
		this.category = category;
		this.subCategory = subCategory;
		this.price = price;
		this.day = day;
		this.timeStamp = timeStamp;
	}


	public int getSerialNo() {
		return serialNo;
	}


	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}


	public int getVendorId() {
		return vendorId;
	}


	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}


	public String getType() {
		return type;
	}


	public String getItem() {
		return item;
	}


	public void setItem(String item) {
		this.item = item;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getSubCategory() {
		return subCategory;
	}


	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}

	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}


	@Override
	public String toString() {
		return "Menu [serialNo=" + serialNo + ", item=" + item + ", vendorId="
				+ vendorId + ", type=" + type + ", category=" + category
				+ ", subCategory=" + subCategory + ", price=" + price
				+ ", day=" + day + ", timeStamp=" + timeStamp + "]";
	}


	

}
