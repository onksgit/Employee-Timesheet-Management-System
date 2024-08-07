package com.tsms.web.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.tsms.web.entity.TimesheetUnlock;

@Repository
public interface TimesheetUnlockDao extends JpaRepository<TimesheetUnlock, Long> {

	int countByUserIdAndRequestDateBetween(Long userId, Date startDate, Date endDate);

	Optional<TimesheetUnlock> findByUserIdAndStatus(Long userId, String status);

	TimesheetUnlock findByUserIdAndRequestDate(Long userId, Date requestDate);

	List<TimesheetUnlock> findByUserIdAndRequestDateBetween(Long primaryId, Date startDate, Date endDate);

	@Query(value = "SELECT * FROM timesheet_request WHERE status IN('Pending','Approved') AND fk_manager_id=:primaryId", nativeQuery = true)
	List<TimesheetUnlock> findByFkManagerIdAndStatus(Long primaryId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE timesheet_request SET status='Filled' where request_date=:date", nativeQuery = true)
	void updateStatus(@RequestParam("date") Date date);

}
