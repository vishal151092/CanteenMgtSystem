package com.tcs.feedback.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="feedback_action")	
public class FeedbackAction {	

	@Id
	@Column(name="feedback_id")
	private int feedbackId;
	
/*	@Id
	@Column(name="owner_id")
	private int ownerId;
*/	
	@Column(name="action")
	private String action;
	
/*	@Column(name="forwarded_to_id")
	private int forwardedToId;
*/	
	@Column(name="action_remark")
	private String remark;
	
	@Column(name="user_msg")
	private String userMsg;
	
	
	
	@Column(name="action_submitted_on")
	private Date actionSumbittedOn;
	
	
	public String getUserMsg() {
		return userMsg;
	}
	public void setUserMsg(String userMsg) {
		this.userMsg = userMsg;
	}
	
	
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
/*	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
*/	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
/*	public int getForwardedToId() {
		return forwardedToId;
	}
	public void setForwardedToId(int forwardedToId) {
		this.forwardedToId=forwardedToId;	
	}
*/
	public String getRemark() {
		return remark;
	}
	public void setRemark(String string) {
		this.remark = string;
	}
	public Date getActionSumbittedOn() {
		return actionSumbittedOn;
	}
	public void setActionSumbittedOn(Date actionSumbittedOn) {
		this.actionSumbittedOn = actionSumbittedOn;
	}
	@Override
	public String toString() {
		return "FeedbackAction [feedbackId=" + feedbackId + ", action="
				+ action + ", remark=" + remark + ", userMsg=" + userMsg
				+ ", actionSumbittedOn=" + actionSumbittedOn + "]";
	}
	
/*	@Override
	public String toString() {
		return "FeedbackAction [feedbackId=" + feedbackId + ", ownerId="
				+ ownerId + ", action=" + action + ", forwardedToId="
				+ forwardedToId + ", remark=" + remark + ", actionSumbittedOn="
				+ actionSumbittedOn + "]/n";
	}	
*/
}
