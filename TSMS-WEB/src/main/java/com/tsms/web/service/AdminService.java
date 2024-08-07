package com.tsms.web.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsms.pojo.AddUserMasterPojo;
import com.tsms.web.dao.AdminDao;
import com.tsms.web.dao.ProjectDao;
import com.tsms.web.dao.UserDao;
import com.tsms.web.dao.UserProjectManagerDao;
import com.tsms.web.dto.UserMasterDto;
import com.tsms.web.entity.ProjectMaster;
import com.tsms.web.entity.UserMaster;
import com.tsms.web.entity.UserProjectManager;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserProjectManagerDao userProjectManagerDao;

	public UserMaster saveEmployee(AddUserMasterPojo addUserMasterPojo) throws IOException {		
		insertProfileImage(addUserMasterPojo);
		UserMaster userMaster= this.modelMapper.map(addUserMasterPojo, UserMaster.class);
		adminDao.save(userMaster);
		
		List<ProjectMaster> pjlist = addUserMasterPojo.getFkProject();

		for (ProjectMaster plist : pjlist) {
		    UserProjectManager userProjectManager = new UserProjectManager(); // Create new instance in each iteration
		    userProjectManager.setFkUser(userMaster);
		    userProjectManager.setFkProject(plist);
		    userProjectManager.setFkManager(plist.getProjectManager());
		    userProjectManagerDao.save(userProjectManager); // Save the new instance
		}

		return userMaster;

	}

	public List<UserMaster> getAllManagerList() {
		Long managerid= 3L;
		Long adminid=1L;
		return adminDao.findByFkDesignation(managerid,adminid);
	}
	
	public List<ProjectMaster> getAllProjectList() {
		return (List<ProjectMaster>) projectDao.findAll();
	}

	public ProjectMaster saveProject(ProjectMaster projectMaster) {
		return projectDao.save(projectMaster);
	}
	

	public AddUserMasterPojo insertProfileImage(AddUserMasterPojo addUserMasterPojo) throws IOException {
		String filePath = null;
		Long insert = null;
		String addedOn = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		if (!addUserMasterPojo.getProfileImageFile().isEmpty()) {
			String contentType =addUserMasterPojo.getProfileImageFile().getContentType();
			byte[] bytes = addUserMasterPojo.getProfileImageFile().getBytes();
			if (contentType.startsWith("image/jpg") || contentType.startsWith("image/png") || contentType.startsWith("image/jpeg")) {
				filePath = "/opt/DRA/TSMS/".concat(addedOn.concat("/"));
				Path path = Paths.get(filePath);
				if (!Files.exists(path)) {
					Files.createDirectories(path);
				}
				Path filePaths = Paths.get(filePath + addUserMasterPojo.getProfileImageFile().getOriginalFilename());
				Files.write(filePaths, bytes);
				addUserMasterPojo.setProfileImage("/opt/DRA/TSMS/"+addedOn.concat("/")+addUserMasterPojo.getProfileImageFile().getOriginalFilename());
			}
		}
		return addUserMasterPojo;
	}
	
	public boolean deleteProfileImage(String imagePath) throws IOException {
		boolean view=false;
        Path path = Paths.get(imagePath);
        if (Files.exists(path)) {
            Files.delete(path);
            view= true;
        } else {
        	view= false;
            throw new FileNotFoundException("Image not found at path: " + imagePath);
        }
        return view;
    }

    public AddUserMasterPojo updateProfileImage(AddUserMasterPojo addUserMasterPojo) throws IOException {
    	try {
    		
		UserMaster usermaster = userDao.findByUserName(addUserMasterPojo.getEmpCode());
        String existingImagePath = usermaster.getProfileImage();
        

        boolean issuccess;

        // Delete existing image if it exists
        if (existingImagePath != null && !existingImagePath.isEmpty()) {
        	 issuccess= deleteProfileImage(existingImagePath);
        }
    	}catch (Exception e) {
			e.printStackTrace();
		}

        // Insert the new image
        return insertProfileImage(addUserMasterPojo);
    }

	public ProjectMaster findProjectById(Long projectId) {
		return projectDao.findByPrimaryId(projectId);
	}

	public List<UserMaster> getAllEmployeeList() {
		return adminDao.findAll();
	}

	public List<ProjectMaster> findAllByOrderByProjectNameAsc() {
		return projectDao.findAllByOrderByProjectNameAsc();
	}




	

}
