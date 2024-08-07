package com.tsms.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "t_emp_date_unlock_req")
public class EmpDateUnlockRequest {
	
	@Id
	@SequenceGenerator(name = "t_emp_date_unlock_req_table_sqc", sequenceName = "t_emp_date_unlock_req_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_emp_date_unlock_req_table_sqc")
	@Column(name = "pk_id")
	private Long primaryId;
	
	@Column(name = "date_unlock_reason")
	private String dateUnlockreason;
	
	@Column(name = "requested_count_in_month")
	private Long requestedCountInMonth;
	
	@OneToOne
	@JoinColumn(name = "fk_manager")
	private UserMaster fkManager;
	
	@OneToOne
	@JoinColumn(name = "fk_user")
	private UserMaster fkUser;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_to_unlock")
	private Date dateToUnlock;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "requested_date")
	private Date requestedDate;

	public Long getPrimaryId() {
		return primaryId;
	}

	public void setPrimaryId(Long primaryId) {
		this.primaryId = primaryId;
	}

	public String getDateUnlockreason() {
		return dateUnlockreason;
	}

	public void setDateUnlockreason(String dateUnlockreason) {
		this.dateUnlockreason = dateUnlockreason;
	}

	public Long getRequestedCountInMonth() {
		return requestedCountInMonth;
	}

	public void setRequestedCountInMonth(Long requestedCountInMonth) {
		this.requestedCountInMonth = requestedCountInMonth;
	}

	public UserMaster getFkManager() {
		return fkManager;
	}

	public void setFkManager(UserMaster fkManager) {
		this.fkManager = fkManager;
	}

	public UserMaster getFkUser() {
		return fkUser;
	}

	public void setFkUser(UserMaster fkUser) {
		this.fkUser = fkUser;
	}

	public Date getDateToUnlock() {
		return dateToUnlock;
	}

	public void setDateToUnlock(Date dateToUnlock) {
		this.dateToUnlock = dateToUnlock;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}
	
	
}
