package com.voicerecog.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voicerecog.core.CreateProfile;
import com.voicerecog.core.Enroll;
import com.voicerecog.core.RecordAudio;
import com.voicerecog.core.RecordAudioShort;
import com.voicerecog.dao.UserInfo;
import com.voicerecog.dao.UserInfoDAO;
import com.voicerecog.dao.UserInfoImplementationDAO;

public class UserSoundRecord extends HttpServlet{




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		RequestDispatcher view = request.getRequestDispatcher( "/index.jsp" );
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




		RecordAudio record = new RecordAudio();
		record.startRecord();
		Enroll enrollnew = new Enroll();
		enrollnew.enrolluser();

		RequestDispatcher view = request.getRequestDispatcher( "/record.jsp" );
		view.forward(request, response);

	}

}
