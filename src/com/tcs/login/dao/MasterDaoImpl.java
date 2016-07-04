package com.tcs.login.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tcs.admin.bean.Vendor;
import com.tcs.login.bean.Admin;
import com.tcs.login.bean.Employee;
import com.tcs.login.bean.Master;


@Repository
public class MasterDaoImpl implements MasterDao {

 @Autowired
  SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}


	
	public Master checkLogin(int aid,String apassword){
      
		System.out.println("in dao");
		Master e1=new Master();
		List<Master> employees=null;
		Session session=sessionFactory.openSession();
		
		try{
		
		session.beginTransaction();
        
		Criteria cr=session.createCriteria(Master.class);
        cr.add(Restrictions.eq("id",aid));
        cr.add(Restrictions.eq("password",apassword));
  	  
		 employees=cr.list();
	     session.getTransaction().commit();
		
		}catch(HibernateException e){
			if(session.getTransaction()!=null)session.getTransaction().rollback();
		}
		finally{
			
	        session.close();
	        
		}
		
        return employees.get(0);
	}
	
	public Admin getAdminDetail(Integer id){
	    System.out.println("inside admin details");
		
		Admin ad=null;
		Session session=sessionFactory.openSession();
	try{
		
			session.beginTransaction();
			ad =(Admin)session.get(Admin.class,id);
	//    System.out.println("inside admin details");
	        session.getTransaction().commit();
		}catch(HibernateException e){
			if(session.getTransaction()!=null)session.getTransaction().rollback();
		}
		finally{
			session.close();
		}
		return ad;
	}
	public Employee getEmployeeDetail(Integer id){
	    System.out.println("inside employee details");
		
		Employee ep=null;
		Session session=sessionFactory.openSession();
	try{
		
			session.beginTransaction();
			ep =(Employee)session.get(Employee.class,id);
	//    System.out.println("inside admin details");
	       session.getTransaction().commit();
		}catch(HibernateException e){
			if(session.getTransaction()!=null)session.getTransaction().rollback();
		}
		finally{
			session.close();
		}
		return ep;
	}
	public Vendor getVendorDetail(Integer id){
	    System.out.println("inside employee details");
		
		Vendor ve=null;
		Session session=sessionFactory.openSession();
	try{
		
			session.beginTransaction();
			ve =(Vendor)session.get(Vendor.class,id);
	//    System.out.println("inside admin details");
	        session.getTransaction().commit();
		}catch(HibernateException e){
			if(session.getTransaction()!=null)session.getTransaction().rollback();
		}
		finally{
			session.close();
		}
		return ve;
	}
}
