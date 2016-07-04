package com.tcs.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tcs.admin.bean.Vendor;
import com.tcs.login.bean.Admin;
import com.tcs.login.bean.Employee;
import com.tcs.login.bean.Master;
import com.tcs.login.service.MasterServiceImpl;

@Controller
public class MasterController {

   @Autowired
	MasterServiceImpl masterservice;
   
   @RequestMapping(value="login/checkLogin",method=RequestMethod.POST,produces="application/json")
   public @ResponseBody String checkLogin(@RequestBody Master emp){
	   System.out.println("in controller");

	   
	   Master e=masterservice.checkLogin(emp.getId(),emp.getPassword());
	  
	   
	   
	  Gson gson=new Gson();
	  
	  String empp=gson.toJson(e);
	  
	  return empp;
 }
   @RequestMapping(value="login/getAdminDetail",params={"adminId"},method=RequestMethod.GET)
   public @ResponseBody String getAdminDetail(@RequestParam(value="adminId") int adminId){
	   System.out.println("get Details dao...");
	  
	   Admin ad=masterservice.getAdminDetail(adminId);
	   
	   Gson gson=new Gson();
	   String adminDetails=gson.toJson(ad);
	   return adminDetails;
   }
   @RequestMapping(value="login/getEmployeeDetail",params={"employeeId"},method=RequestMethod.GET)
   public @ResponseBody String getEmployeeDetail(@RequestParam(value="employeeId") int employeeId){
	   System.out.println("get Details dao...");
	  
	   Employee ep=masterservice.getEmployeeDetail(employeeId);
	   
	   Gson gson=new Gson();
	   String employeeDetails=gson.toJson(ep);
	   return employeeDetails;
   }
   @RequestMapping(value="login/getVendorDetail",params={"vendorId"},method=RequestMethod.GET)
   public @ResponseBody String getVendorDetail(@RequestParam(value="vendorId") int vendorId){
	   System.out.println("get Details dao...");
	  
	   Vendor ve=masterservice.getVendorDetail(vendorId);
	   
	   Gson gson=new Gson();
	   String vendorDetails=gson.toJson(ve);
	   return vendorDetails;
   }
}
