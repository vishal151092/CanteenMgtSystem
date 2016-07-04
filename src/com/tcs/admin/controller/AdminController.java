package com.tcs.admin.controller;

/*import java.util.List;*/


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tcs.admin.bean.Master;
import com.tcs.admin.bean.Vendor;
import com.tcs.admin.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	AdminService vendorService;

	

	@RequestMapping(value = "admin/getAllVendor", method = RequestMethod.GET)
	public @ResponseBody
	String getAllVendor() {
		System.out.println("get all controller ");
		List<Vendor> venList = vendorService.getAllVendor();
		System.out.println("Length : " + venList.size());

		Gson gson = new Gson();
		String resultJson = gson.toJson(venList);

		return resultJson;

	}

	
	
	@RequestMapping(value = "admin/addVendor", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	boolean addVendor(@RequestBody Vendor ven) 
	{
		
		
		if(vendorService.addVendor(ven)==1)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	@RequestMapping(value = "admin/addVendorToMaster", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody int addVendorToMaster(@RequestBody Master ven) 
	{
		return vendorService.addVendorToMaster(ven);
	}
	
	
	/*@RequestMapping(value = "/ven/addVendor", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	void addVendor(@RequestBody Vendor ven) {
		System.out.println("Inside add controller");
		ManageVendor.addVendor(ven.getVendorId(),ven.getFname(),ven.getLname(),ven.getAddress(),ven.getEmail(),ven.getContNo(),ven.getDob());
	}*/
	
	
	

	@RequestMapping(value ="admin/updateVendor", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<Vendor> updateVendor(@RequestBody Vendor ven) {
		System.out.println("in update vendor");
     //System.out.println(ven.getEmail());
	 return vendorService.updateVendor(ven);
	}
/*
	@RequestMapping(value = "/ven/deleteVendor", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	List<Vendor> deleteVendor(@RequestBody int vendorId) {
		return vendorService.deleteVendor(vendorId);
	}*/
	
	@RequestMapping(value="/getVendorById",method = RequestMethod.POST,produces = "application/json")
	public @ResponseBody String getVendorById(@RequestBody int vid)
	{
		System.out.println("vendor id : "+vid);
		//System.out.println("vendor id : "+menu.getVendorId());
		List<Vendor> vendorList =vendorService.getVendorById(vid);
		Gson gson = new Gson();
		String resultJson = gson.toJson(vendorList);
		//List<Vendor> vendorList = vendorService.getVendorById(menu.getVendorId());
		System.out.println("From vendor controller");
		for (Vendor vendor : vendorList) {
			System.out.println(vendor);
		}
		//return menuListService.getVendorById(vid);
		return resultJson;
		//return vendorList;
	}
}