package com.tsms.web.dao;

import java.util.List;

import com.tsms.pojo.AwardNomination;
import com.tsms.web.entity.HrSendMSG;
import com.tsms.web.entity.UserMaster;

public interface SendMessageDao {

	Long save(HrSendMSG hrSendMSG);

	List<HrSendMSG> getLast7daysMessageFromDb(UserMaster reciver, boolean isManager, boolean isEmployee);

	List<HrSendMSG> getLast7daysMessageFromDb(String empCode);

	List<AwardNomination> getAwardNominationsList(String month,String year);

}
