package com.voicerecog.core;

import java.io.FileInputStream;
import java.net.URI;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class GetProfile {

	public boolean getProfileStatus(String identification_id)  {


		boolean isenrolled=false;
		String enrollment_status;
		HttpClient httpclient = HttpClients.createDefault();

		try
		{
			
			
			
			URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/spid/v1.0/identificationProfiles/"+identification_id);


			URI uri = builder.build();
			HttpGet request = new HttpGet(uri);
			request.setHeader("Ocp-Apim-Subscription-Key", "fd7048f4a3674955a1cd27a3579e9bbb");


			// Request body
			//StringEntity reqEntity = new StringEntity("ca06e503-0ce9-47c9-8a86-2bf14c5d6367");
			//request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();



			
			if (entity != null) 
			{

				String profile_json_string = EntityUtils.toString(entity);
				JSONObject jsonObj = new JSONObject(profile_json_string);
				System.out.println(jsonObj);

				enrollment_status = jsonObj.getString("enrollmentStatus");

				System.out.println("This is the enrollment status in the JSON PARSER " + enrollment_status);
				if(enrollment_status.equalsIgnoreCase("enrolled")) {

					System.out.println("The user is enrolled.");
					isenrolled=true;
				}
				else isenrolled=false;

				//System.out.println(EntityUtils.toString(entity));
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}


		
		return isenrolled;

	}


}
