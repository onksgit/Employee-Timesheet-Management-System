package com.tsms.web.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class HrSendMSGDto {
	
	private Long primaryId;
	private String msgString;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date msgSentDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date msgExpireDate;
	private String msgFilePath;
	private String msgSender;
	private String msgReciver;
	private MultipartFile msgFile;

	
	
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
	public MultipartFile getMsgFile() {
		return msgFile;
	}
	public void setMsgFile(MultipartFile msgFile) {
		this.msgFile = msgFile;
	}
	
	
}
