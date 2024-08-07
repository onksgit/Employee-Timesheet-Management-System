package com.tsms.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "timesheet_request")
public class TimesheetUnlock {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(name = "user_id")
private Long userId;

@Column(name = "reason")
private String reason;

@DateTimeFormat(pattern = "yyyy-MM-dd")
@Column(name = "request_date")
private Date requestDate;

@Column(name = "status")
private String status;
@Column(name = "first_Name")
private String firstName;
@Column(name = "last_Name")
private String lastName;

@DateTimeFormat(pattern = "yyyy-MM-dd")
@Column(name = "added_date_time")
private Date addedDateTime;

@DateTimeFormat(pattern = "yyyy-MM-dd")
@Column(name = "updated_date_time")
private Date updatedOnDateTime;

@Column(name = "fk_manager_id")
private Long fkManagerId;

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public Long getUserId() {
return userId;
}

public void setUserId(Long userId) {
this.userId = userId;
}

public String getReason() {
return reason;
}

public void setReason(String reason) {
this.reason = reason;
}

public Date getRequestDate() {
return requestDate;
}

public void setRequestDate(Date requestDate) {
this.requestDate = requestDate;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getFirstName() {
return firstName;
}

public void setFirstName(String firstName) {
this.firstName = firstName;
}

public String getLastName() {
return lastName;
}

public void setLastName(String lastName) {
this.lastName = lastName;
}

public Date getAddedDateTime() {
return addedDateTime;
}

public void setAddedDateTime(Date addedDateTime) {
this.addedDateTime = addedDateTime;
}

public Date getUpdatedOnDateTime() {
return updatedOnDateTime;
}

public void setUpdatedOnDateTime(Date updatedOnDateTime) {
this.updatedOnDateTime = updatedOnDateTime;
}

public Long getFkManagerId() {
return fkManagerId;
}

public void setFkManagerId(Long fkManagerId) {
this.fkManagerId = fkManagerId;
}



}