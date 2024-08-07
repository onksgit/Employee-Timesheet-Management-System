package com.tsms.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tsms.web.entity.TimesheetMaster;
import com.tsms.web.entity.UserMaster;

@Repository
public interface Managerdao extends JpaRepository<TimesheetMaster, Long> {

//	@Query(value = "SELECT u.pk_id,upm.fk_manager,fk_user FROM t_mst_user u "
//	        + "JOIN t_user_project_manager upm ON upm.fk_user=u.pk_id "
//	        + "WHERE upm.fk_manager = :managerId OR upm.fk_user=:managerId", nativeQuery = true)
//	public List<UserMaster> getsendMesaageEmployee(UserMaster managerId);

	@Query(value = "SELECT * from t_mst_user", nativeQuery = true)
	public List<UserMaster> getsendMesaageEmployee();


	
}
