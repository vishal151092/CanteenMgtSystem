package com.tcs.menu.menumapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tcs.admin.bean.Vendor;

public class VendorMapper implements RowMapper<Vendor> {

	@Override
	public Vendor mapRow(ResultSet rs, int rowNumber) throws SQLException {
		
		Vendor vendor = new Vendor();
		vendor.setVendorId(rs.getInt("vendor_id"));
		vendor.setFirstName(rs.getString("first_name"));
		vendor.setLastName(rs.getString("last_name"));
		vendor.setAddress(rs.getString("address"));
		vendor.setEmail(rs.getString("email"));
		vendor.setContactNumber(rs.getString("contact_number"));
		vendor.setVendorName(rs.getString("vendor_name"));
		vendor.setStatus(rs.getString("status"));
		return vendor;
	}

}
