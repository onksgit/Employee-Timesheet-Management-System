package com.tsms.web.service;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsms.web.dao.AwardGivenDao;
import com.tsms.web.dao.AwardsDao;
import com.tsms.web.dao.DesignationDao;
import com.tsms.web.dao.EmployeeTimesheetDao;
import com.tsms.web.dao.TimesheetUnlockDao;
import com.tsms.web.dao.UserDao;
import com.tsms.web.dao.UserRoleMasterDao;
import com.tsms.web.dto.TimesheetDataPojo;
import com.tsms.web.dto.TimesheetMasterDto;
import com.tsms.web.dto.TimesheetStatusDto;
import com.tsms.web.dto.UserMasterDto;
import com.tsms.web.entity.AwardMaster;
import com.tsms.web.entity.AwardsGiven;
import com.tsms.web.entity.DesignationMaster;
import com.tsms.web.entity.HrSendMSG;
import com.tsms.web.entity.TimesheetMaster;
import com.tsms.web.entity.TimesheetUnlock;
import com.tsms.web.entity.UserMaster;
import com.tsms.web.entity.UserRoleMaster;
import com.tsms.web.session.SessionDetails;
import com.tsms.web.utils.DateUtils;

@Service
public class EmployeeTimesheetService {

	@Autowired
	private EmployeeTimesheetDao employeeTimesheetDao;
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	DesignationDao designationDao;
	@Autowired
	UserRoleMasterDao userRoleMasterDao;
	
	@Autowired
	AwardsDao awardsDao;
	@Autowired
	AwardGivenDao awardGivenDao;
	
	
	
	@Autowired
	TimesheetUnlockDao timesheetUnlockDao;
	
	List<TimesheetMasterDto> timesheetMasterDtoList;
	List<TimesheetMaster> timesheetMasterList;

	public TimesheetMaster saveTimesheet(TimesheetMaster timesheetMaster) {
		return employeeTimesheetDao.save(timesheetMaster);
	}

	public List<TimesheetMasterDto> getLast7DaysWork(UserMaster userMaster) {
		long fkUser = userMaster.getPrimaryId();
		timesheetMasterList = employeeTimesheetDao.getLast7DaysWork(fkUser);
		timesheetMasterDtoList = timesheetMasterList.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;

	}

	public List<TimesheetDataPojo> getEmployees(UserMaster userMaster,String month ,String year) {
		Long managerId = userMaster.getPrimaryId();
		List<Object[]> results = employeeTimesheetDao.getApprovedTimesheetForAward(userMaster,month,year);
		 List<TimesheetDataPojo> pojoList = new ArrayList<TimesheetDataPojo>();

	        for (Object[] result : results) {
	        	TimesheetDataPojo pojo = new TimesheetDataPojo();
	            String empCode = (String) result[0];
	            String firstName = (String) result[1];
	            String lastName = (String) result[2];
	            pojo.setEmpCode(empCode);
	            pojo.setFirstName(firstName);
	            pojo.setLastName(lastName);
	            
	            UserMaster userMasterr= (UserMaster) userDao.findByUserName(empCode);
	            List<AwardsGiven> awardsGiven= awardGivenDao.findByEmployeeAndDateGiven(userMasterr,DateUtils.getCurrentDate());
	            
	            if(awardsGiven!=null || !awardsGiven.isEmpty())
	            pojo.setIsawardGiven("Yes");	
	            
	            pojoList.add(pojo);
	        }
			return pojoList;
	}

