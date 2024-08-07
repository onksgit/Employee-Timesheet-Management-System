package com.tsms.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "t_hr_send_msg")
public class HrSendMSG {
	
	@Id
	@SequenceGenerator(name = "hr_send_msg_table_sqc", sequenceName = "hr_send_msg_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hr_send_msg_table_sqc")
	@Column(name = "pk_id")
	private Long primaryId;
	
	@Column(name = "msg_string")
	private String msgString;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "msg_sent_date")
	private Date msgSentDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "msg_expire_date")
	private Date msgExpireDate;
	
	@Column(name = "msg_sender")
	private String msgSender;
	
	@Column(name = "msg_file_path")
	private String msgFilePath;
	
	@Column(name = "msg_reciver")
	private String msgReciver;

	public Long getPrimaryId() {
		return primaryId;
	}

	public void setPrimaryId(Long primaryId) {
		this.primaryId = primaryId;
	}

	public String getMsgString() {
		return msgString;
	}

	public void setMsgString(String msgString) {
		this.msgString = msgString;
	}

	public Date getMsgSentDate() {
		return msgSentDate;
	}

	public void setMsgSentDate(Date msgSentDate) {
		this.msgSentDate = msgSentDate;
	}

	public Date getMsgExpireDate() {
		return msgExpireDate;
	}

	public void setMsgExpireDate(Date msgExpireDate) {
		this.msgExpireDate = msgExpireDate;
	}

	public String getMsgSender() {
		return msgSender;
	}

	public void setMsgSender(String msgSender) {
		this.msgSender = msgSender;
	}

	public String getMsgReciver() {
		return msgReciver;
	}

	public void setMsgReciver(String msgReciver) {
		this.msgReciver = msgReciver;
	}

	public String getMsgFilePath() {
		return msgFilePath;
	}

	public void setMsgFilePath(String msgFilePath) {
		this.msgFilePath = msgFilePath;
	}
	
}
