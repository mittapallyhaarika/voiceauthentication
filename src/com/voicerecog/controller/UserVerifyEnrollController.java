package com.voicerecog.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voicerecog.core.GetPhrases;
import com.voicerecog.core.RecordAudioShort;
import com.voicerecog.core.VerifyEnrollment;
import com.voicerecog.core.VerifyUser;
import com.voicerecog.dao.UserInfo;
import com.voicerecog.dao.UserInfoImplementationDAO;

public class UserVerifyEnrollController extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		VerifyEnrollment verification_process = new VerifyEnrollment();
		String verification_id = verification_process.createVerifyID();
		try {
			UserInfoImplementationDAO dao = new UserInfoImplementationDAO();
			String enrolled = verification_process.startEnrollment(verification_id);
			
			while(enrolled.equalsIgnoreCase("hastoenroll")){
				enrolled = verification_process.startEnrollment(verification_id);

			}

			dao.updateDetails(verification_id, verification_process.getPhrase());

			System.out.println("In controller, now user is enrolled");


		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}

		RequestDispatcher view = request.getRequestDispatcher("/successfullyenrolled.jsp");

		view.forward(request, response);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		try {
			RecordAudioShort record = new RecordAudioShort();
			UserInfo user = new UserInfo();
			UserInfoImplementationDAO dao;
			dao = new UserInfoImplementationDAO();
			String forward = "";

			record.startRecord();

			FileInputStream input = new FileInputStream("id.properties");
			Properties prop = new Properties();
			// load a properties file
			prop.load(input);

			// get the property value and print it out

			String sid =  prop.getProperty("sid");

			System.out.println("Read from properties file sid = "+sid);

			user = dao.retrieveVerificationID(sid);
			
			String name = user.getName();
			String verifyid = user.getEnroll_status();

			VerifyUser newverify = new VerifyUser();
			String isverified = newverify.startVerificationProcess(name, verifyid);
			if((newverify.getError()).equalsIgnoreCase("exception")){
				
				forward = "/tryagain.jsp";
			}
			
			if (isverified.equalsIgnoreCase("failed")){
				forward = "/verificationfailed.jsp";
				
			}
			else
				forward = "/verificationsuccessful.jsp";
			
			request.setAttribute("name", name);
			request.setAttribute("phrase", isverified);
			
			
			RequestDispatcher view = request.getRequestDispatcher(forward);

			view.forward(request, response);




		} catch (Exception e) {
			
			e.printStackTrace();
		}



	}



}
