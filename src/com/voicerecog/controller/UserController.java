package com.voicerecog.controller;

import java.io.IOException;


import com.voicerecog.core.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.voicerecog.dao.UserInfo;
import com.voicerecog.dao.UserInfoImplementationDAO;
import com.voicerecog.dao.UserInfoDAO;
import com.voicerecog.dao.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class UserController extends HttpServlet {

	private UserInfoDAO dao;
	private CreateProfile createprofile =new CreateProfile();
	private static final long serialVersionUID = 1L;


	public UserController() throws ClassNotFoundException{
		dao = (UserInfoDAO) new UserInfoImplementationDAO();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter( "usertype" );

		if( action.equalsIgnoreCase( "newuser" ) ) {
			forward = "/user.jsp";
		}


		else if( action.equalsIgnoreCase( "existinguser" ) ) {
			forward = "/existingmenu.jsp";
		}
		RequestDispatcher view = request.getRequestDispatcher( forward );
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserInfo userinfo = new UserInfo();
		userinfo.setName(request.getParameter("name"));
		userinfo.setSid(request.getParameter("sid"));
		
		String forward = "";
		
		boolean success = dao.addUser(userinfo);
		

		if(success){
			String profileID=createprofile.createProfileMethod();
			
			dao.updateProfileID(profileID, userinfo.getSid());

			try {
				Properties prop1 = new Properties();
				OutputStream output = new FileOutputStream("id.properties");
				prop1.setProperty("identifierID", profileID);
				prop1.store(output, null);
				output.close();

			} catch (IOException e) {
				e.printStackTrace(); 
			}


			System.out.println("The Profile ID for "+request.getParameter("name")+" is generated successfully and the ID is "+profileID);
			forward = "/enroll.jsp";


		}

		else{
			
			forward = "/duplicaterecord.jsp";


		}



		RequestDispatcher view = request.getRequestDispatcher( forward );

		view.forward(request, response);

	}








}
