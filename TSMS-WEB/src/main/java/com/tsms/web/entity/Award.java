package com.tsms.web.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_award")
public class Award {

@Id
@SequenceGenerator(name = "award_table_sqc", sequenceName = "award_table_sqc", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "award_table_sqc")
@Column(name = "pk_id")
private Long primaryId;

@Column(name = "award_name")
private String awardName;

@Column(name = "award_description")
private String awardDescription;

public Long getPrimaryId() {
return primaryId;
}

public void setPrimaryId(Long primaryId) {
this.primaryId = primaryId;
}

public String getAwardName() {
return awardName;
}

public void setAwardName(String awardName) {
this.awardName = awardName;
}




}