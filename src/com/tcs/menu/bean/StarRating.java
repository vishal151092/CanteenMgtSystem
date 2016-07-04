package com.tcs.menu.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="star_rating")
public class StarRating {
	
	@Id
	@Column(name="product_id")
	private int productId;
	
	@Id
	@Column(name="user_id")
	private int userId;
	
	@Column(name="rating")
	private int rating;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "StarRating [productId=" + productId + ", userId=" + userId
				+ ", rating=" + rating + "]";
	}

	
}
