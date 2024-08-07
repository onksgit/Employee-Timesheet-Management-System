package com.tsms.pojo;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AwardRequest {

private String empCode;
private Long managerId;
private String awardId;
private String isawardSelected;
private Date dateGiven;
private String remark;
private String justification;
private int rating;


public String getEmpCode() {
	return empCode;
}
public void setEmpCode(String empCode) {
	this.empCode = empCode;
}
public Long getManagerId() {
return managerId;
}
public void setManagerId(Long managerId) {
this.managerId = managerId;
}
public String getAwardId() {
return awardId;
}
public void setAwardId(String awardId) {
this.awardId = awardId;
}
public Date getDateGiven() {
return dateGiven;
}
public void setDateGiven(Date dateGiven) {
this.dateGiven = dateGiven;
}
public String getRemark() {
return remark;
}
public void setRemark(String remark) {
this.remark = remark;
}
public String getJustification() {
return justification;
}
public void setJustification(String justification) {
this.justification = justification;
}
public int getRating() {
return rating;
}
public void setRating(int rating) {
this.rating = rating;
}
public String getIsawardSelected() {
	return isawardSelected;
}
public void setIsawardSelected(String isawardSelected) {
	this.isawardSelected = isawardSelected;
}

public List<Long> getAwardIdList() {
List<Long> awardIdList = new ArrayList<>();
if (awardId != null && !awardId.isEmpty()) {
String[] ids = awardId.split(",");
for (String id : ids) {
awardIdList.add(Long.parseLong(id.trim()));
}
}
return awardIdList;
}



}