	public void updateRemark(TimesheetStatusDto timesheetStatusDto, SessionDetails sessionDetails) {
		String str[] = null;
		if (timesheetStatusDto.getWeeklyTimesheetId() != null) {
			str = timesheetStatusDto.getWeeklyTimesheetId().split(",");

			for (int i = 0; i < str.length; i++) {
				if (str[i] != null && !str[i].isEmpty()) {
					Long longval = Long.valueOf(str[i]);
					employeeTimesheetDao.updateRemark(timesheetStatusDto.getRemark(), longval);
				}
			}
		}
//		if (str.endsWith(",")) {
//		    str = str.substring(0, str.length() - 1);
//		}
//		String strarr[] = str.split(",");
//		for(int i=0;i<strarr.length;i++) {
//			Long longval=Long.valueOf(strarr[i]);
//			employeeTimesheetDao.updateRemark(timesheetStatusDto.getRemark(), longval);
//		}
//		  LocalDate startDate = LocalDate.now();
//	        LocalDate endDate = LocalDate.now().minusDays(7);
//	        Date startDateAsDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//	        Date endDateAsDate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//		String empCode = timesheetStatusDto.getEmpCode();
//		employeeTimesheetDao.updateRemark(timesheetStatusDto.getRemark(), empCode,startDateAsDate ,endDateAsDate);
	}

	
	
	
	public void updateRequestdate(TimesheetUnlock timesheetUnlock, SessionDetails sessionDetails) {
//        Long managerId=sessionDetails.getLoggedInUser().getFkManager().getPrimaryId();

            try {
				timesheetUnlock.setAddedDateTime(DateUtils.getCurrentDateTime());
//	            timesheetUnlock.setFkManagerId(managerId);
	            timesheetUnlock.setStatus("Pending");
		        timesheetUnlockDao.save(timesheetUnlock);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    
	}

	public List<TimesheetMasterDto> getDateRequestTimesheet(SessionDetails sessionDetails) {
		UserMaster user = sessionDetails.getLoggedInUser();
		List<TimesheetMaster> list= employeeTimesheetDao.getDateRequestTimesheet(user.getPrimaryId());
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}

	public boolean approvedDateLock(Long id) {
		boolean flag = false;
		Optional<TimesheetUnlock> timesheetUnlock = timesheetUnlockDao.findById(id);
		if (timesheetUnlock.isPresent()) {
			TimesheetUnlock timesheetUnlock1 = timesheetUnlock.get();
			timesheetUnlock1.setStatus("Approved");
			TimesheetUnlock unlocked=timesheetUnlockDao.save(timesheetUnlock1);
			if(unlocked !=null)
				flag = true;
		}
		return flag;
	}

	public List<TimesheetMasterDto> getApprovedDates(String empCode) {
		List<TimesheetMaster> list= employeeTimesheetDao.getApprovedDates(empCode);
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}

	public List<UserMasterDto> getAllManagerList() {
		UserRoleMaster userRoleMasterInstance = userRoleMasterDao.getById(3L);
//		List<UserMaster> userMaster=userDao.findByfkUserRoleId(userRoleMasterInstance);
		List<UserMaster> userMaster= userDao.findByFkUserRoleIdOrderByFirstNameAsc(userRoleMasterInstance);
		List<UserMasterDto> userMasterDto = userMaster.stream()
				.map((masters) -> this.modelmapper.map(masters, UserMasterDto.class))
				.collect(Collectors.toList());
		return userMasterDto;
	}

	public List<TimesheetMasterDto> getEmpRequestedDate(String empCode, Date requestedDate) {
		List<TimesheetMaster> list= employeeTimesheetDao.getEmpRequestedDate(empCode,requestedDate);
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}

	public List<TimesheetMasterDto> getAllTimesheetDetailes(UserMaster loggedInUser) {
		List<TimesheetMaster> list= employeeTimesheetDao.getEmployeeTimesheetdata(loggedInUser.getEmpCode());
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}

	public List<TimesheetMasterDto> getEmployeeWeeklyWork(String empcode, UserMaster userMaster) {
		Long managerId = userMaster.getPrimaryId();
		timesheetMasterList = employeeTimesheetDao.getByEmpCode(empcode,managerId);
		timesheetMasterDtoList = timesheetMasterList.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}

	public List<AwardMaster> getAllAwards() {
		return awardsDao.findAll();
	}

	public void updateEmployeeWithAwards(TimesheetStatusDto timesheetStatusDto, UserMaster userMaster) {
		Long managerId = userMaster.getPrimaryId();
		timesheetMasterList = employeeTimesheetDao.getByEmpCode(timesheetStatusDto.getEmpCode(),managerId);
		for(int i=0;i<timesheetMasterList.size();i++) {
			TimesheetMaster master = timesheetMasterList.get(i);
			master.setAwards(timesheetStatusDto.getAwardname());
			master.setManagerRating(timesheetStatusDto.getRating().toString());
			employeeTimesheetDao.save(master);
		}
	}
	
	public List<TimesheetMasterDto> getDateRequestTimesheetEmployee(SessionDetails sessionDetails) {
		UserMaster user = sessionDetails.getLoggedInUser();
		List<TimesheetMaster> list= employeeTimesheetDao.getDateRequestTimesheetEmployee(user.getEmpCode());
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}

	public TimesheetMaster findByEmpCode(String empCode, String string) {
		return employeeTimesheetDao.findByEmpCodeAndDate(empCode, string);
	}

	public List<TimesheetDataPojo> getPendingtimesheet(UserMaster userMaster) {
		Long managerId = userMaster.getPrimaryId();
		List<Object[]> results = employeeTimesheetDao.getPendingStatusUsersIn7Days(managerId);
        List<TimesheetDataPojo> pojoList = new ArrayList<TimesheetDataPojo>();

        for (Object[] result : results) {
        	TimesheetDataPojo pojo = new TimesheetDataPojo();
            String empCode = (String) result[0];
            String firstName = (String) result[1];
            String lastName = (String) result[2];
            String branch = (String) result[3];
            String department = (String) result[4];
            BigInteger designation=  (BigInteger) result[5];
            Long des=designation.longValue();
            DesignationMaster designationMaster=designationDao.getById(des);
            String grade = (String) result[6];
            boolean isActive = (boolean) result[7];
            String titile = (String) result[8];
            String status = (String) result[9];
            pojo.setEmpCode(empCode);
            pojo.setFirstName(firstName);
            pojo.setLastName(lastName);
            pojo.setDesignation(designationMaster.getDesignationName());
            pojo.setBranch(branch);
            pojo.setDepartment(department);
            pojo.setGrade(grade);
            if(isActive) {            	
            	pojo.setIsActive("True");
            }
            pojo.setTitle(titile);
            pojo.setApprovalStatus(status);
            pojoList.add(pojo);
        }
		return pojoList;
	}

	public TimesheetMaster getTimeshDetailesByDate(Date timesheetDate, UserMaster userMaster, String projectname) throws ParseException {	
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = date.format(timesheetDate);
		return employeeTimesheetDao.getTimeshDetailesByDate(formattedDate,userMaster.getEmpCode(),projectname);
	}

	public TimesheetMaster getTimeshDetailesByRequestedDate(Date timesheetDate, UserMaster userMaster) {
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = date.format(timesheetDate);
		return employeeTimesheetDao.getTimeshDetailesByRequestedDate(formattedDate,userMaster.getEmpCode());

	}

	public List<TimesheetMasterDto> getDailyCountTimesheet() {
		List<TimesheetMaster> list= employeeTimesheetDao.getDailyCountTimesheet();
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}

	public List<TimesheetMasterDto> getWeeklyCountTimesheet() {
		List<TimesheetMaster> list= employeeTimesheetDao.getWeeklyCountTimesheet();
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}
	
	public List<TimesheetMasterDto> getMonthlyCountTimesheet() {
		List<TimesheetMaster> list= employeeTimesheetDao.getMonthlyCountTimesheet();
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}

	public List<TimesheetMasterDto> getMonthlyTimesheetWork(String empCode, Long fkManager) {
		List<TimesheetMaster> list= employeeTimesheetDao.getMonthlyTimesheetWork(empCode,fkManager);
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}

	public void updateEmployeeWithAwards(String empcode, String rating, String awards, UserMaster userMaster) {
		Long managerId = userMaster.getPrimaryId();
		timesheetMasterList = employeeTimesheetDao.getByEmpCodeList(empcode, managerId);
		for(int i=0;i<timesheetMasterList.size();i++) {
			TimesheetMaster master = timesheetMasterList.get(i);
			master.setAwards(awards);
			master.setManagerRating(rating);
			employeeTimesheetDao.save(master);
		}
		
	}

	public List<TimesheetMasterDto> getDailyApprCountTimesheet() {
		List<TimesheetMaster> list= employeeTimesheetDao.getDailyApprCountTimesheet();
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}
	
	public List<TimesheetMasterDto> getWeeklyApprCountTimesheet() {
		List<TimesheetMaster> list= employeeTimesheetDao.getWeeklyApprCountTimesheet();
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}
	
	public List<TimesheetMasterDto> getMonthlyApprCountTimesheet() {
		List<TimesheetMaster> list= employeeTimesheetDao.getMonthlyApprCountTimesheet();
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}

	//overloading of methods
	public List<TimesheetMasterDto> getDailyCountTimesheet(Long loggedInUser) {
		List<TimesheetMaster> list= employeeTimesheetDao.getDailyCountTimesheet(loggedInUser);
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}

	public List<TimesheetMasterDto> getWeeklyCountTimesheet(Long loggedInUser) {
		List<TimesheetMaster> list= employeeTimesheetDao.getWeeklyCountTimesheet(loggedInUser);
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}
	
	public List<TimesheetMasterDto> getMonthlyCountTimesheet(Long loggedInUser) {
		List<TimesheetMaster> list= employeeTimesheetDao.getMonthlyCountTimesheet(loggedInUser);
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}
	
	
	public List<TimesheetMasterDto> getDailyApprCountTimesheet(Long loggedInUser) {
		List<TimesheetMaster> list= employeeTimesheetDao.getDailyApprCountTimesheet(loggedInUser);
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}

	public List<TimesheetMasterDto> getWeeklyApprCountTimesheet(Long loggedInUser) {
		List<TimesheetMaster> list= employeeTimesheetDao.getWeeklyApprCountTimesheet(loggedInUser);
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}

	public List<TimesheetMasterDto> getMonthlyApprCountTimesheet(Long loggedInUser) {
		List<TimesheetMaster> list= employeeTimesheetDao.getMonthlyApprCountTimesheet(loggedInUser);
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}
	
	
	//Man
	
	public List<TimesheetMasterDto> getDailyCountManTimesheet(Long loggedInUser) {
		List<TimesheetMaster> list= employeeTimesheetDao.getDailyCountManTimesheet(loggedInUser);
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}

	public List<TimesheetMasterDto> getWeeklyCountManTimesheet(Long loggedInUser) {
		List<TimesheetMaster> list= employeeTimesheetDao.getWeeklyCountManTimesheet(loggedInUser);
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}
	
	public List<TimesheetMasterDto> getMonthlyCountManTimesheet(Long loggedInUser) {
		List<TimesheetMaster> list= employeeTimesheetDao.getMonthlyCountManTimesheet(loggedInUser);
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}
	
	
	public List<TimesheetMasterDto> getDailyApprCountManTimesheet(Long loggedInUser) {
		List<TimesheetMaster> list= employeeTimesheetDao.getDailyApprCountManTimesheet(loggedInUser);
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}

	public List<TimesheetMasterDto> getWeeklyApprCountManTimesheet(Long loggedInUser) {
		List<TimesheetMaster> list= employeeTimesheetDao.getWeeklyApprCountManTimesheet(loggedInUser);
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}

	public List<TimesheetMasterDto> getMonthlyApprCountManTimesheet(Long loggedInUser) {
		List<TimesheetMaster> list= employeeTimesheetDao.getMonthlyApprCountManTimesheet(loggedInUser);
		List<TimesheetMasterDto> timesheetMasterDtoList = list.stream()
				.map((timesheet) -> this.modelmapper.map(timesheet, TimesheetMasterDto.class))
				.collect(Collectors.toList());
		return timesheetMasterDtoList;
	}

	public List<TimesheetMaster> getTimeshDetailesByDate(Date timesheetDate, UserMaster loggedInUser) {
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = date.format(timesheetDate);
		return employeeTimesheetDao.getTimeshDetailesByDate(formattedDate,loggedInUser.getEmpCode());
	}

	public List<UserMasterDto> getAllAdminList() {
		UserRoleMaster userRoleMasterInstance = userRoleMasterDao.getById(1L);
		List<UserMaster> userMaster=userDao.findByfkUserRoleId(userRoleMasterInstance);
		List<UserMasterDto> userMasterDto = userMaster.stream()
				.map((masters) -> this.modelmapper.map(masters, UserMasterDto.class))
				.collect(Collectors.toList());
		return userMasterDto;
	}

}
