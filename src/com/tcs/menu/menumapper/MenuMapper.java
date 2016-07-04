package com.tcs.menu.menumapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tcs.menu.bean.Menu;

public class MenuMapper implements RowMapper<String> {

	@Override
	public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		/*Menu menu=new Menu();
		menu.setSerialNo(rs.getInt("serial_no"));
		menu.setVendorId(rs.getInt("vendor_id"));
		menu.setCategory(rs.getString("category"));
		menu.setSubCategory(rs.getString("sub_category"));*/
		return rs.getString("menu_type");
		/*menu.setItem(rs.getString("item"));
		menu.setPrice(rs.getDouble("price"));
		menu.setDay(rs.getString("days"));
		menu.setTimeStamp(rs.getTimestamp("selected_on"));*/
		
	}

}
