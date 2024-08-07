package com.tsms.web.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tsms.web.entity.BranchMaster;

public interface BranchDao extends CrudRepository<BranchMaster, Long> {

	BranchMaster findByPrimaryId(Long branchId);

	List<BranchMaster> findAllByOrderByBranchNameAsc();

}
