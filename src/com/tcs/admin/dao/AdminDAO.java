package com.tcs.admin.dao;

import java.util.List;

import com.tcs.admin.bean.Master;
import com.tcs.admin.bean.Vendor;

public interface AdminDAO {
	
	public List<Vendor> getAllVendor();
	public int addVendor(Vendor ven);
	public int addVendorToMaster(Master ven);
	public List<Vendor> updateVendor(Vendor ven);
	/*public int deleteVendor(int vendorId);*/
	public List<Vendor> getVendorById(int vid);

}
