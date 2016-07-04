package com.tcs.menu.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="menu_rating_details")
public class MenuRatingDetails {

	@Id
	@Column(name="product_id")
	/*@SequenceGenerator(name="productIdGeneration", sequenceName="product_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="productIdGeneration")*/
	private int productId;
	
	@Column(name="item_name")
	private String itemName;
	
	@Column(name="vendor_id")
	private int vendorId;
	
	@Column(name="average_rate")
	private double averageRate;
	
	@Column(name="count")
	private int count;
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getAverageRate() {
		return averageRate;
	}
	public void setAverageRate(double averageRate) {
		this.averageRate = averageRate;
	}
	@Override
	public String toString() {
		return "MenuRatingDetails [productId=" + productId + ", itemName="
				+ itemName + ", vendorId=" + vendorId + ", averageRate="
				+ averageRate + ", count=" + count + "]";
	}
	
	
}
