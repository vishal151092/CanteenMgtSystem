package com.tcs.menu.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tcs.admin.bean.Vendor;
import com.tcs.menu.bean.Menu;
import com.tcs.menu.bean.MenuJoinList;
import com.tcs.menu.bean.MenuRate;
import com.tcs.menu.bean.MenuRatingDetails;
import com.tcs.menu.bean.StarRating;
import com.tcs.menu.menumapper.MenuJoinListMapper;
import com.tcs.menu.menumapper.MenuMapper;
import com.tcs.menu.menumapper.MenuRatingMapper;
import com.tcs.menu.menumapper.StarMapper;
import com.tcs.menu.menumapper.VendorMapper;



@Repository
public class MenuDaoImpl implements MenuDao {
	@Autowired
	private SessionFactory sessionfactory;

	DataSource dataSource;
	JdbcTemplate jdbcTemplate;

	List<Menu> menuList;
	List<MenuJoinList> menuJoinList;
	List<MenuRatingDetails> menuRating;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String AddToDB(String saveDirectory) {

		
		
			try {
				FileInputStream input = new FileInputStream(saveDirectory+"/"+"menu.xls");
				POIFSFileSystem fs = new POIFSFileSystem(input);
				HSSFWorkbook wb = new HSSFWorkbook(fs);
				HSSFSheet sheet = wb.getSheetAt(0);
				Row row;
				Date date = new Date();
				

				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					row = sheet.getRow(i);

					
					//int serialNo = (int) row.getCell(0).getNumericCellValue();
					int vendorID = (int) row.getCell(0).getNumericCellValue();
					String type = row.getCell(1).getStringCellValue();
					String category = row.getCell(2).getStringCellValue();
					String subCategory = row.getCell(3).getStringCellValue();
					String item = row.getCell(4).getStringCellValue();
					double price = (double) row.getCell(5).getNumericCellValue();
					//int quantity = (int) row.getCell(7).getNumericCellValue();
					String day = row.getCell(6).getStringCellValue();
					Timestamp timestamp = new Timestamp(date.getTime());
					System.out.println("Import rows " + i);
					
					Menu menu = new Menu(item,vendorID,type,category,subCategory,price,day,timestamp);
					
						System.out.println(menu);
					jdbcTemplate.update(
							"INSERT INTO menu_list(vendor_id,menu_type,category,sub_category,item,price,days,selected_on) VALUES (?,initcap(?),initcap(?),initcap(?),initcap(?),?,initcap(?),?)",
							new Object[] { menu.getVendorId(),
									menu.getType(), menu.getCategory(),
									menu.getSubCategory(), menu.getItem(),
									menu.getPrice(), menu.getDay(),
									menu.getTimeStamp() });
					
				}
				
				jdbcTemplate.update(
						"INSERT INTO menu_rating_details (vendor_id, item_name,average_rate) SELECT vendor_id,item,0.0 FROM menu_list m where not exists (select vendor_id,item_name from menu_rating_details where vendor_id= m.vendor_id and item_name = m.item)");
			
			/*} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
		
		} catch (Exception e) {
			System.out.println("Mismatched column name or column values.");
			return "Invalid data.";
		}

		//insertMenuRating();
		return "Menu added successfully.";
	}

	
	public List<MenuJoinList> getAllMenuWithRating() {
		String sql = "select menu_list.serial_no,vendor.vendor_name,menu_list.vendor_id ,menu_list.menu_type ,menu_list.category ,menu_list.sub_category ,menu_list.item ,menu_list.price , menu_list.days, menu_list.selected_on, menu_rating_details.product_id, menu_rating_details.average_rate,menu_rating_details.count from menu_list,menu_rating_details,vendor where menu_list.vendor_id=menu_rating_details.vendor_id and menu_list.item=menu_rating_details.item_name and menu_list.vendor_id=vendor.vendor_id and vendor.status=?  ORDER BY \n CASE \nWHEN days ='Sunday' THEN 7 \nWHEN days ='Monday' THEN 1 \n WHEN days ='Tuesday' THEN 2 \n WHEN days ='Wednesday' THEN 3 \n WHEN days ='Thursday' THEN 4 \n WHEN days ='Friday' THEN 5 \n WHEN days ='Saturday' THEN 6 \n END ASC";

		menuJoinList = jdbcTemplate.query(sql,new Object[]{"Active"}, new MenuJoinListMapper());
		for (MenuJoinList menu : menuJoinList) {
			System.out.println("menu with vendor"+menu);
		}
		return menuJoinList;
	}
	public void deleteMenuList() {

		jdbcTemplate.update("DELETE From menu_list");
		System.out.println("DataBase cleared.....");
	}


	@Override
	public List<String> getVendorName() {
		String sql = "select * from vendor where status=?";

		List<Vendor> venList= jdbcTemplate.query(sql,new Object[]{"Active"},new VendorMapper());
		List<String> venName=new ArrayList<String>();
		for (Vendor vendor : venList) 
		{
			venName.add(vendor.getVendorName());
		}
		return venName;
	}
	
	@Override
	public List<String> getTypeName() {
		String sql = "select distinct menu_type from menu_list";

		List<String> menList= jdbcTemplate.query(sql,new MenuMapper());
		/*List<String> menuType=new ArrayList<String>();
		for (Menu menu : menList) 
		{
			menuType.add(menu.getType());
		}*/
		return menList;
	}
	
