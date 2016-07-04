package com.tcs.feedback.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tcs.feedback.bean.CanteenMgmtTeam;
import com.tcs.feedback.bean.Feedback;
import com.tcs.feedback.bean.FeedbackAction;
import com.tcs.feedback.rowmapper.EmpAndVendorNameRowMapper;
import com.tcs.feedback.rowmapper.FeedbackActionRowMapper;
import com.tcs.feedback.rowmapper.FeedbackListRowMapper;
import com.tcs.feedback.rowmapper.VendorListRowMapper;



@Repository
public class FeedbackDaoImpl implements FeedbackDao {
	DataSource dataSource;
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;		
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	FeedbackDaoImpl(){
		System.out.println("Inside FeedbackDAOImpl constructor");
	}
	

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;	
	}

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int addFeedback(Feedback fb) {
		return jdbcTemplate.update("INSERT INTO feedback (emp_id,vendor_id,category,problem,suggestion,submitted_on,status) VALUES (?,?,?,?,?,now(),?)", new Object[]{fb.getEmpId(),fb.getVendorId(),fb.getCategory(),fb.getProblem(),fb.getSuggestion(),"Pending"});
	}

	
	@Override
	public List<ArrayList> getVendor() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("Select vendor_name,vendor_id from vendor", new VendorListRowMapper());
	}

	@Override
	public int getNotificationCount(int id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForInt("Select count(*) from feedback_action where action is null", new Object[]{});
	}
	
	@Override
	public List<Feedback> getMyFeedback(int value) {
		
		//value = value.toLowerCase();
		List<Feedback> temp = null;
		
	temp = jdbcTemplate.query("Select * from feedback as f left outer join feedback_action as fa on f.feedback_id=fa.feedback_id where emp_id = ?", new Object[]{value}, new FeedbackListRowMapper());
	
		return temp;
		
	}
	
	//moderator
	@Override
	public List<Feedback> getAllFeedback() {
		
		Session session=sessionFactory.openSession();
		Transaction trans=null;
	
			trans= session.beginTransaction();
			
			
			String sql = "SELECT * FROM feedback where status='Pending'";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Feedback.class);
			@SuppressWarnings("unchecked")
			List<Feedback> fdbacks = query.list();
			
			session.close();
				
			return fdbacks;	
	}

	@Override
	public void accept(int feedbackId, String remark) {
		

		Session session=sessionFactory.openSession();
		Transaction trans=null;
		try
		{
			//insert into worklist when click on accept button	
			
			SQLQuery q2 = session.createSQLQuery("INSERT into feedback_action (feedback_id) values (?)");
			
			q2.setParameter(0,feedbackId);
			q2.executeUpdate();
			 
		
			Query query = session.createSQLQuery("UPDATE  feedback set status='In Process with Admin',filtered_on=Now() where feedback_id= :id");
			 query.setLong("id",feedbackId );
//			 query.setString("msg", remark);
		        int result = query.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();

		}
	}


	@Override
	public void reject(String remark,int feedbackId) {
		Session session=sessionFactory.openSession();
		Transaction trans=null;
		try
		{
	
			trans= session.beginTransaction();
			SQLQuery q = session.createSQLQuery("update feedback set status='Rejected', usermsg=:msg,filtered_on=Now() where feedback_id= :id");
			
			q.setParameter("msg",remark ); 
			q.setLong("id",feedbackId );
		
			 int result = q.executeUpdate();
		        System.out.println("Feedback Update Status="+result);
				trans.commit();
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		session.close();

	}
}
	
	
	@Override
	public List<Feedback> getAllFeedbackDetails() {
	/*	System.out.println("Inside Dao for all Feedback Details");
		Session session=sessionFactory.openSession();
        session.beginTransaction();	
        
		List<Feedback> feedbacks=session.createQuery("from Feedback").list();
		
		session.getTransaction().commit();
		session.close();
		return feedbacks;
	*/
	
			List<Feedback> temp = null;
		
		temp = jdbcTemplate.query("Select * from feedback as f left outer join feedback_action as fa on f.feedback_id=fa.feedback_id ", new Object[]{}, new FeedbackListRowMapper());
		
			return temp;
	
	
	}

	@Override
	public List<FeedbackAction> getAllFeedbackActionDetails(int fId) {
		System.out.println("Inside Dao for all Feedback Action Details");
		String query = "select * from feedback_action where feedback_id=?";
		List<FeedbackAction> fa=jdbcTemplate.query(query,new Object[]{fId}, new FeedbackActionRowMapper());	
		return fa;
	}

	@Override
	public Feedback getFeedbackDetail(int FeedbackId) {
		
		System.out.println("Inside Dao getfbDetails");
		Session session=sessionFactory.openSession();
        session.beginTransaction();	
        
        Feedback feedback = 
                (Feedback)session.get(Feedback.class,FeedbackId);
		session.getTransaction().commit();
		session.close();		
		return feedback;
	}

	@Override
	public List<ArrayList> getAllValidFeedbackDetails(int eId,int vId) {
	
		return jdbcTemplate.query("select x.emp_name,y.vendor_name from (select concat(first_name,' ',last_name)as emp_name from employee where employee_id=?) as x,(select vendor_name from vendor where vendor_id=?) as y;",new Object[]{eId,vId}, new EmpAndVendorNameRowMapper());
	}

	
	@Override
	public List<Feedback> viewMyNotifications(int value) {
		
		List<Feedback> temp = null;
		
	 temp = jdbcTemplate.query("Select fa.feedback_id,f.emp_id,f.vendor_id,f.category,f.problem,f.suggestion,f.submitted_on,f.status,f.usermsg,f.filtered_on,fa.user_msg from feedback_action as fa Inner Join feedback as f on fa.feedback_id=f.feedback_id where action is null", new Object[]{}, new FeedbackListRowMapper());
	
	 return temp;
		
	}

	@Override
	public int resolvedFeedbackAction(FeedbackAction feedbackAction) {
		
		
		Session session=sessionFactory.openSession();
		Transaction trans=null;
		try
		{
			//insert into fedbck action when click on accept button	
			
			SQLQuery q2 = session.createSQLQuery("Update feedback_action  set action=?,action_remark=?,user_msg=?,action_submitted_on=Now() where feedback_id=?");			
			q2.setParameter(0,feedbackAction.getAction());
			q2.setParameter(1,feedbackAction.getRemark());

			if(feedbackAction.getUserMsg()==null||feedbackAction.getUserMsg()==""){
				q2.setParameter(2,null);
			}
			else{
				
				q2.setParameter(2,feedbackAction.getUserMsg());

			}
			q2.setParameter(3,feedbackAction.getFeedbackId());
			q2.executeUpdate();
			 
			
			if(feedbackAction.getAction().equalsIgnoreCase("Solved")){
			/*Query query = session.createSQLQuery("UPDATE  feedback set status='Solved' where feedback_id= :id");
			 query.setLong("id",feedbackAction.getFeedbackId() );
		        int result = query.executeUpdate();
		    */    
				setStatus(feedbackAction.getFeedbackId() ,"Solved");	
					        
			}
			else if(feedbackAction.getAction().equalsIgnoreCase("Closed")){
				setStatus(feedbackAction.getFeedbackId() ,"Closed");	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();

		}

	return 0;
	}

	@Override
	public int forwardedFeedbackAction(FeedbackAction feedbackAction) {
		return 0;
	}

	@Override
	public void setStatus(int feedbackId, String status) {
		// TODO Auto-generated method stub
		
		Session session=sessionFactory.openSession();
		Transaction trans=null;
		try
		{
	
			trans= session.beginTransaction();
			SQLQuery q = session.createSQLQuery("update feedback set status=:status where feedback_id= :id");
			
			q.setParameter("status",status);
			q.setLong("id",feedbackId );
		
			 int result = q.executeUpdate();
				trans.commit();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		session.close();

	}
}

	public HttpServletResponse generateExcel(HttpServletRequest request,HttpServletResponse response,String status){
		
		/*Session session=sessionFactory.openSession();
		Transaction trans=null;
	
			trans= session.beginTransaction();
			
			
			String sql = "SELECT * FROM feedback where status like "+status;
			System.out.println(sql);
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Feedback.class);
			@SuppressWarnings("unchecked")
			List<Feedback> fdbacks = query.list();
			
			trans.commit();
			session.close();
	*/	
		
		List<Feedback> fdbacks = null;
		
		fdbacks = jdbcTemplate.query("Select * from feedback as f left outer join feedback_action as fa on f.feedback_id=fa.feedback_id where status like ?", new Object[]{status}, new FeedbackListRowMapper());
	
	
		
			try {
			  
				HSSFWorkbook workbook = new HSSFWorkbook();
			    HSSFSheet sheet = workbook.createSheet("lawix10");
			    
			    CellStyle style = workbook.createCellStyle();
		        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.index);
		        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		        style.setAlignment(CellStyle.ALIGN_CENTER);
			    
			    
			    HSSFRow rowhead = sheet.createRow((short) 0);
			    rowhead.createCell((short) 0).setCellValue("Feedback ID");
			    rowhead.createCell((short) 1).setCellValue("Employee ID");
			    rowhead.createCell((short) 2).setCellValue("Vendor ID");
			    rowhead.createCell((short) 3).setCellValue("Category");
			    rowhead.createCell((short) 4).setCellValue("Feedback");
			    rowhead.createCell((short) 5).setCellValue("Suggestion");
			    rowhead.createCell((short) 5).setCellValue("Suggestion");
			    rowhead.createCell((short) 6).setCellValue("Status");
			    
			    int j =1;
			    int i=0;
			    int k=fdbacks.size();
			    System.out.println(k);
			    System.out.println(fdbacks);
			    
			    for (i = 0; i < k; i++){
			        HSSFRow row = sheet.createRow((short) j);
			        row.createCell((short) 0).setCellValue(Integer.toString(fdbacks.get(i).getFeedbackId()));
			        row.createCell((short) 1).setCellValue(Integer.toString(fdbacks.get(i).getEmpId()));
			        row.createCell((short) 2).setCellValue(Integer.toString(fdbacks.get(i).getVendorId()));
			        row.createCell((short) 3).setCellValue(fdbacks.get(i).getCategory());
			        row.createCell((short) 4).setCellValue(fdbacks.get(i).getProblem());
			        row.createCell((short) 5).setCellValue(fdbacks.get(i).getSuggestion());
			        row.createCell((short) 6).setCellValue(fdbacks.get(i).getStatus());
			        j++;
				    System.out.println(i);
			    }
			    System.out.println("Now1111111111111111111111111111");
		//	    String yemi = "C:\\Menu\\menu4.xls";
			
		//	    String yemi = new File("").getAbsolutePath();
			    
		//	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		//	    InputStream input = ec.getResourceAsStream("/FormattedExcel/abc.xls");
			    
		//	    System.out.println(yemi.concat("\\"));
			    
			    
//			    String relativeWebPath = "/images";
//			    String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
//			    File file = new File(absoluteDiskPath, "imagetosave.jpg");

			    
//			    String relativeWebPath = "/images";
//			    String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
//			    File file = new File(absoluteDiskPath, "imagetosave.jpg");
			    
	//		    File file = new File(path);
	//		    String fullPathToYourWebappRoot = file.getCanonicalPath();
				   
			   //offer the user the option of opening or downloading the resulting Excel file
			
			    response.setContentType("application/vnd.ms-excel");
			    response.setHeader("Content-Disposition", "attachment; filename=filename.xls");
			    
			    
			    String path = request.getServletContext().getRealPath("/feedback/FeedbackReport.xls");
			    System.out.println(path);
					FileOutputStream fileOut2 = new FileOutputStream(path);
					// TODO Auto-generated catch block
			    
			    
			    
			       
		//	    FileOutputStream fileOut = new FileOutputStream(yemi);
			    
			    workbook.write(fileOut2);
			    
			    
//			catch (ClassNotFoundException e1) {
//			       e1.printStackTrace();
//			    } 
//			catch (SQLException e1) {
//			        e1.printStackTrace();
//			    } catch (FileNotFoundException e1) {
//			        e1.printStackTrace();
			    } catch (IOException e1) {
			        e1.printStackTrace();
			    }
			catch(Exception e){
			    e.printStackTrace();
			}
		
			return response;
			
	}

	
}
