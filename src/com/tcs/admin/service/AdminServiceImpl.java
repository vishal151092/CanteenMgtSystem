package com.tcs.admin.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.admin.bean.Master;
import com.tcs.admin.bean.Vendor;
import com.tcs.admin.dao.AdminDAO;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO vendorDAO;
	
	
	
	
	
	@Override
	public List<Vendor> getAllVendor() {
		
		return vendorDAO.getAllVendor();
	
	}
	@Override
	public int addVendor(Vendor ven) {
		
		
		

		return vendorDAO.addVendor(ven);
		
		/*if (insertCount == 1)
			return employeeDAO.getAllEmployee();
		else
			return null;*/
	}
	
	
	@Override
	public int addVendorToMaster(Master ven) {
		
		
		

		return vendorDAO.addVendorToMaster(ven);
		
		/*if (insertCount == 1)
			return employeeDAO.getAllEmployee();
		else
			return null;*/
	}


	@Override
	public List<Vendor> updateVendor(Vendor ven) {
		System.out.println("in update vednor service");
		
		
		
		//int updateCount = vendorDAO.updateVendor(ven);
		return vendorDAO.updateVendor(ven);
		
		
	}

	/*@Override
	public List<Vendor> deleteVendor(int vendorId) {
		int deleteCount = 0;
		
		deleteCount = vendorDAO.deleteVendor(vendorId);
		
		if (deleteCount == 1)
			return vendorDAO.getAllVendor();
		else
			return null;
		
	}*/
	
	@Override
	public List<Vendor> getVendorById(int vid) 
	{
		return vendorDAO.getVendorById(vid);
	}
}