	/*@Override
	public List<String> getCategoryName() {
		String sql = "select * from menu_list";

		List<Menu> menList= jdbcTemplate.query(sql,new Object[]{},new MenuMapper());
		List<String> menuCategory=new ArrayList<String>();
		for (Menu menu : menList) 
		{
			menuCategory.add(menu.getType());
		}
		return menuCategory;
	}
	
	@Override
	public List<String> getDays() {
		String sql = "select * from menu_list";

		List<Menu> menList= jdbcTemplate.query(sql,new Object[]{},new MenuMapper());
		List<String> menuDays=new ArrayList<String>();
		for (Menu menu : menList) 
		{
			menuDays.add(menu.getType());
		}
		return menuDays;
	}*/
	

	@Override
	public List<MenuJoinList> saveRate(int fid, int eid, int star) {
		int j = 0;
		System.out.println("inserting data");

		List<StarRating> res = null;

		String sq = "select * from star_rating";
		res = jdbcTemplate.query(sq, new StarMapper());
		System.out.println("size:" + res.size());
		int x = 0;
		while (x < res.size()) {
			if (res.get(x).getProductId() == fid) {
				if (res.get(x).getUserId() == eid) {
					System.out.println("inside if.update");
					String sql1 = "update star_rating set rating=? where product_id=? and user_id=?";
					j = jdbcTemplate.update(sql1, star, fid, eid);
				}
			}

			x++;
		}

		if (j == 0) {
			String sql = "insert into star_rating (product_id,user_id,rating) values(?,?,?)";
			j = jdbcTemplate.update(sql, fid, eid, star);

		}

		System.out.println("Getting data"+fid);

		String sql = "select count(*),avg(rating) from star_rating where product_id=?";
		MenuRate menu = (MenuRate) jdbcTemplate.queryForObject(sql,new Object[]{fid}, new MenuRatingMapper());
	
		//System.out.println("avg:" + avg);

		String sql1 = "update menu_rating_details set average_rate=?,count=? where product_id=?";
		j = jdbcTemplate.update(sql1, menu.getAverage(),menu.getCount(), fid);
		System.out.println("no. of rows affected by avg:" + j);
		return getAllMenuWithRating();

	}
	/*@Override
	public Menu getMenuById(int id) {
		Session session = sessionfactory.openSession();
		Transaction trans = null;
		trans = session.beginTransaction();
		Criteria cr = session.createCriteria(Menu.class);
		Menu menu = (Menu) cr.add(Restrictions.eq("itemId", id)).uniqueResult();
		trans.commit();
		return menu;
	}*/
	/*public List<MenuRatingDetails> getAllMenuRatingDetails() {
	Session session = sessionfactory.openSession();
	Transaction trans = null;
	trans = session.beginTransaction();
	List<MenuRatingDetails> ratingList = session.createQuery(
			"From MenuRatingDetails").list();
	trans.commit();
	return ratingList;
}*/

/*public List<Menu> getAllMenu() {

	Session session = sessionfactory.openSession();
	Transaction trans = null;
	trans = session.beginTransaction();
	menuList = session.createQuery("From Menu").list();
	trans.commit();
	return menuList;

}*/
	
