/*package com.tcs.vendor.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.tcs.login.bean.Vendor;



@Repository
public class VendorDaoImpl implements VendorDao
{
	@Autowired
	private SessionFactory sessionfactory;

	

	@Override
	public List<Vendor> getAllVendor() 
	{
		Session session=sessionfactory.openSession();
		Transaction trans=null;
	
			trans= session.beginTransaction();
			List<Vendor> vendorList=session.createQuery("From Vendor").list();
			for (Vendor vendor : vendorList) 
			{
				//System.out.println("From get vendor dao");
				System.out.println(vendor);
				
			}
			trans.commit();
			return vendorList;
	}
}
*/