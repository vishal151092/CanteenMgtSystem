package com.tcs.feedback.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;




public class FeedbackListUserMsgRowMapper implements RowMapper<ArrayList> {
	@Override
	public ArrayList mapRow(ResultSet result, int rowSeq) throws SQLException {
		
		ArrayList list=new ArrayList();
		
		
		list.add(result.getInt("feedback_id"));
		list.add(result.getInt("emp_id"));
		list.add(result.getInt("vendor_id"));
		list.add(result.getString("category"));
		list.add(result.getString("problem"));
		list.add(result.getString("suggestion"));
		list.add(result.getDate("submitted_on"));
		list.add(result.getString("status"));
		list.add(result.getString("filter_remark"));
		
		
		list.add(result.getString("emp_name"));
		list.add(result.getString("vendor_name"));
	
		return list;
	}
	

}
