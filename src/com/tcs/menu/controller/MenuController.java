package com.tcs.menu.controller;

import java.io.File;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.google.gson.Gson;
import com.tcs.admin.bean.Vendor;
import com.tcs.menu.bean.Menu;
import com.tcs.menu.bean.MenuJoinList;
import com.tcs.menu.bean.MenuRatingDetails;
import com.tcs.menu.service.MenuService;



@Controller
public class MenuController 
{
	@Autowired
	private MenuService menuService;
	
	/*private String saveDirectory = "C:\\workspace1\\CMS\\WebContent\\vendor\\upload\\";
	private String destDirectory = "C:\\workspace1\\CMS\\WebContent\\vendor\\history";
	*/
	private String saveDirectory;
	private String destDirectory;
	
	@RequestMapping(value = "/AddToDB", method = RequestMethod.GET)
	@ResponseBody
	public String addToDB() {
		menuService.deleteMenuList();
		String str=menuService.AddToDB(saveDirectory);
		Gson gson = new Gson();
		String resultJson = gson.toJson(str);
		return resultJson;
		 
		
	}

	@RequestMapping(value = "/continueFileUpload", method = RequestMethod.POST)
	@ResponseBody
	public String continueFileUpload(HttpServletRequest request,
			HttpServletResponse response) {
		
		saveDirectory = request.getServletContext().getRealPath("/vendor/upload/");
		destDirectory =request.getServletContext().getRealPath("/vendor/history");
		
		MultipartHttpServletRequest mRequest;
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		String string = timestamp.toString();
		string = string.replaceAll("\\s", "_");
		string = string.replaceAll(":", ".");
		System.out.println(string);
		try {
			mRequest = (MultipartHttpServletRequest) request;
			mRequest.getParameterMap();
			File saveDir = new File(saveDirectory);
			File destDir = new File(destDirectory);
			File[] files = saveDir.listFiles();

			Iterator<String> itr = mRequest.getFileNames();

			while (itr.hasNext()) {

				MultipartFile mFile = mRequest.getFile(itr.next());
				System.out.println(mFile);
				if (mFile != null) {
					//String fileName = mFile.getOriginalFilename();
			String fileName="menu.xls";
					if (!fileName.endsWith("xls")) {
						System.out.println("Invalid");

						return ("Invalid");
					} else {

						if (files != null) {
							// some JVMs return null for empty dirs
							for (File f : files) {
								System.out.println("Deleting file: "
										+ f.getName());
								f.renameTo(new File(destDir + "\\" + string
										+ "_" + f.getName()));
								f.delete();
							}
						}

						System.out.println("inside else");
						System.out.println("Saving file: " + fileName);
						mFile.transferTo(new File(saveDirectory+"/"
								+ "menu.xls"));
					}
				}

				String fileName1 = mFile.getOriginalFilename();
				System.out.println(fileName1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@RequestMapping(value="/getAllItemsWithRating",method = RequestMethod.GET)
	public @ResponseBody String getAllItemsWithRating()
	{
		List<MenuJoinList> itemList =menuService.getAllMenuWithRating();
		Gson gson = new Gson();
		String resultJson = gson.toJson(itemList);
		return resultJson;
	}
	
	@RequestMapping(value="/rating",params={"proid","eid","rate"}, method = RequestMethod.GET)
    public @ResponseBody String starRate(@RequestParam(value="proid") int proid,@RequestParam (value="eid") int eid,@RequestParam (value="rate") int rating)
    {
		System.out.println("------store rates--------" );
		//MenuRatingDetails menu=menuService.getIdByMenu(name, vid);
		//System.out.println("getting menu detail by id :"+menu);
		List<MenuJoinList> itemList= menuService.saveRate(proid,eid,rating);
		Gson gson = new Gson();
		String resultJson = gson.toJson(itemList);
		return resultJson;
    }
	
	@RequestMapping(value="/getVendorName",method = RequestMethod.GET)
	public @ResponseBody String getVendorName()
	{
		List<String> vendorList= menuService.getVendorName();
		Gson gson = new Gson();
		String resultJson = gson.toJson(vendorList);
		return resultJson;
		
	}
	
	/*@RequestMapping(value="/getDays",method = RequestMethod.GET)
	public @ResponseBody String getDays()
	{
		List<String> menuDays= menuService.getDays();
		Gson gson = new Gson();
		String resultJson = gson.toJson(menuDays);
		return resultJson;
		
	}*/
	
	@RequestMapping(value="/getTypeName",method = RequestMethod.GET)
	public @ResponseBody String getTypeName()
	{
		List<String> menuType= menuService.getTypeName();
		Gson gson = new Gson();
		String resultJson = gson.toJson(menuType);
		return resultJson;
		
	}
	
	/*@RequestMapping(value="/getCategoryName",method = RequestMethod.GET)
	public @ResponseBody String getCategoryName()
	{
		List<String> menuCategory= menuService.getCategoryName();
		Gson gson = new Gson();
		String resultJson = gson.toJson(menuCategory);
		return resultJson;
		
	}*/
	/*@RequestMapping(value="/getAllItems",method = RequestMethod.GET)
	public @ResponseBody String getAllItems()
	{
		List<Menu> itemList =menuService.getAllMenu();
		Gson gson = new Gson();
		String resultJson = gson.toJson(itemList);

		return resultJson;
	}*/
	
	/*@RequestMapping(value="/getAllAverage",method = RequestMethod.GET)
	public @ResponseBody String getAllAverage()
	{
		List<MenuRatingDetails> avgList =menuService.getAllAverage();
		Gson gson = new Gson();
		String resultJson = gson.toJson(avgList);

		return resultJson;
	}*/
	
	/*@RequestMapping(value="/getMenuByVendor",params={"vid"},method = RequestMethod.GET)
	public @ResponseBody String getMenuByVendor(@RequestParam(value="vid") int vid)
	{
		List<MenuJoinList> itemList =menuService.getMenuByVendor(vid);
		Gson gson = new Gson();
		String resultJson = gson.toJson(itemList);
		System.out.println(resultJson);
		return resultJson;
		
	}*/
	
	/*@RequestMapping(value="/getMenuByCategory",params={"category"},method = RequestMethod.GET)
	public @ResponseBody String getMenuByCategory(@RequestParam(value="category") String category)
	{
		List<MenuJoinList> itemList =menuService.getMenuByCategory(category);
		Gson gson = new Gson();
		String resultJson = gson.toJson(itemList);
		return resultJson;
	}
	
	@RequestMapping(value="/getMenuByType",params={"type"},method = RequestMethod.GET)
	public @ResponseBody String getMenuByType(@RequestParam(value="type") String type)
	{
		List<MenuJoinList> itemList =menuService.getMenuByType(type);
		Gson gson = new Gson();
		String resultJson = gson.toJson(itemList);
		return resultJson;
	}
	
	@RequestMapping(value="/getMenuByDays",params={"day"},method = RequestMethod.GET)
	public @ResponseBody String getMenuByDays(@RequestParam(value="day") String day)
	{
		List<MenuJoinList> itemList =menuService.getMenuByDay(day);
		Gson gson = new Gson();
		String resultJson = gson.toJson(itemList);
		return resultJson;
	}*/
	
	
	
}
