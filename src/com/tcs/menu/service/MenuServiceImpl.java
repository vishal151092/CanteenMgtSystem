package com.tcs.menu.service;

import java.util.List;

import javax.swing.event.MenuListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.admin.bean.Vendor;
import com.tcs.menu.bean.Menu;
import com.tcs.menu.bean.MenuJoinList;
import com.tcs.menu.bean.MenuRatingDetails;
import com.tcs.menu.dao.MenuDao;

@Service
public class MenuServiceImpl implements MenuService
{
	@Autowired
	private MenuDao menuDao;
	

	public String AddToDB(String saveDirectory)
	{
		return menuDao.AddToDB(saveDirectory);
	}
	public void deleteMenuList(){
		
		menuDao.deleteMenuList();
	}
	public List<String> getVendorName()
	{
		return menuDao.getVendorName();
	}

	@Override
	public List<MenuJoinList> saveRate(int fid, int eid, int rating) {
		return menuDao.saveRate(fid,eid,rating);
	}
	
	@Override
	public List<MenuJoinList> getAllMenuWithRating() {
		return menuDao.getAllMenuWithRating();
	}
	@Override
	public List<String> getTypeName() {
		return menuDao.getTypeName();
	}
	/*@Override
	public List<String> getCategoryName() {
		return menuDao.getCategoryName();
	}
	@Override
	public List<String> getDays() {
		return menuDao.getDays();
	}*/
	
	/*@Override
	public List<Menu> getAllMenu() {
		
		return menuDao.getAllMenu();
	}*/

	
	/*@Override
	public List<MenuJoinList> getMenuByVendor(int vid) 
	{
		return menuDao.getMenuByVendor(vid);
	}


	@Override
	public List<MenuJoinList> getMenuByCategory(String category) {
		return menuDao.getMenuByCategory(category);
	}


	@Override
	public List<MenuJoinList> getMenuByType(String type) {
		return menuDao.getMenuByType(type);
	}*/


	/*@Override
	public Menu getMenuById(int id) {
		return menuDao.getMenuById(id);
	}*/

	/*@Override
	public List<MenuJoinList> getMenuByDay(String day) {
		return menuDao.getMenuByDays(day);
	}*/
	
	
	/*@Override
	public MenuRatingDetails getIdByMenu(String name, int vid) {
		
		return menuDao.getIdByMenu(name, vid);
	}*/
	
	/*@Override
	public List<MenuRatingDetails> getAllAverage() {
		
		return menuDao.getAllAverage();
	}*/
	
	
	/*@Override
	public void addToExcel() {
		menuDao.addToExcel();
	}*/

}
