package com.tsms.web.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_awards_given")
public class AwardsGiven {

@Id
@SequenceGenerator(name = "awards_given_table_sqc", sequenceName = "awards_given_table_sqc", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "awards_given_table_sqc")
@Column(name = "pk_id")
private Long primaryId;

@ManyToOne
@JoinColumn(name = "fk_employee_id", referencedColumnName = "pk_id")
private UserMaster employee;

@ManyToOne
@JoinColumn(name = "fk_manager_id", referencedColumnName = "pk_id")
private UserMaster manager;

@ManyToOne
@JoinColumn(name = "fk_award_id", referencedColumnName = "pk_id")
private Award award;

@Temporal(TemporalType.DATE)
@Column(name = "date_given")
private Date dateGiven;

@Column(name = "remark")
private String remark;

@Column(name = "justification")
private String justification;

@Column(name = "rating")
private int rating;

public Long getPrimaryId() {
return primaryId;
}

public void setPrimaryId(Long primaryId) {
this.primaryId = primaryId;
}

public UserMaster getEmployee() {
return employee;
}

public void setEmployee(UserMaster employee) {
this.employee = employee;
}

public UserMaster getManager() {
return manager;
}

public void setManager(UserMaster manager) {
this.manager = manager;
}

public Award getAward() {
return award;
}

public void setAward(Award award) {
this.award = award;
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


}