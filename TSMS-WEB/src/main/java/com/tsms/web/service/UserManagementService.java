package com.tsms.web.service;

import com.tsms.web.dto.ChangePasswordDto;
import com.tsms.web.dto.LoginDto;
import com.tsms.web.entity.UserMaster;

public interface UserManagementService {

	public UserMaster updateUserPassword(LoginDto loginDto, String newPassword, String encodedSecretKey, String encodedIv);

	public UserMaster updateUserPassword(ChangePasswordDto changePasswordDto, String encryptedPassword, String encodedSecretKey, String encodedIv);

	public UserMaster updateUserPassword(UserMaster usermaster, String encryptedPassword, String encodedSecretKey, String encodedIv);

}
