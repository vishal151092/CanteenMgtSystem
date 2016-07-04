package com.tcs.menu.menumapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.tcs.menu.bean.StarRating;

public class StarMapper implements RowMapper<StarRating> {
	

	@Override
	public StarRating mapRow(ResultSet rs, int arg1) throws SQLException {
		
		StarRating st= new StarRating();
		st.setProductId(rs.getInt("product_id"));
		st.setUserId(rs.getInt("user_id"));
		st.setRating(rs.getInt("Rating"));
		
		return st;
	}


}
