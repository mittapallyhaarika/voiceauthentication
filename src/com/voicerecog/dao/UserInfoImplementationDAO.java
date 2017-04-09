package com.voicerecog.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.voicerecog.conn.OracleConnUtils;
import com.voicerecog.core.CreateProfile;
public class UserInfoImplementationDAO implements UserInfoDAO {

	private Connection conn;
	

	public UserInfoImplementationDAO() throws ClassNotFoundException{
		conn=OracleConnUtils.getConnection();
	}
	
	
	
	//*************************************TO ADD NEW USER********************************************

	@Override
	public boolean addUser(UserInfo user) {
		boolean success = false;
		try {

			String query = "insert into USER_DETAILS values (?,?,?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement( query );
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getSid());
			preparedStatement.setString(3, "notverified");
			preparedStatement.setString(4, user.getProfile_id());
			preparedStatement.setString(5, "notverified");


			preparedStatement.executeQuery();

			preparedStatement.close();
			success = true;
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		}
		return success;

	}

	
	// *********************************TO UPDATE PROFILE ID ************************************
public void updateProfileID(String id, String sid){
		
		try {			
            PreparedStatement preparedStatement = conn.prepareStatement("update user_details set profile_id = ? where sid = ?");
            // Parameters start with 1
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, sid);
           
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } 
		
		
	}
	
	
	//*************************************TO GET ALL IDs TO IDENTIFY THE SPEAKER********************************************

	public List<String> allUsers(){

		List<String> userlist = new ArrayList<>();
		try {

			String query = "select profile_id from USER_DETAILS";
			PreparedStatement preparedStatement = conn.prepareStatement( query );
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){

				//				UserInfo users = new UserInfo();
				//				users.setName(rs.getString("NAME"));
				//				users.setSid(rs.getString("SID"));
				//				users.setProfile_id(rs.getString("PROFILE_ID"));

				userlist.add(rs.getString("PROFILE_ID"));

			}


		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return userlist;
	}



	//*************************************TO GET NAME AND SID FOR DISPLAYING AFTER IDENTIFICATION********************************************

	public UserInfo retrieveDetails(String id){
		UserInfo user = new UserInfo();
		try {
			
			String query = "select * from USER_DETAILS where profile_id = ?";
			PreparedStatement preparedStatement = conn.prepareStatement( query );
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				user.setName(rs.getString("name"));
				user.setSid(rs.getString("sid"));
			}

			System.out.println("written");

			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;


	}

	
	//*************************************TO CHECK IF USER ID ALREADY ENROLLED FOR VERIFICATION OR NOT********************************************

	public String isVerified(String sid){

		String isverified = "";
		try {
			
			System.out.println("Executing the query now. received id is "+sid);
			String query = "select ENROLL_STATUS from USER_DETAILS where SID = ?";
			PreparedStatement preparedStatement = conn.prepareStatement( query );
			preparedStatement.setString(1, sid);
			
			
			ResultSet rs = preparedStatement.executeQuery();
			
if(!rs.isBeforeFirst()){
				
				isverified = "doesntexist";
			}
			
			else if (rs.next()) {
				if((rs.getString("enroll_status")).equals("notverified")){
					
					System.out.println("This id is not verified, query executed");
					
					isverified = "false";
				}
			
			
				
				else isverified = "true";
				
				
			}
	
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return isverified;

		
		//****************TO UPDATE INFORMATION THAT THE USER COMPLETED VERIFICATION ENROLLMENT PROCESS*************

		
	}

	public void updateDetails(String id, String phrase){
		
		try {
			
			FileInputStream input = new FileInputStream("id.properties");
			Properties prop = new Properties();
			// load a properties file
			prop.load(input);

			// get the property value and print it out
			
			String sid =  prop.getProperty("sid");
			
			System.out.println("Read from properties file sid = "+sid);
			
            PreparedStatement preparedStatement = conn.prepareStatement("update user_details set enroll_status = ?, verify_id= ? where sid = ?");
            // Parameters start with 1
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, phrase);
            preparedStatement.setString(3, sid );
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("Written phrase in db");
	}
	
	
	
	
	//*************************************TO RETRIEVE VERIFICATION ID AND NAME FOR ALREADY ENROLLED USER********************************************
	
	public UserInfo retrieveVerificationID(String sid){
		
		UserInfo user = new UserInfo();
		try {
			
			System.out.println("Executing the verify id fetch query now. Received id is "+sid);
			String query = "select ENROLL_STATUS, NAME from USER_DETAILS where SID = ?";
			PreparedStatement preparedStatement = conn.prepareStatement( query );
			preparedStatement.setString(1, sid);
			
			
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				user.setEnroll_status((rs.getString("enroll_status")));
				user.setName(rs.getString("name"));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
				
		return user;
		
		
		
		
		
	}

}
