package com.voicerecog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.ListUtils;

import com.voicerecog.core.Enroll;
import com.voicerecog.core.Identify;
import com.voicerecog.core.RecordAudio;
import com.voicerecog.core.RecordAudioShort;
import com.voicerecog.dao.UserInfo;
import com.voicerecog.dao.UserInfoImplementationDAO;

public class UserExistingController extends HttpServlet{


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String forward="";
		String action = request.getParameter( "userselection" );


		if( action.equalsIgnoreCase( "identify" ) ) {
			forward = "/identifymainview.jsp";
		}


		else if( action.equalsIgnoreCase( "verify" ) ) {
			forward = "/verifymainview.jsp";
		}

		else if(action.equalsIgnoreCase("Back")){
			forward = "/existingmenu.jsp";


		}
		RequestDispatcher view = request.getRequestDispatcher( forward );
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RecordAudioShort idaudio = new RecordAudioShort();
		Identify iduser = new Identify();
		idaudio.startRecord();
		String result = "";
		String name = "";
		String id = "";
		String forward = "";

		List<String> userlist = new ArrayList<String>();
		try {
			UserInfoImplementationDAO userinfoimpldao = new UserInfoImplementationDAO();
			userlist = userinfoimpldao.allUsers();


			if(userlist.size() < 11)
				result = iduser.identifyUser(userlist);

			else {


				List<List<String>> smallerlist = ListUtils.partition(userlist, 10);

				for(int x=0;x < smallerlist.size();x++){

					System.out.println("The list being sent in iteration is :"+smallerlist.get(x));
					result = iduser.identifyUser(smallerlist.get(x));
					
					if(result.equalsIgnoreCase("success")) break;
				}


			}

			//result = iduser.identifyUser(userlist.get(i), userlist.get(i+1));

			//	System.out.println("the list contains "+userlist.get(i));
			if (result.equalsIgnoreCase("success")){

				//		System.out.println("The user is "+userlist.get(i));
				name = iduser.getUsername();
				id = iduser.getSid();
				forward = "/identifieduser.jsp";
				System.out.println("Name and id in the controller servlet" + name + "  " + id);

				request.setAttribute("username", name);
				request.setAttribute("id", id);


			}

			else
				forward="/usernotfound.jsp";






		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		RequestDispatcher view = request.getRequestDispatcher( forward );

		view.forward(request, response);

	}

}

