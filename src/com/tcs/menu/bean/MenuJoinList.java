package com.tcs.menu.bean;

import java.util.Date;

import javax.persistence.Column;



public class MenuJoinList {
	private int serialNo;
	
	private String item;
	
	private int vendorId;
	
	private String type;
	
	private String category;
	
	private String subCategory;
	
	private double price;
	
	private String day;
	
	private Date timeStamp;
	
	private int productId;
	
	private double averageRate;
	
	private String vendorName;
	
	private int count;
	
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}


	public double getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(double averageRate) {
		this.averageRate = averageRate;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
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
		return "MenuJoinList [serialNo=" + serialNo + ", item=" + item
				+ ", vendorId=" + vendorId + ", type=" + type + ", category="
				+ category + ", subCategory=" + subCategory + ", price="
				+ price + ", day=" + day + ", timeStamp=" + timeStamp
				+ ", productId=" + productId + ", averageRate=" + averageRate
				+ ", vendorName=" + vendorName + ", count=" + count + "]";
	}

	

	
	
}
