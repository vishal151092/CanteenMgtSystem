package com.tcs.feedback.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tcs.feedback.bean.FeedbackAction;

public class FeedbackActionRowMapper implements RowMapper<FeedbackAction> {

	@Override
	public FeedbackAction mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		FeedbackAction fa = new FeedbackAction();
		
		fa.setFeedbackId(rs.getInt(1));
//		fa.setOwnerId(rs.getInt(2));
		fa.setAction(rs.getString(2));
//		fa.setForwardedToId(rs.getInt(4));
		fa.setRemark(rs.getString(3));
		fa.setUserMsg(rs.getString(4));
		fa.setActionSumbittedOn(rs.getDate(5));
		
		return fa;
	}

}
