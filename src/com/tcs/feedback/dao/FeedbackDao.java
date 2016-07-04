package com.tcs.feedback.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcs.feedback.bean.Feedback;
import com.tcs.feedback.bean.FeedbackAction;

public interface FeedbackDao {

	public int addFeedback(Feedback fb);

	public List<ArrayList> getVendor();

	public int getNotificationCount(int id);

	public List<Feedback> getMyFeedback(int value);
	
	public List<Feedback> getAllFeedbackDetails();	
	
	public Feedback getFeedbackDetail(int fId);
	
	public List<FeedbackAction> getAllFeedbackActionDetails(int fId);
	
	public List<ArrayList> getAllValidFeedbackDetails(int eId,int vId);

	void reject(String remark, int feedbackId);

	void accept(int feedbackId, String iCategory);

	List<Feedback> getAllFeedback();

	public List<Feedback> viewMyNotifications(int value);

	public int resolvedFeedbackAction(FeedbackAction feedbackAction);

	public int forwardedFeedbackAction(FeedbackAction feedbackAction);

	public void setStatus(int feedbackId, String status);
	
	public HttpServletResponse generateExcel(HttpServletRequest request,HttpServletResponse response, String status);
	
	

}
