package com.tcs.menu.menumapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.tcs.menu.bean.MenuJoinList;

public class MenuJoinListMapper implements RowMapper<MenuJoinList> {

	@Override
	public MenuJoinList mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		
		MenuJoinList menuJoin = new MenuJoinList();
		menuJoin.setSerialNo(rs.getInt("serial_no"));
		menuJoin.setVendorId(rs.getInt("vendor_id"));
		menuJoin.setType(rs.getString("menu_type"));
		menuJoin.setCategory(rs.getString("category"));
		menuJoin.setSubCategory(rs.getString("sub_category"));
		menuJoin.setItem(rs.getString("item"));
		menuJoin.setPrice(rs.getInt("price"));
		menuJoin.setDay(rs.getString("days"));
		menuJoin.setTimeStamp(rs.getTimestamp("selected_on"));
		menuJoin.setProductId(rs.getInt("product_id"));
		menuJoin.setAverageRate(rs.getDouble("average_rate"));
		menuJoin.setVendorName(rs.getString("vendor_name"));
		menuJoin.setCount(rs.getInt("count"));
		
		return menuJoin;	
		
	}

}
