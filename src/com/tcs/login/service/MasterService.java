package com.tcs.login.service;

import com.tcs.admin.bean.Vendor;
import com.tcs.login.bean.Admin;
import com.tcs.login.bean.Employee;
import com.tcs.login.bean.Master;


public interface MasterService {

  public Master checkLogin(int id,String password);
  public Admin getAdminDetail(int id);
  public Employee getEmployeeDetail(int id);
  public Vendor getVendorDetail(int id);

}
