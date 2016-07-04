package com.tcs.admin.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.tcs.admin.bean.Master;
import com.tcs.admin.bean.Vendor;

@Repository
public class AdminDAOImpl implements AdminDAO {
	

	@Autowired
	 SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<Vendor> getAllVendor() {
		Session session=sessionFactory.openSession();
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
		  
	

	@Override
	public int addVendor(Vendor ven) {
	
		
		//System.out.println("inside addVendor DAO ");
	   
		Vendor v=new Vendor();
		
	//	v.setVendorId(ven.getVendorId());
		v.setFirstName(ven.getFirstName());
		v.setLastName(ven.getLastName());
		v.setAddress(ven.getAddress());
		v.setEmail(ven.getEmail());
		v.setContactNumber(ven.getContactNumber());
	//	v.setDob(ven.getDob());
		v.setVendorName(ven.getVendorName());
		int result=0;
		Session session=sessionFactory.openSession();
		
		try{
			session.beginTransaction();
			System.out.println("inside try begin :"+result);
			// add=(Integer) session.save(v);
			 SQLQuery query= session.createSQLQuery("insert into vendor(first_name,last_name,address,email,contact_number,vendor_name,status) values(?,?,?,?,?,?,?)");
		//		query.setInteger(0,v.getVendorId());
				query.setString(0,v.getFirstName());
				query.setString(1,v.getLastName() );
				query.setString(2,v.getAddress() );
				query.setString(3, v.getEmail() );
				query.setString(4,v.getContactNumber() );
				query.setString(5, v.getVendorName());
				query.setString(6,"Active" );
		//		query.setDate(7,v.getDob());
				
				 result = query.executeUpdate();
				System.out.println(result);
			session.getTransaction().commit();
			System.out.println("inside try :"+result);
		}catch(HibernateException e){
		    if(session.getTransaction()!=null)session.getTransaction().rollback();
		}
		finally{
		   session.close();	
		}
		
		System.out.println(result);
		//session.close();
		//return add;
		return result;
	}
	
	
	@Override
	public int addVendorToMaster(Master ven) {
		
	
		Master v=new Master();
		
		v.setUserId(ven.getUserId());
		v.setPassword(ven.getPassword());
		v.setRole("vendor");
	
		int i=v.getUserId();
		String p=v.getPassword();
		String r=v.getRole();
		
		int add=0;
		Session session=sessionFactory.openSession();
        
		try{	
	   
		  session.beginTransaction();
		  System.out.println(i);
		  System.out.println(p);
		  System.out.println(r);
		  
	      Query query=session.createSQLQuery("insert into master values(?,?,?)");
	      query.setInteger(0,v.getUserId());
	      query.setString(1,v.getPassword());
	      query.setString(2,v.getRole());
	      add=query.executeUpdate();
	      
	      session.getTransaction().commit();
	}catch(HibernateException e){
		if(session.getTransaction()!=null)session.getTransaction().rollback();
	}
	finally{
		session.close();
	}
		
		return add;
	}
	
		
	public List<Vendor> updateVendor(Vendor v){
		
		Session session=sessionFactory.openSession();
		List<Vendor> vs=null;
		try {
			
			session.beginTransaction();
			
			SQLQuery query= session.createSQLQuery("update vendor set first_name=?,last_name=?,address=?,email=?,contact_number=?,vendor_name=?,status=? where vendor_id=?");
			System.out.println(v.getVendorId());
			query.setString(0,v.getFirstName());
			query.setString(1,v.getLastName() );
			query.setString(2,v.getAddress() );
			query.setString(3, v.getEmail() );
			query.setString(4,v.getContactNumber() );
//			query.setDate(5,v.getDob() );
			query.setString(5,v.getVendorName() );
			query.setString(6,v.getStatus() );
			query.setInteger(7,v.getVendorId());
			int result = query.executeUpdate();
				
			session.getTransaction().commit();
			
			session.beginTransaction();
			
			System.out.println("Vendor Update Status="+result);
		        
			
		        SQLQuery q1=session.createSQLQuery("select * from  vendor");
				q1.addEntity(Vendor.class);
				vs=q1.list();
			    System.out.println("commit");
				session.getTransaction().commit();
		}
		catch(HibernateException  e)
		{
			if(session.getTransaction()!=null)session.getTransaction().rollback();
		}
		finally
		{
			session.close();
			
		}
		return vs;
	
		}
		
	
	@Override
	public List<Vendor> getVendorById(int vid) {
		Session session=sessionFactory.openSession();
		Transaction trans=null;
			trans= session.beginTransaction();
			Criteria cr = session.createCriteria(Vendor.class);
			cr.add(Restrictions.eq("vendorId",vid));
			List<Vendor> vendorList=cr.list();
			for (Vendor vendor : vendorList) 
			{
				System.out.println("From get vendor dao by id");
				System.out.println(vendor);
				
			}
			trans.commit();
			return vendorList;
		
	}
	
	}
	