package com.tsms.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsms.pojo.TimesheetReportReq;
import com.tsms.pojo.TimesheetReportResponsePojo;
import com.tsms.web.dao.TimesheetReportDao;
import com.tsms.web.entity.HrSendMSG;
import com.tsms.web.entity.UserMaster;

@Repository
public class TimesheetReportDaoimpl implements TimesheetReportDao{

	@Autowired
	private SessionFactory sessionFactory;

	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<TimesheetReportResponsePojo> getTimesheetReportList(TimesheetReportReq timesheetReportReq,int offset,int limit) {
	
		String queryQ="", selectQ="", fromQ="", whereQ = "",grpBy = " group by 1", orderBy = " order by 1", appendGrpBy = "",appendOrderBy = "";
		int count = 1;
		String q="";
		SQLQuery query=null;
		List<TimesheetReportResponsePojo> timesheetReportResponsePojoList = null;
		
		Session session =null;
				try {
					
					boolean branchCondnnApply = true;
					String branchId=timesheetReportReq.getBranch();
					StringBuffer branchSplitId = new StringBuffer("");
					if ( branchId== null || branchId.equalsIgnoreCase("null")) {
						branchCondnnApply = false;
					} else {
						String branchsplit[] = branchId.split(",");
						for (int i = 0; i < branchsplit.length; i++) {
							if (branchsplit[i].equalsIgnoreCase("ALL")) {
								branchCondnnApply = false;
								break;
							} else {
								branchSplitId = branchSplitId.append(",'").append(branchsplit[i]).append("'");
							}
						}
					}
					
					
					boolean projectCondnnApply = true;
					String projectId=timesheetReportReq.getProject();
					StringBuffer projectSplitId = new StringBuffer("");
					if ( projectId== null || projectId.equalsIgnoreCase("null")) {
						projectCondnnApply = false;
					} else {
						String projectsplit[] = projectId.split(",");
						for (int i = 0; i < projectsplit.length; i++) {
							if (projectsplit[i].equalsIgnoreCase("ALL")) {
								projectCondnnApply = false;
								break;
							} else {
								projectSplitId = projectSplitId.append(",'").append(projectsplit[i]).append("'");
							}
						}
					}
					
					boolean managerCondnnApply = true;
					String managerId=timesheetReportReq.getManager();
					StringBuffer managerSplitId = new StringBuffer("");
					if (managerId== null || "null".equalsIgnoreCase(managerId)) {
						managerCondnnApply = false;
					} else {
						String managersplit[] = managerId.split(",");
						for (int i = 0; i < managersplit.length; i++) {
							if (managersplit[i].equalsIgnoreCase("ALL")) {
								managerCondnnApply = false;
								break;
							} else {
								managerSplitId = managerSplitId.append(",").append(managersplit[i]);
							}
						}
					}
					
					boolean employeeCondnnApply = true;
					String employeeId=timesheetReportReq.getEmployee();
					StringBuffer employeeSplitId = new StringBuffer("");
					if (employeeId== null || "null".equalsIgnoreCase(employeeId)) {
						employeeCondnnApply = false;
					} else {
						String employeesplit[] = employeeId.split(",");
						for (int i = 0; i < employeesplit.length; i++) {
							if (employeesplit[i].equalsIgnoreCase("ALL")) {
								employeeCondnnApply = false;
								break;
							} else {
								employeeSplitId = employeeSplitId.append(",").append(employeesplit[i]);
							}
						}
					}
					
					boolean approvalStatusCondnnApply = true;
					String approvalStatusId=timesheetReportReq.getEmployee();
					StringBuffer approvalStatusSplitId = new StringBuffer("");
					if (approvalStatusId== null || "null".equalsIgnoreCase(approvalStatusId)) {
						approvalStatusCondnnApply = false;
					} else {
						String approvalStatussplit[] = approvalStatusId.split(",");
						for (int i = 0; i < approvalStatussplit.length; i++) {
							if (approvalStatussplit[i].equalsIgnoreCase("ALL")) {
								approvalStatusCondnnApply = false;
								break;
							} else {
								approvalStatusSplitId = approvalStatusSplitId.append(",'").append(approvalStatussplit[i]).append("'");
							}
						}
					}
					
					
					
					session=sessionFactory.openSession();
					selectQ="select DISTINCT u.branch AS branchName, ";
					
					if (timesheetReportReq.isProjectSelected()) {
						selectQ = selectQ + "p.project_name AS projectName, ";
						count = count + 1;
						appendGrpBy = appendGrpBy + "," + String.valueOf(count);
						appendOrderBy = appendOrderBy + "," + String.valueOf(count);

					}
					
					if (timesheetReportReq.isManagerSelected()) {
						selectQ = selectQ + " CONCAT(m.first_name, ' ', m.last_name) AS managerName, ";
						count = count + 1;
						appendGrpBy = appendGrpBy + "," + String.valueOf(count);
						appendOrderBy = appendOrderBy + "," + String.valueOf(count);	

					}
					
					
					if (timesheetReportReq.isEmployeeSelected()) {
						selectQ = selectQ + " CONCAT(u.first_name, ' ', u.last_name) AS employeeName, ";
						count = count + 1;
						appendGrpBy = appendGrpBy + "," + String.valueOf(count);
						appendOrderBy = appendOrderBy + "," + String.valueOf(count);

					}
					
//					if (timesheetReportReq.getIsApprovalStatusSelected().equalsIgnoreCase("on")) {
//						selectQ = selectQ + " COALESCE(tm.approval_status, '--') AS approvalStatus, ";
//						count = count + 1;
//						appendGrpBy = appendGrpBy + "," + String.valueOf(count);
//						appendOrderBy = appendOrderBy + "," + String.valueOf(count);
//
//					}
					
					queryQ=queryQ +" CAST(SUM(COUNT(*)) OVER() AS CHAR) AS overAllTimesheetCount,"
							+ "CAST(COUNT(*) OVER() AS CHAR) AS totalRecordCount, "
							+ " CAST(COUNT(*) AS CHAR) AS totalTimesheetCount, "
							+ " CAST(SUM(CASE WHEN tm.approval_status = 'Approved' THEN 1 ELSE 0 END) AS CHAR) AS approvedTimesheetCount, "
							+ " CAST(SUM(CASE WHEN tm.approval_status = 'Pending' THEN 1 ELSE 0 END) AS CHAR) AS pendingTimesheetCount ";
					
					
					fromQ=fromQ + "FROM  t_mst_timesheet tm "
								+ "JOIN t_mst_user u ON u.pk_id = tm.fk_user "
								+ "JOIN t_mst_user m ON m.pk_id = tm.fk_manager "
							    + "JOIN  t_mst_project p ON tm.project_name = p.project_name ";
					
					
					if(branchCondnnApply)
					{
						whereQ = whereQ + " and " + " u.branch  in(" + branchSplitId.toString().replaceFirst(",", "") + ")";

					}
					
					if(projectCondnnApply)
					{
						whereQ = whereQ + " and " + "p.pk_id in(" + projectSplitId.toString().replaceFirst(",", "") + ")";

					}
					
					if(managerCondnnApply)
					{
						whereQ = whereQ + " and " + "tm.fk_manager in(" + managerSplitId.toString().replaceFirst(",", "") + ")";

					}
					
					if(employeeCondnnApply)
					{
						whereQ = whereQ + " and " + "tm.fk_user in(" + employeeSplitId.toString().replaceFirst(",", "") + ")";

					}
					
					if(approvalStatusCondnnApply)
					{
						whereQ = whereQ + " and " + "tm.approval_status in(" + approvalStatusSplitId.toString().replaceFirst(",", "") + ")";

					}
					whereQ ="Where tm.timesheet_date between '"+timesheetReportReq.getFromDate()+"' and '"+timesheetReportReq.getToDate() +"'" +whereQ;
					
					
					grpBy = grpBy + appendGrpBy;
					orderBy=orderBy+appendOrderBy;
					
					q=selectQ+queryQ+fromQ+whereQ+grpBy+orderBy;
					
					if(limit!=0){
						q=q+" LIMIT "+limit+" OFFSET "+offset ;
					}
					query=session.createSQLQuery(q);
				
					
					query.setResultTransformer(Transformers.aliasToBean(TimesheetReportResponsePojo.class));

					timesheetReportResponsePojoList = query.list();

				}catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					session.close();
				}

		
		return timesheetReportResponsePojoList;
	}
	
	@Override
	public List<TimesheetReportResponsePojo> getDetailedTimesheetReportList(TimesheetReportReq timesheetReportReq,int offset,int limit) {
		String queryQ="", selectQ="", fromQ="", whereQ = "",grpBy = " group by 1", orderBy = " order by 1", appendGrpBy = "",appendOrderBy = "";
		int count = 1;
		String q="";
		SQLQuery query=null;
		List<TimesheetReportResponsePojo> timesheetReportResponsePojoList = null;
		
		Session session =null;
				try {
					
					boolean branchCondnnApply = true;
					String branchId=timesheetReportReq.getBranch();
					StringBuffer branchSplitId = new StringBuffer("");
					if ( branchId== null || branchId.equalsIgnoreCase("null")) {
						branchCondnnApply = false;
					} else {
						String branchsplit[] = branchId.split(",");
						for (int i = 0; i < branchsplit.length; i++) {
							if (branchsplit[i].equalsIgnoreCase("ALL")) {
								branchCondnnApply = false;
								break;
							} else {
								branchSplitId = branchSplitId.append(",'").append(branchsplit[i]).append("'");
							}
						}
					}
					
					
					boolean projectCondnnApply = true;
					String projectId=timesheetReportReq.getProject();
					StringBuffer projectSplitId = new StringBuffer("");
					if ( projectId== null || projectId.equalsIgnoreCase("null")) {
						projectCondnnApply = false;
					} else {
						String projectsplit[] = projectId.split(",");
						for (int i = 0; i < projectsplit.length; i++) {
							if (projectsplit[i].equalsIgnoreCase("ALL")) {
								projectCondnnApply = false;
								break;
							} else {
								projectSplitId = projectSplitId.append(",'").append(projectsplit[i]).append("'");
							}
						}
					}
					
					boolean managerCondnnApply = true;
					String managerId=timesheetReportReq.getManager();
					StringBuffer managerSplitId = new StringBuffer("");
					if (managerId== null || "null".equalsIgnoreCase(managerId)) {
						managerCondnnApply = false;
					} else {
						String managersplit[] = managerId.split(",");
						for (int i = 0; i < managersplit.length; i++) {
							if (managersplit[i].equalsIgnoreCase("ALL")) {
								managerCondnnApply = false;
								break;
							} else {
								managerSplitId = managerSplitId.append(",").append(managersplit[i]);
							}
						}
					}
					
					boolean employeeCondnnApply = true;
					String employeeId=timesheetReportReq.getEmployee();
					StringBuffer employeeSplitId = new StringBuffer("");
					if (employeeId== null || "null".equalsIgnoreCase(employeeId)) {
						employeeCondnnApply = false;
					} else {
						String employeesplit[] = employeeId.split(",");
						for (int i = 0; i < employeesplit.length; i++) {
							if (employeesplit[i].equalsIgnoreCase("ALL")) {
								employeeCondnnApply = false;
								break;
							} else {
								employeeSplitId = employeeSplitId.append(",").append(employeesplit[i]);
							}
						}
					}
					
					boolean approvalStatusCondnnApply = true;
					String approvalStatusId=timesheetReportReq.getEmployee();
					StringBuffer approvalStatusSplitId = new StringBuffer("");
					if (approvalStatusId== null || "null".equalsIgnoreCase(approvalStatusId)) {
						approvalStatusCondnnApply = false;
					} else {
						String approvalStatussplit[] = approvalStatusId.split(",");
						for (int i = 0; i < approvalStatussplit.length; i++) {
							if (approvalStatussplit[i].equalsIgnoreCase("ALL")) {
								approvalStatusCondnnApply = false;
								break;
							} else {
								approvalStatusSplitId = approvalStatusSplitId.append(",'").append(approvalStatussplit[i]).append("'");
							}
						}
					}
					
					
					
					session=sessionFactory.openSession();
					selectQ="select DISTINCT u.branch AS branchName, ";
					
					if (timesheetReportReq.isProjectSelected()) {
						selectQ = selectQ + "p.project_name AS projectName, ";
						count = count + 1;
						appendGrpBy = appendGrpBy + "," + String.valueOf(count);
						appendOrderBy = appendOrderBy + "," + String.valueOf(count);

					}
					
					if (timesheetReportReq.isManagerSelected()) {
						selectQ = selectQ + " CONCAT(m.first_name, ' ', m.last_name) AS managerName, ";
						count = count + 1;
						appendGrpBy = appendGrpBy + "," + String.valueOf(count);
						appendOrderBy = appendOrderBy + "," + String.valueOf(count);	

					}
					
					
					if (timesheetReportReq.isEmployeeSelected()) {
						selectQ = selectQ + " CONCAT(u.first_name, ' ', u.last_name) AS employeeName, ";
						count = count + 1;
						appendGrpBy = appendGrpBy + "," + String.valueOf(count);
						appendOrderBy = appendOrderBy + "," + String.valueOf(count);

					}
					
					selectQ = selectQ + " tm.work_done AS timesheetWork, ";
					count = count + 1;
					appendGrpBy = appendGrpBy + "," + String.valueOf(count);
					appendOrderBy = appendOrderBy + "," + String.valueOf(count);
					selectQ = selectQ + " tm.approval_status AS approvalStatus, ";
					count = count + 1;
					appendGrpBy = appendGrpBy + "," + String.valueOf(count);
					appendOrderBy = appendOrderBy + "," + String.valueOf(count);


					
//					if (timesheetReportReq.getIsApprovalStatusSelected().equalsIgnoreCase("on")) {
//						selectQ = selectQ + " COALESCE(tm.approval_status, '--') AS approvalStatus, ";
//						count = count + 1;
//						appendGrpBy = appendGrpBy + "," + String.valueOf(count);
//						appendOrderBy = appendOrderBy + "," + String.valueOf(count);
//
//					}
					
					queryQ=queryQ +" CAST(SUM(COUNT(*)) OVER() AS CHAR) AS overAllTimesheetCount,"
							+ "CAST(COUNT(*) OVER() AS CHAR) AS totalRecordCount ";
					
					
					fromQ=fromQ + "FROM  t_mst_timesheet tm "
								+ "JOIN  t_mst_user u ON u.pk_id = tm.fk_user "
								+ "JOIN  t_mst_user m ON m.pk_id = tm.fk_manager "
							    + "JOIN  t_mst_project p ON tm.project_name = p.project_name";
							    
					// FOR NEW QUERY MULTIPLE PROJECTS by onkar
//					fromQ=fromQ + "FROM  t_mst_timesheet tm "
//							+ "JOIN  t_mst_user u ON u.pk_id = tm.fk_user "
//							+ "JOIN  t_user_project_manager upm ON upm.fk_user=u.pk_id"
//							+ "JOIN  t_user_project_manager upm1 ON upm1.fk_manager=tm.fk_manager"
//							+ "JOIN  t_user_project_manager upm2 ON upm2.fk_project=tm.fk_project";
//					
					
					if(branchCondnnApply)
					{
						whereQ = whereQ + " and " + " u.branch  in(" + branchSplitId.toString().replaceFirst(",", "") + ")";

					}
					
					if(projectCondnnApply)
					{
						whereQ = whereQ + " and " + "p.pk_id in(" + projectSplitId.toString().replaceFirst(",", "") + ")";

					}
					
					if(managerCondnnApply)
					{
						whereQ = whereQ + " and " + "tm.fk_manager in(" + managerSplitId.toString().replaceFirst(",", "") + ")";

					}
					
					if(employeeCondnnApply)
					{
						whereQ = whereQ + " and " + "tm.fk_user in(" + employeeSplitId.toString().replaceFirst(",", "") + ")";

					}
					
					if(approvalStatusCondnnApply)
					{
						whereQ = whereQ + " and " + "tm.approval_status in(" + approvalStatusSplitId.toString().replaceFirst(",", "") + ")";

					}
					whereQ ="Where tm.timesheet_date between '"+timesheetReportReq.getFromDate()+"' and '"+timesheetReportReq.getToDate() +"'" +whereQ;
					
					
					grpBy = grpBy + appendGrpBy;
					orderBy=orderBy+appendOrderBy;
					
					q=selectQ+queryQ+fromQ+whereQ+grpBy+orderBy;
					if(limit!=0){
						q=q+" LIMIT "+limit+" OFFSET "+offset ;
					}
					query=session.createSQLQuery(q);
					query.setResultTransformer(Transformers.aliasToBean(TimesheetReportResponsePojo.class));

					timesheetReportResponsePojoList = query.list();

				}catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					session.close();
				}

		
		return timesheetReportResponsePojoList;
	}
	
	

	@Override
	public List<TimesheetReportResponsePojo> getProjectWiseReport(Long projectID, String startDate, String endDate) {
		Session session = sessionFactory.openSession();
		List<TimesheetReportResponsePojo> TimesheetReportResponsePojoList=new ArrayList<>() ;
        List<TimesheetReportResponsePojo> pojoList = new ArrayList<>();

		
		try {
			
			String query="SELECT p.project_name AS projectName,CONCAT(u.first_name, ' ', u.last_name) AS employeeName, tm.work_done AS timesheetWork, COALESCE(tm.approval_status, '--') AS approvalStatus "
					+ "FROM  t_mst_timesheet tm JOIN  t_mst_user u ON u.pk_id = tm.fk_user "
					+ "LEFT JOIN  t_mst_project p ON tm.project_name = p.pk_id"
					+ " WHERE tm.project_name = :projectID AND tm.timesheet_date BETWEEN :start AND :end";		   
		  
			
//			Query query1 =  session.createSQLQuery(query);
//			query1.setParameter("projectID", projectID);
//			TimesheetReportResponsePojoList = query1.getResultList();
			   org.hibernate.Query<Object[]> query1 = session.createNativeQuery(query);
		        query1.setParameter("projectID", projectID);
				query1.setParameter("start", startDate);
				query1.setParameter("end", endDate);

		        List<Object[]> resultList = query1.getResultList();

		        for (Object[] result : resultList) {
		            String projectName = (String) result[0];
		            String employeeName = (String) result[1];
		            String timesheetWork = (String) result[2];
		            String approvalStatus = (String) result[3];

		            TimesheetReportResponsePojo pojo = new TimesheetReportResponsePojo();
		            pojo.setProjectName(projectName);
		            pojo.setEmployeeName(employeeName);
		            pojo.setTimesheetWork(timesheetWork);
		            pojo.setApprovalStatus(approvalStatus);
		            pojoList.add(pojo);
		        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

			
		        return pojoList;
	}

	@Override
	public List<TimesheetReportResponsePojo> getManagerWiseReport(Long managerID, String startDate, String endDate) {
		Session session = sessionFactory.openSession();
		List<TimesheetReportResponsePojo> TimesheetReportResponsePojoList=new ArrayList<>() ;
        List<TimesheetReportResponsePojo> pojoList = new ArrayList<>();

		
		try {
			
			String query="SELECT CONCAT(m.first_name, ' ', m.last_name) AS managerName,CONCAT(u.first_name, ' ', u.last_name) AS employeeName, tm.work_done AS timesheetWork, COALESCE(tm.approval_status, '--') AS approvalStatus, COALESCE(pro.project_name,'--') AS projectName "
					+ " FROM  t_mst_timesheet tm JOIN  t_mst_user u ON u.pk_id = tm.fk_user "
					+ " JOIN  t_mst_user m ON tm.fk_manager = m.pk_id "
					+ " JOIN t_mst_project pro ON tm.project_name = pro.pk_id "
					+ " WHERE tm.fk_manager = :managerID AND tm.timesheet_date BETWEEN :start AND :end";		   
		  
			
			   org.hibernate.Query<Object[]> query1 = session.createNativeQuery(query);
		        query1.setParameter("managerID", managerID);
				query1.setParameter("start", startDate);
				query1.setParameter("end", endDate);
		        List<Object[]> resultList = query1.getResultList();

		        for (Object[] result : resultList) {
		            String managerName = (String) result[0];
		            String employeeName = (String) result[1];
		            String timesheetWork = (String) result[2];
		            String approvalStatus = (String) result[3];
		            String projectName = (String) result[4];

		            TimesheetReportResponsePojo pojo = new TimesheetReportResponsePojo();
		            pojo.setManagerName(managerName);
		            pojo.setEmployeeName(employeeName);
		            pojo.setTimesheetWork(timesheetWork);
		            pojo.setApprovalStatus(approvalStatus);
		            pojo.setProjectName(projectName);
		            pojoList.add(pojo);
		        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

			
		        return pojoList;
	}

	@Override
	public List<TimesheetReportResponsePojo> getlistBranchWiseReport(String branch,String startDate, String endDate) {
		Session session = sessionFactory.openSession();
		List<TimesheetReportResponsePojo> pojoList = new ArrayList<>();
		try {
			String query = "SELECT u.branch AS branchName,CONCAT(u.first_name, ' ', u.last_name) AS employeeName, tm.work_done AS timesheetWork, COALESCE(tm.approval_status, '--') AS approvalStatus "
					+ "FROM  t_mst_timesheet tm JOIN  t_mst_user u ON u.pk_id = tm.fk_user "
					+ " WHERE u.branch = :branch AND tm.timesheet_date BETWEEN :start AND :end";

			org.hibernate.Query<Object[]> query1 = session.createNativeQuery(query);
			query1.setParameter("branch", branch);
			query1.setParameter("start", startDate);
			query1.setParameter("end", endDate);
			List<Object[]> resultList = query1.getResultList();
			for (Object[] result : resultList) {
				String branchName = (String) result[0];
				String employeeName = (String) result[1];
				String timesheetWork = (String) result[2];
				String approvalStatus = (String) result[3];

				TimesheetReportResponsePojo pojo = new TimesheetReportResponsePojo();
				pojo.setBranchName(branchName);
				pojo.setEmployeeName(employeeName);
				pojo.setTimesheetWork(timesheetWork);
				pojo.setApprovalStatus(approvalStatus);
				pojoList.add(pojo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return pojoList;
	}

	public List<UserMaster> getTimesheetNotFilledReport(String fromDate, String toDate) {
	    Session session = sessionFactory.openSession();
	    List<UserMaster> usermasterList = null;
	    SQLQuery query = null;

	    try {
	        String q ="SELECT DISTINCT u.* "
	        		+ "FROM t_mst_user u "
	        		+ "JOIN t_mst_timesheet t ON u.pk_id != t.fk_user "
	        		+ "WHERE t.timesheet_date = :fromDate ";

	        query = session.createSQLQuery(q);
	        query.setParameter("fromDate", fromDate);
//	        query.setParameter("toDate", toDate);
	        query.addEntity(UserMaster.class);  // Specify entity class for correct mapping

	        usermasterList = query.list();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return usermasterList;
	}

	@Override
	public List<TimesheetReportResponsePojo> getEmployeeWiseReport(Long empID, String startDate, String endDate) {
		Session session = sessionFactory.openSession();
		List<TimesheetReportResponsePojo> TimesheetReportResponsePojoList=new ArrayList<>() ;
        List<TimesheetReportResponsePojo> pojoList = new ArrayList<>();

		
		try {
			
			String query="SELECT CONCAT(m.first_name, ' ', m.last_name) AS managerName,CONCAT(u.first_name, ' ', u.last_name) AS employeeName, tm.work_done AS timesheetWork, COALESCE(tm.approval_status, '--') AS approvalStatus, COALESCE(pro.project_name,'--') AS projectName "
					+ " FROM  t_mst_timesheet tm JOIN  t_mst_user u ON u.pk_id = tm.fk_user "
					+ " JOIN  t_mst_user m ON tm.fk_manager = m.pk_id "
					+ " JOIN t_mst_project pro ON tm.project_name = pro.pk_id "
					+ " WHERE u.pk_id = :empID AND tm.timesheet_date BETWEEN :start AND :end";		   
		  
			
			   org.hibernate.Query<Object[]> query1 = session.createNativeQuery(query);
		        query1.setParameter("empID", empID);
				query1.setParameter("start", startDate);
				query1.setParameter("end", endDate);
		        List<Object[]> resultList = query1.getResultList();

		        for (Object[] result : resultList) {
		            String managerName = (String) result[0];
		            String employeeName = (String) result[1];
		            String timesheetWork = (String) result[2];
		            String approvalStatus = (String) result[3];
		            String projectName = (String) result[4];

		            TimesheetReportResponsePojo pojo = new TimesheetReportResponsePojo();
		            pojo.setManagerName(managerName);
		            pojo.setEmployeeName(employeeName);
		            pojo.setTimesheetWork(timesheetWork);
		            pojo.setApprovalStatus(approvalStatus);
		            pojo.setProjectName(projectName);
		            pojoList.add(pojo);
		        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		        return pojoList;
	}


}
