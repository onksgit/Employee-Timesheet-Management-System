package com.tsms.web.dao;

import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;

import com.tsms.web.dto.TimesheetDataPojo;
import com.tsms.web.dto.TimesheetMasterDto;
import com.tsms.web.entity.TimesheetMaster;
import com.tsms.web.entity.UserMaster;

@Repository
public interface EmployeeTimesheetDao extends JpaRepository<TimesheetMaster, Long>{

    @Query(value = "SELECT * FROM t_mst_timesheet WHERE fk_user = :fk_user AND added_date_time IS NOT NULL ORDER BY timesheet_date DESC LIMIT 7", nativeQuery = true)
	List<TimesheetMaster> getLast7DaysWork(@Param("fk_user") Long fk_user);

    @Query(value = "SELECT * FROM t_mst_timesheet WHERE fk_manager = :managerId AND approval_status ='Pending' ORDER BY timesheet_date", nativeQuery = true)
	List<TimesheetMaster> getPendingStatusUsers(@Param("managerId") Long managerId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE t_mst_timesheet SET approval_date =NOW(), approval_status ='Approved', manager_remark=:remark WHERE pk_id = :timesheetId", nativeQuery = true)
	void updateRemark(@Param("remark") String remark, @Param("timesheetId") Long timesheetId);

	TimesheetMaster findByEmpCode(String empCode);

	//this is for manager view
	@Query(value = "SELECT * FROM t_mst_timesheet WHERE fk_manager = :id AND (request_date_approval_status='Pending' OR request_date_approval_status='Approved') AND MONTH(request_date)=MONTH(CURRENT_DATE()) order by request_date_approval_status DESC ", nativeQuery = true)
	List<TimesheetMaster> getDateRequestTimesheet(@Param("id") Long long1);

	
	@Query(value = "SELECT * FROM t_mst_timesheet WHERE emp_code = :empCode AND request_date_approval_status = 'Approved'", nativeQuery = true)
	List<TimesheetMaster> getApprovedDates(@Param("empCode") String empCode);

	@Query(value = "SELECT * FROM t_mst_timesheet WHERE emp_code = :empCode AND request_flag=false and date_update_count=1 AND YEAR(request_date) = YEAR(CURDATE()) AND MONTH(request_date) = MONTH(:requestedDate) AND approval_status IS NULL", nativeQuery = true)
	List<TimesheetMaster> getEmpRequestedDate(String empCode,Date requestedDate);

	@Query(value = "SELECT * FROM t_mst_timesheet WHERE emp_code = :empCode limit 7", nativeQuery = true)
	List<TimesheetMaster> getEmployeeTimesheetdata(String empCode);

	@Query(value = "SELECT COUNT(emp_code), emp_name FROM t_mst_timesheet WHERE fk_manager=:primaryId group by 2" , nativeQuery = true)
	List<TimesheetMaster> getEmployeeForAwards(@Param("primaryId") Long primaryId);

	@Query(value = "SELECT * FROM t_mst_timesheet WHERE fk_manager = :managerId AND approval_status ='Pending' AND emp_code=:empcode ORDER BY timesheet_date DESC LIMIT 7", nativeQuery = true)
	List<TimesheetMaster> getByEmpCode(@Param("empcode") String empcode, @Param("managerId") Long managerId);

	//this is for employee view
	@Query(value = "SELECT * FROM t_mst_timesheet WHERE emp_code = :empCode  AND request_date IS NOT NULL AND MONTH(request_date) = MONTH(CURRENT_DATE()) AND (request_date_approval_status='Pending' OR request_date_approval_status='Approved')", nativeQuery = true)
	List<TimesheetMaster> getDateRequestTimesheetEmployee(@Param("empCode") String empCode);

	@Query(value = "SELECT * FROM t_mst_timesheet WHERE emp_code = :empCode AND request_date=:date AND MONTH(date) = MONTH(CURRENT_DATE()) AND request_flag=true ", nativeQuery = true)
	TimesheetMaster findByEmpCodeAndDate(@Param("empCode") String empCode, @Param("date") String string);
	
	//For Award rating display
	@Query(value = "SELECT DISTINCT sheet.emp_code, user.first_name, user.last_name "
			+ " FROM t_mst_timesheet sheet "
			+ " JOIN t_mst_user user ON user.emp_code = sheet.emp_code "
			+ " WHERE sheet.fk_manager = :managerId"
			+ " AND "
			+ " MONTH(sheet.timesheet_date) =  :month "
			+ "	AND YEAR(sheet.timesheet_date) =  :year "
			+ " AND sheet.approval_status = 'Approved'", nativeQuery = true)
	List<Object[]> getApprovedTimesheetForAward(UserMaster managerId,String month ,String year);

    @Query(value = "SELECT * FROM t_mst_timesheet WHERE emp_code = :empCode AND timesheet_date = :timesheetDate AND project_name = :projectname", nativeQuery = true)
	TimesheetMaster getTimeshDetailesByDate(@Param("timesheetDate") String formattedDate, String empCode, String projectname);

	@Query(value = "SELECT * FROM t_mst_timesheet WHERE emp_code = :empCode AND request_date=:timesheetDate", nativeQuery = true)
	TimesheetMaster getTimeshDetailesByRequestedDate(@Param("timesheetDate") String timesheetDate, @Param("empCode") String empCode);
	
//    @Query(value = "SELECT distinct(user.emp_code) ,user.first_name, user.last_name,user.branch,user.department,user.grade,user.is_active,user.titile,sheet.approval_status FROM t_mst_timesheet sheet JOIN t_mst_user user ON user.emp_code=sheet.emp_code where sheet.fk_manager=:managerId", nativeQuery = true)
//    List<Object[]> getPendingStatusUsersIn7Days(@Param("managerId") Long managerId);
	@Query(value = "SELECT DISTINCT user.emp_code, user.first_name, user.last_name, user.branch, user.department,user.fk_designation ,user.grade, user.is_active, user.titile, sheet.approval_status " +
            "FROM t_mst_timesheet sheet " +
            "JOIN t_mst_user user ON user.emp_code = sheet.emp_code " +
            "WHERE sheet.fk_manager = :managerId " +
            "  AND DAYOFWEEK(sheet.timesheet_date) BETWEEN 2 AND 7 " +
            "  AND sheet.timesheet_date <= CURRENT_DATE + INTERVAL 8 DAY " +
            "ORDER BY user.emp_code", nativeQuery = true)
	List<Object[]> getPendingStatusUsersIn7Days(@Param("managerId") Long managerId);

    @Query(value = "SELECT * FROM t_mst_timesheet where DATE(added_date_time) = current_date", nativeQuery = true)
    List<TimesheetMaster> getDailyCountTimesheet();

    @Query(value = "SELECT * FROM t_mst_timesheet WHERE YEARWEEK(added_date_time, 1) = YEARWEEK(CURDATE(), 1)", nativeQuery = true)
	List<TimesheetMaster> getWeeklyCountTimesheet();
    
    @Query(value = "SELECT * FROM t_mst_timesheet sheet where MONTH(added_date_time) = MONTH(CURRENT_DATE())", nativeQuery = true)
 	List<TimesheetMaster> getMonthlyCountTimesheet();

//AND fk_manager=:fkManager
    @Query(value = "SELECT * FROM t_mst_timesheet where emp_code=:empCode AND fk_manager=:fkManager AND  MONTH(timesheet_date) = MONTH(CURRENT_DATE()) ", nativeQuery = true)
	List<TimesheetMaster> getMonthlyTimesheetWork(@Param("empCode")String empCode, @Param("fkManager")Long fkManager);

	@Query(value = "SELECT * FROM t_mst_timesheet WHERE fk_manager = :managerId AND approval_status ='Approved' AND emp_code=:empcode ORDER BY timesheet_date", nativeQuery = true)
	List<TimesheetMaster> getByEmpCodeList(String empcode, Long managerId);

	@Query(value = "SELECT * FROM t_mst_timesheet sheet where DATE(added_date_time)=current_date AND approval_status='Approved'", nativeQuery = true)
	List<TimesheetMaster> getDailyApprCountTimesheet();
	
    @Query(value = "SELECT * FROM t_mst_timesheet WHERE YEARWEEK(added_date_time, 1) = YEARWEEK(CURDATE(), 1)  AND approval_status='Approved'", nativeQuery = true)
	List<TimesheetMaster> getWeeklyApprCountTimesheet();
    
    @Query(value = "SELECT * FROM t_mst_timesheet sheet where MONTH(added_date_time) = MONTH(CURRENT_DATE())  AND approval_status='Approved'", nativeQuery = true)
 	List<TimesheetMaster> getMonthlyApprCountTimesheet();

    @Query(value = "SELECT * FROM t_mst_timesheet where fk_user = :fk_user AND DATE(added_date_time) = current_date", nativeQuery = true)
    List<TimesheetMaster> getDailyCountTimesheet(@Param("fk_user") Long loggedInUser);

    @Query(value = "SELECT * FROM t_mst_timesheet WHERE fk_user = :fk_user AND YEARWEEK(added_date_time, 1) = YEARWEEK(CURDATE(), 1)", nativeQuery = true)
	List<TimesheetMaster> getWeeklyCountTimesheet(@Param("fk_user") Long loggedInUser);
    
    @Query(value = "SELECT * FROM t_mst_timesheet sheet where fk_user = :fk_user AND MONTH(added_date_time) = MONTH(CURRENT_DATE())", nativeQuery = true)
 	List<TimesheetMaster> getMonthlyCountTimesheet(@Param("fk_user") Long loggedInUser);
    
	@Query(value = "SELECT * FROM t_mst_timesheet sheet where DATE(added_date_time)=current_date AND approval_status='Approved' AND fk_user = :fk_user", nativeQuery = true)
	List<TimesheetMaster> getDailyApprCountTimesheet(@Param("fk_user") Long loggedInUser);

    @Query(value = "SELECT * FROM t_mst_timesheet WHERE YEARWEEK(added_date_time, 1) = YEARWEEK(CURDATE(), 1)  AND approval_status='Approved' AND fk_user = :fk_user", nativeQuery = true)
	List<TimesheetMaster> getWeeklyApprCountTimesheet(@Param("fk_user") Long loggedInUser);
    
    @Query(value = "SELECT * FROM t_mst_timesheet sheet where MONTH(added_date_time) = MONTH(CURRENT_DATE())  AND approval_status='Approved' AND fk_user = :fk_user", nativeQuery = true)
 	List<TimesheetMaster> getMonthlyApprCountTimesheet(@Param("fk_user") Long loggedInUser);
    
    
    //man
    
    @Query(value = "SELECT * FROM t_mst_timesheet where fk_manager = :fk_manager AND DATE(added_date_time) = current_date", nativeQuery = true)
    List<TimesheetMaster> getDailyCountManTimesheet(@Param("fk_manager") Long loggedInUser);

    @Query(value = "SELECT * FROM t_mst_timesheet WHERE fk_manager = :fk_manager AND YEARWEEK(added_date_time, 1) = YEARWEEK(CURDATE(), 1)", nativeQuery = true)
	List<TimesheetMaster> getWeeklyCountManTimesheet(@Param("fk_manager") Long loggedInUser);
    
    @Query(value = "SELECT * FROM t_mst_timesheet sheet where fk_manager = :fk_manager AND MONTH(added_date_time) = MONTH(CURRENT_DATE())", nativeQuery = true)
 	List<TimesheetMaster> getMonthlyCountManTimesheet(@Param("fk_manager") Long loggedInUser);
    
	@Query(value = "SELECT * FROM t_mst_timesheet sheet where DATE(added_date_time)=current_date AND approval_status='Approved' AND fk_manager = :fk_manager", nativeQuery = true)
	List<TimesheetMaster> getDailyApprCountManTimesheet(@Param("fk_manager") Long loggedInUser);

    @Query(value = "SELECT * FROM t_mst_timesheet WHERE YEARWEEK(added_date_time, 1) = YEARWEEK(CURDATE(), 1)  AND approval_status='Approved' AND fk_manager = :fk_manager", nativeQuery = true)
	List<TimesheetMaster> getWeeklyApprCountManTimesheet(@Param("fk_manager") Long loggedInUser);
    
    @Query(value = "SELECT * FROM t_mst_timesheet sheet where MONTH(added_date_time) = MONTH(CURRENT_DATE())  AND approval_status='Approved' AND fk_manager = :fk_manager", nativeQuery = true)
 	List<TimesheetMaster> getMonthlyApprCountManTimesheet(@Param("fk_manager") Long loggedInUser);

    @Modifying
    @Transactional
    @Query(value = "UPDATE t_mst_timesheet SET approval_date =NOW(), approval_status ='Approved', manager_remark=:remark WHERE emp_code=:empCode AND added_date_time BETWEEN :endDateAsDate AND :startDateAsDate", nativeQuery = true)
    void updateRemark(String remark, String empCode, Date startDateAsDate, Date endDateAsDate);

    @Query(value = "SELECT * FROM t_mst_timesheet WHERE emp_code = :empCode AND timesheet_date = :formattedDate", nativeQuery = true)
	List<TimesheetMaster> getTimeshDetailesByDate(@Param("formattedDate") String formattedDate,@Param("empCode") String empCode);

}
