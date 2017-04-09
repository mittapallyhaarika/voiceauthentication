package com.voicerecog.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voicerecog.core.GetPhrases;
import com.voicerecog.core.Identify;
import com.voicerecog.core.RecordAudioShort;
import com.voicerecog.dao.UserInfo;
import com.voicerecog.dao.UserInfoImplementationDAO;

public class UserVerifyController extends HttpServlet{
	
	String isverified = "";
	
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			List<String> phraselist = new ArrayList<>();
			GetPhrases getphrase = new GetPhrases();
			
			phraselist = getphrase.getAllPhrases();
			
			request.setAttribute("phrases", phraselist);
			
			System.out.println("Back in the doget method all phrases "+phraselist);
			String forward = "/verifyphrase.jsp";
			
			
			RequestDispatcher view = request.getRequestDispatcher(forward);

			view.forward(request, response);
			
			
			
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String sid = request.getParameter("sid");
			
			//Writing sid to properties file
			try {
				Properties prop = new Properties();
				OutputStream output = new FileOutputStream("id.properties");
				prop.setProperty("sid", sid);
				prop.store(output, null);
				output.close();
				
		    } catch (IOException e) {
		        e.printStackTrace(); 
		    }
			
			String forward = "";
			
			try {
				UserInfoImplementationDAO checkverified = new UserInfoImplementationDAO();
				isverified = checkverified.isVerified(sid);
				
				System.out.println("This is the value of isverified in controller "+isverified);
				
				if(isverified.equalsIgnoreCase("true")){
					
					forward = "/verificationstart.jsp";
				}
				else if(isverified.equalsIgnoreCase("false"))
					forward = "/verificationenroll.jsp";
				
				else 
				forward = "/userdoesntexist.jsp";
				
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			

			RequestDispatcher view = request.getRequestDispatcher(forward);

			view.forward(request, response);

		}


}
