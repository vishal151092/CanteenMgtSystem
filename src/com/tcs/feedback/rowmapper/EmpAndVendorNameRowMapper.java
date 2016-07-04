package com.tcs.feedback.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;




public class EmpAndVendorNameRowMapper implements RowMapper<ArrayList> {
	@Override
	public ArrayList mapRow(ResultSet result, int rowSeq) throws SQLException {
		
		ArrayList vendorList=new ArrayList();
		
		
		vendorList.add(result.getString("emp_name"));
		vendorList.add(result.getString("vendor_name"));
	
		return vendorList;
	}
	

}
