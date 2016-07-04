package com.tcs.feedback.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="feedback")	
public class Feedback {
	
	@Id
	@Column(name="feedback_id")
	private int feedbackId;
	
	@Column(name="emp_id")
	private int empId;
	@Column(name="vendor_id")
	private int vendorId;
	@Column(name="category")
	private String category;
	@Column(name="problem")
	private String problem;
	@Column(name="suggestion")
	private String suggestion;
	@Column(name="submitted_on")
	private Date submittedOn;
	@Column(name="status")
	private String status;
/*	@Column(name="moderator_remark")
	private String moderatorRemark;
*/	
	
	
	
	@Column(name="filter_remark")
	private String filterRemark;
	
	@Column(name="filtered_on")
	private Date filteredOn;
	
	private String userMsg;
	
	/*public Feedback(int feedbackId, int empId, int vendeorId,
			String category, String problem, String suggestion,
			Date submittedOn, String status, String moderatorRemark) {
		super();
		this.feedbackId = feedbackId;
		this.empId = empId;
		this.vendorId = vendeorId;
		this.category = category;
		this.problem = problem;
		this.suggestion = suggestion;
		this.submittedOn = submittedOn;
		this.status = status;
		this.moderatorRemark = moderatorRemark;
	}*/
	
	public Feedback(){};
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public Date getSubmittedOn() {
		return submittedOn;
	}
	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

/*	public String getModeratorRemark() {
		return moderatorRemark;
	}
	public void setModeratorRemark(String moderatorRemark) {
		this.moderatorRemark = moderatorRemark;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", empId=" + empId
				+ ", vendorId=" + vendorId + ", category=" + category
				+ ", problem=" + problem + ", suggestion=" + suggestion
				+ ", submittedOn=" + submittedOn + ", status=" + status
				+ ", moderatorRemark=" + moderatorRemark + "]/n";
	}
		
*/	

	public String getFilterRemark() {
		return filterRemark;
	}
	public void setFilterRemark(String filterRemark) {
		this.filterRemark = filterRemark;
	}
	public Date getFilteredOn() {
		return filteredOn;
	}
	public void setFilteredOn(Date filteredOn) {
		this.filteredOn = filteredOn;
	}
	
	
	public String getUserMsg() {
		return userMsg;
	}
	public void setUserMsg(String userMsg) {
		this.userMsg = userMsg;
	}
	
	
	
	
	
}