	/*@Override
	public void insertMenuRating() {

		
		menuList = getAllMenu();
		menuRating = getAllMenuRatingDetails();

		if (menuRating.size() == 0) {
			MenuRatingDetails menuDetail = new MenuRatingDetails();
			
			System.out.println("inside menu rating if");
			for (Menu menu : menuList) {
				System.out.println(menu);
				System.out.println("inside menulist for");

				menuDetail.setItemName(menu.getItem());
				menuDetail.setVendorId(menu.getVendorId());
				menuDetail.setAverageRate(0.0);

				String sql = "insert into menu_rating_details (item_name,average_rate,vendor_id) values(?,?,?)";
				int id = jdbcTemplate.update(sql, menu.getItem(), 0.0,
						menu.getVendorId());
				System.out.println("id inserted :" + id);
				
			}
		} else {
			
			MenuRatingDetails menuDetail = new MenuRatingDetails();
			boolean status = false;
			for (Menu menu : menuList) {
				for (MenuRatingDetails menudetail : menuRating) {
					System.out.println(menudetail);
					if (((!(menu.getItem().equalsIgnoreCase(menudetail
							.getItemName()))) && menu.getVendorId() == menudetail
							.getVendorId())
							|| ((menu.getItem().equalsIgnoreCase(menudetail
									.getItemName())) && (menu.getVendorId() != menudetail
									.getVendorId()))
							|| ((!(menu.getItem().equalsIgnoreCase(menudetail
									.getItemName()))) && menu.getVendorId() != menudetail
									.getVendorId())) {
						status = true;
						menuDetail = new MenuRatingDetails();
						menuDetail.setItemName(menu.getItem());
						menuDetail.setVendorId(menu.getVendorId());
						menuDetail.setAverageRate(0.0);
					}
					
				}
			
			}
			if (status) {
				String sql = "insert into menu_rating_details (item_name,average_rate,vendor_id) values(?,?,?)";
				int id = jdbcTemplate.update(sql, menuDetail.getItemName(),
						0.0, menuDetail.getVendorId());
				System.out.println("id inserted :" + id);
				status = false;
			}

		}

	}*/
	
	
	/*@Override
	public MenuRatingDetails getIdByMenu(String name, int vid) {
		System.out.println("name:" + name + "vendor id:" + vid);

		Session session = sessionfactory.openSession();
		Transaction trans = null;
		trans = session.beginTransaction();
		Criteria cr = session.createCriteria(MenuRatingDetails.class);
		cr.add(Restrictions.eq("itemName", name));
		cr.add(Restrictions.eq("vendorId", vid));
		MenuRatingDetails menu = (MenuRatingDetails) cr.uniqueResult();
		trans.commit();
		System.out.println("from DAO " + menu);

		return menu;
	}*/

	/*@Override
	public List<MenuRatingDetails> getAllAverage() {

		Session session = sessionfactory.openSession();
		Transaction trans = null;

		trans = session.beginTransaction();
		menuRating = session.createQuery("From MenuRatingDetails ").list();
		trans.commit();
		return menuRating;
	}*/
	
	/*@Override
	public List<MenuJoinList> getMenuByVendor(int vid) {
		String sql = "select menu_list.vendor_id ,menu_list.menu_type ,menu_list.category ,menu_list.sub_category ,menu_list.item ,menu_list.price , menu_list.days, menu_list.selected_on, menu_rating_details.product_id, menu_rating_details.average_rate from menu_list,menu_rating_details where menu_list.vendor_id=menu_rating_details.vendor_id and menu_list.item=menu_rating_details.item_name and menu_list.vendor_id=?";

		menuJoinList = jdbcTemplate.query(sql,new Object[]{vid}, new MenuJoinListMapper());

		return menuJoinList;
	}

	@Override
	public List<MenuJoinList> getMenuByCategory(String category) {

		String sql = "select menu_list.vendor_id ,menu_list.menu_type ,menu_list.category ,menu_list.sub_category ,menu_list.item ,menu_list.price , menu_list.days, menu_list.selected_on, menu_rating_details.product_id, menu_rating_details.average_rate from menu_list,menu_rating_details where menu_list.vendor_id=menu_rating_details.vendor_id and menu_list.item=menu_rating_details.item_name and menu_list.category=?";

		menuJoinList = jdbcTemplate.query(sql,new Object[]{category}, new MenuJoinListMapper());
		return menuJoinList;
	}

	@Override
	public List<MenuJoinList> getMenuByType(String type) {
		String sql = "select menu_list.vendor_id ,menu_list.menu_type ,menu_list.category ,menu_list.sub_category ,menu_list.item ,menu_list.price , menu_list.days, menu_list.selected_on, menu_rating_details.product_id, menu_rating_details.average_rate from menu_list,menu_rating_details where menu_list.vendor_id=menu_rating_details.vendor_id and menu_list.item=menu_rating_details.item_name and menu_list.menu_type=?";

		menuJoinList = jdbcTemplate.query(sql,new Object[]{type}, new MenuJoinListMapper());
		return menuJoinList;
	}
*/
	

