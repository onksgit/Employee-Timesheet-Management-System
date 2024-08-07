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

import com.tsms.pojo.AwardNomination;
import com.tsms.pojo.TimesheetReportResponsePojo;
import com.tsms.web.dao.SendMessageDao;
import com.tsms.web.entity.HrSendMSG;
import com.tsms.web.entity.UserMaster;

@Repository
public class SendMessageDaoimpl implements SendMessageDao {
	

	@Autowired
	private SessionFactory sessionFactory;

	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public Long save(HrSendMSG hrSendMSG) {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Long hMsg = null;
			try {
				hMsg=(Long) session.save(hrSendMSG);
				transaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
			return hMsg;			
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<HrSendMSG> getLast7daysMessageFromDb(UserMaster reciver, boolean isManager,boolean isEmployee) {
		Session session = sessionFactory.openSession();
		List<HrSendMSG> msgList=new ArrayList<>() ;
		try {
			
			String query="select * FROM t_hr_send_msg WHERE msg_reciver = :reciver OR msg_reciver = 'All' OR msg_reciver= :branch or msg_sender= :sender ";		   
			String daterangequery = " AND msg_sent_date >= DATE_SUB(NOW(), INTERVAL 7 DAY) ";
		    String ismanagerQuery=isManager?" OR msg_reciver = 'AllManager'":isEmployee?"OR msg_reciver = 'AllEmployee'":" ";
		    String orderbyquery=" ORDER BY msg_sent_date DESC";
			
			query=query+ismanagerQuery+daterangequery+orderbyquery;
			
			Query query1 =  session.createSQLQuery(query).addEntity(HrSendMSG.class);;
			query1.setParameter("reciver", reciver.getEmpCode());
			query1.setParameter("branch", reciver.getBranch());
			query1.setParameter("sender", reciver.getEmpCode());

			
			
			msgList = query1.getResultList();


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return msgList;
	}
	
	@Override
	public List<HrSendMSG> getLast7daysMessageFromDb(String empCode) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<HrSendMSG> msgList=new ArrayList<>() ;
		try {
			
			String query="select * FROM t_hr_send_msg WHERE msg_sender = :sender ";		   
			String daterangequery = " AND msg_sent_date >= DATE_SUB(NOW(), INTERVAL 7 DAY) ";
		  
			
			query=query+daterangequery;
			
			Query query1 =  session.createSQLQuery(query) .addEntity(HrSendMSG.class);;
			query1.setParameter("sender", empCode);
			msgList = query1.getResultList();


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return msgList;
	}


	@Override
	public List<AwardNomination> getAwardNominationsList(String month,String year) {
		Session session = sessionFactory.openSession();
		List<AwardNomination> AwardNomination=new ArrayList<>() ;
		SQLQuery query=null;

		try {
			
			String q="SELECT \r\n"
					+ "    CONCAT(u.first_name, ' ', u.last_name) AS employeeName,\r\n"
					+ "    CONCAT(m.first_name, ' ', m.last_name) AS managerName,\r\n"
					+ "    GROUP_CONCAT(DISTINCT aw.award_name ORDER BY aw.award_name SEPARATOR ', ') AS awardName,\r\n"
					+ "    CAST(ag.date_given as CHAR) AS awardDate,\r\n"
					+ "    CAST(ag.rating as CHAR) AS rating,\r\n"
					+ "    ag.justification AS justification,\r\n"
					+ "    ag.remark AS remark\r\n"
					+ "FROM \r\n"
					+ "    t_awards_given ag\r\n"
					+ "JOIN \r\n"
					+ "    t_mst_user u ON u.pk_id = ag.fk_employee_id\r\n"
					+ "JOIN \r\n"
					+ "    t_mst_user m ON m.pk_id = ag.fk_manager_id\r\n"
					+ "JOIN \r\n"
					+ "    t_award aw ON aw.pk_id = ag.fk_award_id\r\n"
					+ "WHERE \r\n"
					+ "    MONTH(ag.date_given) = "+month+" \r\n"
					+ "    AND YEAR(ag.date_given) = "+year+"\r\n"
					+ "GROUP BY \r\n"
					+ "    u.first_name, u.last_name, m.first_name, m.last_name, ag.date_given, ag.rating, ag.justification, ag.remark;\r\n"
					+ "";							   
			
			
			
			query=session.createSQLQuery(q);
			query.setResultTransformer(Transformers.aliasToBean(AwardNomination.class));
			AwardNomination = query.list();



		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return AwardNomination;
	}


	

}
