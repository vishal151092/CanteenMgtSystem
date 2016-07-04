package com.tcs.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.admin.bean.Vendor;
import com.tcs.login.bean.Admin;
import com.tcs.login.bean.Employee;
import com.tcs.login.bean.Master;
import com.tcs.login.dao.MasterDaoImpl;

@Service
public class MasterServiceImpl implements MasterService {

   @Autowired
	MasterDaoImpl masterdao;

	public Master checkLogin(int id,String password){
		System.out.println("in service");   
	   return masterdao.checkLogin(id, password);
	}
	
	public Admin getAdminDetail(int id){
		return masterdao.getAdminDetail(id);
	}
	public Employee getEmployeeDetail(int id){
		return masterdao.getEmployeeDetail(id);
	}
	public Vendor getVendorDetail(int id){
		return masterdao.getVendorDetail(id);
	}
}
