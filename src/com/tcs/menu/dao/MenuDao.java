package com.tcs.menu.dao;

import java.util.List;

import com.tcs.admin.bean.Vendor;
import com.tcs.menu.bean.Menu;
import com.tcs.menu.bean.MenuJoinList;
import com.tcs.menu.bean.MenuRatingDetails;




public interface MenuDao 
{
	
	public String AddToDB(String saveDirectory);
	public void deleteMenuList();
	public  List<MenuJoinList> saveRate(int fid,int eid, int star);
	public List<MenuJoinList> getAllMenuWithRating();
	public List<String> getVendorName();
	public List<String> getTypeName();
	/*public List<String> getCategoryName();
	public List<String> getDays();*/
	//public List<Menu> getAllMenu();
	//public  List<MenuJoinList> getMenuByVendor(int vid);
	//public  List<MenuJoinList> getMenuByCategory(String category);
	//public  List<MenuJoinList> getMenuByType(String type);
	//public  List<MenuJoinList> getMenuByDays(String day);
	//public Menu getMenuById(int id);
	//public void insertMenuRating();
	//public MenuRatingDetails getIdByMenu(String name,int vid);
	//public List<MenuRatingDetails> getAllAverage();
	//public void addToExcel();
	}