	/*@Override
	public List<MenuJoinList> getMenuByDays(String day) {

		String sql = "select menu_list.vendor_id ,menu_list.menu_type ,menu_list.category ,menu_list.sub_category ,menu_list.item ,menu_list.price , menu_list.days, menu_list.selected_on, menu_rating_details.product_id, menu_rating_details.average_rate from menu_list,menu_rating_details where menu_list.vendor_id=menu_rating_details.vendor_id and menu_list.item=menu_rating_details.item_name and menu_list.days=?";
		menuJoinList = jdbcTemplate.query(sql,new Object[]{day}, new MenuJoinListMapper());
		return menuJoinList;
	}*/

	//@Override
	/*public void addToExcel() {
		try{
			FileOutputStream output = new FileOutputStream("C:\\workspace1\\CMS\\WebContent\\vendor\\doc\\edit.xls");
			POIFSFileSystem fs = new POIFSFileSystem();
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			String filename="C:\\Edit\\edit.xls" ;
			HSSFWorkbook hwb=new HSSFWorkbook();
			HSSFSheet sheet =  hwb.createSheet();

			HSSFRow rowhead=   sheet.createRow((short)0);
			rowhead.createCell((short) 0).setCellValue("Serial_Number");
			rowhead.createCell((short) 1).setCellValue("Vendor_Id");
			rowhead.createCell((short) 2).setCellValue("Type");
			rowhead.createCell((short) 3).setCellValue("Category");
			rowhead.createCell((short) 4).setCellValue("Sub_Category");
			rowhead.createCell((short) 5).setCellValue("item");
			rowhead.createCell((short) 6).setCellValue("Price");
			rowhead.createCell((short) 7).setCellValue("Day");
			rowhead.createCell((short) 8).setCellValue("Selected_On");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("Select * from employee");
			int i=1;
			while(rs.next()){
			Session session=sessionfactory.openSession();
			Transaction tran=null;
			tran=session.beginTransaction();
			List<Menu> menus=session.createQuery("From Menu").list();
			tran.commit();
			int i=1;
			for (Menu menu : menus) {
				HSSFRow row=   sheet.createRow((short)i);
				row.createCell((short) 0).setCellValue(Integer.toString(menu.getSerialNo()));
				row.createCell((short) 1).setCellValue(Integer.toString(menu.getVendorId()));
				row.createCell((short) 2).setCellValue(menu.getType());
				row.createCell((short) 3).setCellValue(menu.getCategory());
				row.createCell((short) 4).setCellValue(menu.getSubCategory());
				row.createCell((short) 5).setCellValue(menu.getItem());
				row.createCell((short) 6).setCellValue(Double.toString(menu.getPrice()));
				row.createCell((short) 7).setCellValue(menu.getDay());
				row.createCell((short) 8).setCellValue(menu.getTimeStamp().toString());
				i++;
			}
			//String sql = "select * from menu_list;";
			//double avg = jdbcTemplate.query(sql, Double.class, fid);
				
			
			HSSFRow row=   sheet.createRow((short)i);
			row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("id")));
			row.createCell((short) 1).setCellValue(rs.getString("name"));
			row.createCell((short) 2).setCellValue(rs.getString("address"));
			row.createCell((short) 3).setCellValue(Integer.toString(rs.getInt("contactNo")));
			row.createCell((short) 4).setCellValue(rs.getString("email"));
			i++;
			}
			FileOutputStream fileOut =  new FileOutputStream(filename);
			hwb.write(fileOut);
			fileOut.close();
			System.out.println("Your excel file has been generated!");

			} catch ( Exception ex ) {
			    System.out.println(ex);

			}
	}
*/
}
