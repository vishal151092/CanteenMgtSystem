package com.tcs.menu.menumapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.tcs.menu.bean.MenuRate;

public class MenuRatingMapper implements RowMapper<MenuRate> 
{

	@Override
	public MenuRate mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		MenuRate menuRate=new MenuRate();
		menuRate.setCount(rs.getInt("count"));
		menuRate.setAverage(rs.getDouble("avg"));
		
		return menuRate;
	}

}
