/*package com.tcs.vendor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tcs.login.bean.Vendor;
import com.tcs.vendor.dao.VendorDao;

@Service
public class VendorServiceImpl implements VendorService
{
	@Autowired
	private VendorDao vendorDao;
	
	@Override
	public List<Vendor> getAllVendor()
	{
		return vendorDao.getAllVendor();
	}

	@Override
	public List<Vendor> getVendorById(int vid) 
	{
		return vendorDao.getVendorById(vid);
	}
}
*/