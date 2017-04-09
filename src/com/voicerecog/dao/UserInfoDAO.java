package com.voicerecog.dao;

import java.util.List;

import com.voicerecog.dao.UserInfo;


//This is the interface that includes all the data access methods
//All these methods are implemented in the UserInfoImplementaionDAO class

public interface UserInfoDAO {

	public boolean addUser(UserInfo user);
	public void updateProfileID(String id, String sid);
	public List<String> allUsers();
	public UserInfo retrieveDetails(String id);
	public String isVerified(String sid);
	public void updateDetails(String id, String phrase);
	public UserInfo retrieveVerificationID(String sid);
	
	
	
}
