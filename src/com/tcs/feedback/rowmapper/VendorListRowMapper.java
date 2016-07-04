package com.tcs.feedback.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;




public class VendorListRowMapper implements RowMapper<ArrayList> {
	@Override
	public ArrayList mapRow(ResultSet result, int rowSeq) throws SQLException {
		
		ArrayList vendorList=new ArrayList();
		
		
		vendorList.add(result.getString("vendor_name"));
		vendorList.add(result.getInt("vendor_id"));
		
		
//		Vehicle bk = new Vehicle();
//		bk.setVehicleNo(String.valueOf(result.getString("vehicleNo")));
//		bk.setChasisNo(result.getString("chasisNo"));
//		bk.setEngineNo(result.getString("engineNo"));
//		bk.setName(result.getString("name"));
//		bk.setAddress(result.getString("address"));
//		return bk;
	
		return vendorList;
	}
	

}
