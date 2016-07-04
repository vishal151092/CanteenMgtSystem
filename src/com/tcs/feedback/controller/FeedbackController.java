	package com.tcs.feedback.controller;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tcs.feedback.bean.Feedback;
import com.tcs.feedback.bean.FeedbackAction;
import com.tcs.feedback.service.FeedbackService;

@Controller
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;
	
	FeedbackController(){
		System.out.println("Inside controller constructor");
	}

	@RequestMapping(value="/fb/addFeedback",method = RequestMethod.POST,produces = "application/json")
	public @ResponseBody int addFeedback(@RequestBody Feedback feedback) 
	{
		System.out.println("Inside addFeed Java controller");
		return feedbackService.addFeedback(feedback);
	}
		
	
	@RequestMapping(value="/fb/getVendor",method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody String getVendor() 
	{
		System.out.println("Inside getVendor controller");
		
		List<ArrayList> vendorList = feedbackService.getVendor();
		System.out.println(vendorList);
	
		Gson gson = new Gson();
		String resultJson = gson.toJson(vendorList);
		
		return resultJson;
	}
	

	@RequestMapping(value="/fb/getNotificationCount",params = {"value"},method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody String getNotificationCount(@RequestParam (value = "value") Integer value) 
	{
		System.out.println("Inside notif controller");
		
		int notifCount = feedbackService.getNotificationCount(value);
		System.out.println(notifCount);
	
		Gson gson = new Gson();
		String resultJson = gson.toJson(notifCount);
		
		return resultJson;
	}
	
	@RequestMapping(value="/feedback/getMyFeedback",params = {"value"}, method = RequestMethod.GET)
	public @ResponseBody String getMyFeedback(@RequestParam (value = "value") Integer value)
	{
		
		System.out.println("Inside coms controller");
		List<Feedback> feedList = feedbackService.getMyFeedback(value);
		System.out.println("Length : "+feedList.size());
		
		Gson gson = new Gson();
		String resultJson = gson.toJson(feedList);
		
		return resultJson;
		
	}
	
	@RequestMapping(value="getAllFeedbackDetails",method=RequestMethod.GET)
	public @ResponseBody String getAllFeedbackDetails(){
		System.out.println("Getting Feedback Details in Controller");
		
		List<Feedback> fd=feedbackService.getAllFeedbackDetails();
		
		Gson gson=new Gson();
        String result=gson.toJson(fd);
        System.out.println(result);
        return result;	
	}
	
	@RequestMapping(value="getFeedbackActionDetails",params={"fId"},method=RequestMethod.GET)
	public @ResponseBody String getById(@RequestParam(value="fId") int fId){
		
		System.out.println("Getting FeedbackAction Details in Controller");
    	
		List<FeedbackAction> fa = feedbackService.getAllFeedbackActionDetails(fId);
    	Gson gson=new Gson();
        String fA=gson.toJson(fa);
        System.out.println(fA);
        return fA;
	}
	
	@RequestMapping(value="getEmpAndVendorName",params={"eId","vId"},method=RequestMethod.GET)
	public @ResponseBody String getAllValidFeedbackDetails(@RequestParam(value="eId") int eId,@RequestParam(value="vId") int vId){
		System.out.println("Getting All Valid Feedback Details in Controller");
		
		List<ArrayList> fd=feedbackService.getAllValidFeedbackDetails(eId,vId);
		
		Gson gson=new Gson();
        String result=gson.toJson(fd);
        System.out.println(result);
        return result;	
	}
	
	@RequestMapping(value="getFeedbackDetail",params={"fId"},method=RequestMethod.GET)
	public @ResponseBody String getFeedbackById(@RequestParam(value="fId") int fId){
		
		System.out.println("Getting FeedbackAction Details in Controller");
    	
		Feedback fa = feedbackService.getFeedbackDetail(fId);
    	Gson gson=new Gson();
        String fA=gson.toJson(fa);
        System.out.println(fA);
        return fA;
	}

	@RequestMapping(value="/feedback/getAllFeedback",method = RequestMethod.GET)
	public @ResponseBody String getAllFeedback()
	{
		System.out.println("Inside feedbackController Controller");
		List <Feedback> fbList=feedbackService.getAllFeedback();
		Gson gson = new Gson();
		String resultJson = gson.toJson(fbList);
		System.out.println(fbList);
		System.out.println("after return in controller");
		return resultJson;
	}
	
	   @RequestMapping(value="/feedback/accept",params={"feedbackId","remark"},method=RequestMethod.GET)
		public @ResponseBody void accept(@RequestParam(value="feedbackId") int feedbackId,@RequestParam(value="remark") String remark){
			System.out.println("inside accept method");
			System.out.println(feedbackId);
			System.out.println(remark);
			feedbackService.accept(feedbackId, remark);
			
			
		}
	
	   @RequestMapping(value="/feedback/reject",params={"feedbackId","remark"},method=RequestMethod.GET)
		public @ResponseBody void reject(@RequestParam(value="feedbackId") int feedbackId,@RequestParam(value="remark") String remark){
			System.out.println("inside accept method");
			System.out.println(feedbackId);
			
			feedbackService.reject(remark,feedbackId);
			
			
		}
	
	   
	   @RequestMapping(value="/feedback/viewMyNotifications",params = {"value"}, method = RequestMethod.GET)
		public @ResponseBody String viewMyNotifications(@RequestParam (value = "value") Integer value)
		{
			
			System.out.println("Inside coms controller");
			List<Feedback> feedList = feedbackService.viewMyNotifications(value);
			System.out.println("Length : "+feedList.size());
			
			Gson gson = new Gson();
			String resultJson = gson.toJson(feedList);
			
			return resultJson;
			
		}
	
	
	   @RequestMapping(value = "/fa/resolvedFeedbackAction", method = RequestMethod.POST, produces = "application/json")
		public @ResponseBody
		int resolcedFeedbackAction(@RequestBody FeedbackAction feedbackAction) {
			System.out.println("Inside update resolvedFeedBack Java controller");
			return feedbackService.resolvedFeedbackAction(feedbackAction);
		}
	   
		@RequestMapping(value = "/fa/forwardedFeedbackAction", method = RequestMethod.POST, produces = "application/json")
		public @ResponseBody
		int forwardedFeedbackAction(@RequestBody FeedbackAction feedbackAction) {
			System.out.println("Inside forwarded FeedBack Java controller");
			return feedbackService.forwardedFeedbackAction(feedbackAction);
		}

	
		@RequestMapping(value="/feedback/setStatus",params={"feedbackId","status"},method=RequestMethod.GET)
			public @ResponseBody void setStatus(@RequestParam(value="feedbackId") int feedbackId,@RequestParam(value="status") String status){
				System.out.println("inside accept method");
				System.out.println(feedbackId);
				
				feedbackService.setStatus(feedbackId,status);
				
				
		}
		
		
		
		@RequestMapping(value="/feedback/generateExcel",params={"status"},method=RequestMethod.GET)
		    public @ResponseBody void doDownload(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="status") String status){
			System.out.println("in excel34534534534534");
		/*	XSSFWorkbook workbook = new XSSFWorkbook(); 
		      //Create file system using specific name
		      FileOutputStream out;
			try {
				out = new FileOutputStream(new File("createworkbook.xlsx"));
			 
		    	workbook.write(out);
				out.close();
		}
			catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      System.out.println(" createworkbook.xlsx written successfully");
			System.out.println("in 4534534534534");
		*/			
		/*    String path = request.getServletContext().getRealPath("/vendor/menu.xls");
		    System.out.println(path);
		    try {
				FileOutputStream fileOut = new FileOutputStream(path);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    
		    File file = new File(path);
		    try {
				String fullPathToYourWebappRoot = file.getCanonicalPath();
			    System.out.println(fullPathToYourWebappRoot);
				
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		*/	
		    
			feedbackService.generateExcel(request,response,status);
			
		}
		
		
		
}
