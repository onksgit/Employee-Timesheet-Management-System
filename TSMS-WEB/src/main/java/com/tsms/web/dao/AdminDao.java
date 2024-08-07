package com.tsms.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tsms.web.entity.ProjectMaster;
import com.tsms.web.entity.UserMaster;

@Repository
public interface AdminDao extends JpaRepository<UserMaster, Long> {

    @Query(value = "SELECT * FROM t_mst_user WHERE fk_user_role_id = :managerid OR fk_user_role_id = :adminid", nativeQuery = true)
	List<UserMaster> findByFkDesignation(@Param("managerid") Long managerid,@Param("adminid") Long adminid);


	List<UserMaster> findAllByOrderByFirstNameAsc();

}
