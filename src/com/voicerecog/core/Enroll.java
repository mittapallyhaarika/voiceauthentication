
package com.voicerecog.core;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Enroll {


	public String enrolluser() 
	{
		HttpClient httpclient = HttpClients.createDefault();
		String status="";
		try
		{
			FileInputStream input = new FileInputStream("id.properties");
			Properties prop = new Properties();
			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println(prop.getProperty("identifierID"));
			String profileId=prop.getProperty("identifierID");
			String enroll_api="https://westus.api.cognitive.microsoft.com/spid/v1.0/identificationProfiles/" + profileId + "/enroll";

			
			URIBuilder builder = new URIBuilder(enroll_api);

			builder.setParameter("shortAudio", "false");

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Content-Type", "application/octet-stream");
			//request.setHeader("Content-Type", "multipart/form-data");
			
			
			

			request.setHeader("Ocp-Apim-Subscription-Key", "fd7048f4a3674955a1cd27a3579e9bbb");


			// Request body
			

			FileEntity reqEntity = new FileEntity(new File("E:/audio/newrecordingwebapp.wav"), ContentType.APPLICATION_OCTET_STREAM);

			request.setEntity(reqEntity);

			System.out.println("Sending request...");

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();



			input.close();
			if (entity != null) 
			{

				//System.out.println(EntityUtils.toString(entity));
				System.out.println("Enrolling now...");

				status = enrollmentStatus(profileId);
				
				
				if(status.equalsIgnoreCase("enrolled"))
				{
					return status;

				}
			}
			else
				System.out.println("No response");
			status="no response";
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return status;
	}

	public String enrollmentStatus(String profileId){

		GetProfile newprofile = new GetProfile();
		
		boolean currentstatus=newprofile.getProfileStatus(profileId);

		if( currentstatus == true){
			
			return "enrolled";
		}
		else
		{		enrolluser();
		return "enrolling again";
		}


	}

}
