package com.tsms.web.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsms.web.dao.UserDao;
import com.tsms.web.dto.ChangePasswordDto;
import com.tsms.web.dto.LoginDto;
import com.tsms.web.entity.UserMaster;
import com.tsms.web.service.UserManagementService;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	UserDao userDao;
	
	@Override
	public UserMaster updateUserPassword(LoginDto loginDto, String newPassword,String secretKey,String secretIv) {
		String userName=loginDto.getUsername().toUpperCase();
		Optional<UserMaster> optionalUser = Optional.ofNullable(userDao.findByUserName(userName));
		
		if (optionalUser.isPresent()) {
			UserMaster userMaster = optionalUser.get();
			userMaster.setPassword(newPassword);
			userMaster.setSecretKey(secretKey);
			userMaster.setSecretIV(secretIv);
            return userDao.save(userMaster);
        } else {
            throw new RuntimeException("User not found with id: " + userName);
        }
	}

	@Override
	public UserMaster updateUserPassword(ChangePasswordDto changePasswordDto, String encodedPassword,String secretKey,String secretIv) {
		String userName=changePasswordDto.getUserName().toUpperCase();
		Optional<UserMaster> optionalUser = Optional.ofNullable(userDao.findByUserName(userName));
		
		if (optionalUser.isPresent()) {
			UserMaster userMaster = optionalUser.get();
			userMaster.setPassword(encodedPassword);
			userMaster.setSecretKey(secretKey);
			userMaster.setSecretIV(secretIv);
            return userDao.save(userMaster);
        } else {
            throw new RuntimeException("User not found with id: " + userName);
        }
	}

	@Override
	public UserMaster updateUserPassword(UserMaster usermaster, String encryptedPassword, String encodedSecretKey,
			String encodedIv) {
		String userName=usermaster.getUserName().toUpperCase();
		Optional<UserMaster> optionalUser = Optional.ofNullable(userDao.findByUserName(userName));
		
		if (optionalUser.isPresent()) {
			UserMaster userMaster = optionalUser.get();
			userMaster.setPassword(encryptedPassword);
			userMaster.setSecretKey(encodedSecretKey);
			userMaster.setSecretIV(encodedIv);
            return userDao.save(userMaster);
        } else {
            throw new RuntimeException("User not found with id: " + userName);
        }
	}

}
