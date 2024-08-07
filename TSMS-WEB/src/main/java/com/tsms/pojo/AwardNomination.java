package com.tsms.pojo;

public class AwardNomination {
	
	private String awardName,employeeName,awardDate,managerName,rating,remark,justification;

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAwardDate() {
		return awardDate;
	}

	public void setAwardDate(String awardDate) {
		this.awardDate = awardDate;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
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

	@Override
	public String toString() {
		return "AwardNomination [awardName=" + awardName + ", employeeName=" + employeeName + ", awardDate=" + awardDate
				+ ", managerName=" + managerName + ", rating=" + rating + ", remark=" + remark + ", justification="
				+ justification + "]";
	}
	
	
	

}
