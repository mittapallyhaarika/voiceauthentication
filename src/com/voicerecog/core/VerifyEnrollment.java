package com.voicerecog.core;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class VerifyEnrollment {
	
	private String phrase;

	public String getPhrase() {
		return phrase;
	}


	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}


	public String createVerifyID() 
	{
		HttpClient httpclient = HttpClients.createDefault();
		String verification_id = "";
		
		

		try
		{
			
			
			URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/spid/v1.0/verificationProfiles");


			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", "fd7048f4a3674955a1cd27a3579e9bbb");




			FileEntity reqEntity = new FileEntity(new File("C:/Users/User/Desktop/Hackathon/src/locale"), ContentType.APPLICATION_JSON);
			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) 
			{
				String jsonresponse = EntityUtils.toString(entity);
				System.out.println(jsonresponse);

				JSONObject jsonObj = new JSONObject(jsonresponse);


				verification_id = jsonObj.getString("verificationProfileId");

			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return verification_id;
	}


	public String startEnrollment(String verification_id){



		HttpClient httpclient = HttpClients.createDefault();
		String enrollstatusjson = "";
		String enrollmentStatus = "";
		int remainingEnrollments = 3;
		
		String hastoenroll = "hastoenroll";


		try
		{
			
			URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/spid/v1.0/verificationProfiles/"+verification_id+"/enroll");


			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Content-Type", "application/octet-stream");
			request.setHeader("Ocp-Apim-Subscription-Key", "fd7048f4a3674955a1cd27a3579e9bbb");


		
			RecordAudioShort newrecord = new RecordAudioShort();
			newrecord.startRecord();
			Thread.sleep(1000);
			FileEntity reqEntity = new FileEntity(new File("E:/audio/idaudio.wav"), ContentType.APPLICATION_OCTET_STREAM);
			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) 
			{
				enrollstatusjson = EntityUtils.toString(entity);
				JSONObject jsonObj = new JSONObject(enrollstatusjson);

				System.out.println("Enrollment json " + jsonObj);
				
				enrollmentStatus = jsonObj.getString("enrollmentStatus");
				remainingEnrollments = jsonObj.getInt("remainingEnrollments");
				this.phrase = jsonObj.getString("phrase");

				if((enrollmentStatus.equalsIgnoreCase("Enrolling"))){

					hastoenroll = "hastoenroll";

				}
				else hastoenroll = "enrolled";




			}

		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}






		return hastoenroll;




	}


}



