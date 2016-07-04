package com.tcs.feedback.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tcs.feedback.bean.Feedback;



public class FeedbackListRowMapper implements RowMapper<Feedback> {
	@Override
	public Feedback mapRow(ResultSet result, int rowSeq) throws SQLException {
		Feedback feed = new Feedback();
		
		
		feed.setFeedbackId(result.getInt("feedback_id"));
		feed.setEmpId(result.getInt("emp_id"));
		feed.setVendorId(result.getInt("vendor_id"));
		feed.setCategory(result.getString("category"));
		feed.setProblem(result.getString("problem"));
		feed.setSuggestion(result.getString("suggestion"));
		feed.setSubmittedOn(result.getDate("submitted_on"));
		feed.setStatus(result.getString("status"));
//		feed.setFilterRemark(result.getString("filter_remark"));
		feed.setFilteredOn(result.getDate("filtered_on"));
		if(result.getString("usermsg")!=null){
		feed.setUserMsg(result.getString("usermsg"));
		}
		else{
		feed.setUserMsg(result.getString("user_msg"));
		}

		
		return feed;
	}
}
