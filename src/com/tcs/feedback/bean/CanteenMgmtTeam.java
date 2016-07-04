package com.tcs.feedback.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name="canteen_mgmt_team")
public class CanteenMgmtTeam {
	
	@Id
	@Column(name="employee_id")
	private int  empId;
	
	

	@Column(name="category")
	private String category;
	
	
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
	
	

}
