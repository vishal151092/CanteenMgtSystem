package com.tcs.feedback.service;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.feedback.bean.Feedback;
import com.tcs.feedback.bean.FeedbackAction;
import com.tcs.feedback.dao.FeedbackDao;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	FeedbackDao feedbackDao;
	FeedbackServiceImpl(){
		System.out.println("Inside FeedbackServiceImpl constructor");
	}
	
	public int addFeedback(Feedback fb) {
		return feedbackDao.addFeedback(fb);
	
		
	}

	@Override
	public List<ArrayList> getVendor() {
		// TODO Auto-generated method stub
		
		return feedbackDao.getVendor();
	}

	@Override
	public int getNotificationCount(int id) {
		// TODO Auto-generated method stub
		return feedbackDao.getNotificationCount(id);
	}

	
	@Override
	public List<Feedback> getMyFeedback(int value) {
		
		return feedbackDao.getMyFeedback(value);
	}

	@Override
	public List<Feedback> getAllFeedbackDetails() {
		
		return feedbackDao.getAllFeedbackDetails();
	}

	@Override
	public List<FeedbackAction> getAllFeedbackActionDetails(int fId) {
		
		return feedbackDao.getAllFeedbackActionDetails(fId);
	}

	@Override
	public Feedback getFeedbackDetail(int fId) {
		// TODO Auto-generated method stub
		return feedbackDao.getFeedbackDetail(fId) ;
	}

	@Override
	public List<ArrayList> getAllValidFeedbackDetails(int eId,int vId) {
		// TODO Auto-generated method stub
		return feedbackDao.getAllValidFeedbackDetails(eId,vId);
	}

	@Override
	public List<Feedback> getAllFeedback() {
		// TODO Auto-generated method stub
		return feedbackDao.getAllFeedback();
	}

	@Override
	public void accept(int feedbackId, String category1) {
		// TODO Auto-generated method stub
		feedbackDao.accept(feedbackId, category1);
	}

	@Override
	public void reject(String remark, int feedbackId) {
		// TODO Auto-generated method stub
		feedbackDao.reject(remark, feedbackId);
	}

	
	
	@Override
	public List<Feedback> viewMyNotifications(int value) {
		
		return feedbackDao.viewMyNotifications(value);
	}

	@Override
	public int resolvedFeedbackAction(FeedbackAction feedbackAction) {
		// TODO Auto-generated method stub
		return feedbackDao.resolvedFeedbackAction(feedbackAction);
	}

	@Override
	public int forwardedFeedbackAction(FeedbackAction feedbackAction) {
		// TODO Auto-generated method stub
		return feedbackDao.forwardedFeedbackAction(feedbackAction);
	}

	@Override
	public void setStatus(int feedbackId, String status) {
		// TODO Auto-generated method stub
		feedbackDao.setStatus(feedbackId,status);
		
	}

	@Override
	public HttpServletResponse generateExcel(HttpServletRequest request,HttpServletResponse response,String status) {
		return feedbackDao.generateExcel(request,response,status);	
	}
}