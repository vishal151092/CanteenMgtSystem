/*package com.tcs.vendor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.tcs.login.bean.Vendor;
import com.tcs.vendor.service.VendorService;

@Controller
public class VendorController 
{
	@Autowired
	private VendorService vendorService;
	
	@RequestMapping(value="/getAllVendor",method = RequestMethod.GET)
	public @ResponseBody String getAllVendor()
	{
		List<Vendor> vendorList =vendorService.getAllVendor();
		Gson gson = new Gson();
		String resultJson = gson.toJson(vendorList);

		return resultJson;
	}
	
	
}
*/