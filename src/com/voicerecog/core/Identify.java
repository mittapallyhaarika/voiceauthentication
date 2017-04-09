package com.voicerecog.core;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

//import com.sun.xml.internal.ws.util.StringUtils;
import com.voicerecog.dao.UserInfo;
import com.voicerecog.dao.UserInfoImplementationDAO;
import org.apache.commons.collections4.ListUtils;
public class Identify {
	
	String username;
	public String getSid() {
		return sid;
	}



	public void setSid(String sid) {
		this.sid = sid;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	String sid;

	public String identifyUser(List<String> userlist) 
	{
		HttpClient httpclient = HttpClients.createDefault();
		String operationuri = "";

		String ismatched = "";

		

		try
		{
			
	
			String users = "";
			for(int i =0; i<userlist.size();i++){
				users = users + userlist.get(i) + ",";
			
			}
			
			
			users = users.substring(0, users.length()-1);
			System.out.println("Users:"+users);
			
			URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/spid/v1.0/identify?identificationProfileIds="+users);

			builder.setParameter("shortAudio", "true");

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Content-Type", "application/octet-stream");
			request.setHeader("Ocp-Apim-Subscription-Key", "fd7048f4a3674955a1cd27a3579e9bbb");


			// Request body
			

			FileEntity reqEntity = new FileEntity(new File("E:/audio/idaudio.wav"), ContentType.APPLICATION_OCTET_STREAM);



			request.setEntity(reqEntity);

			System.out.println("Sending request to identify...");

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			Thread.sleep(1000);
			Thread.sleep(1000);

	
			if (entity != null) 
			{

				//String sentence = EntityUtils.toString(entity);
				String sentence = response.toString();
				//System.out.println("This is the response from id "+sentence);
				Pattern pattern = Pattern.compile("(?<=/operations/).*.(?<=X-Asp)");
				Matcher matcher = pattern.matcher(sentence);



				boolean found = false;
				while (matcher.find()) {

					operationuri=matcher.group().toString();

					//System.out.println("I found the text: " + operationuri);
					operationuri = operationuri.substring(0, operationuri.length()-7);
					operationuri.trim();
					found = true;
				}
				if (!found) {
					System.out.println("I didn't find the text");
				}





				ismatched=checkOpStatus(operationuri);
				
				if(ismatched.equalsIgnoreCase("success")) ismatched ="success";
				else ismatched = "failed";
				





			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		
		return ismatched;
	}



	public String checkOpStatus(String operationsuri){


		
		String checkstatus = "";
		String ismatched = "";
		System.out.println("In check operation status");

		HttpClient httpclient = HttpClients.createDefault();

		try
		{
			UserInfoImplementationDAO dao = new UserInfoImplementationDAO();
			UserInfo user = new UserInfo();
			URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/spid/v1.0/operations/"+operationsuri);

			
	
			

			URI uri = builder.build();
			HttpGet request = new HttpGet(uri);
			request.setHeader("Ocp-Apim-Subscription-Key", "fd7048f4a3674955a1cd27a3579e9bbb");

			
			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();
			
		
			if (entity != null) 
			{
				
				String profile_json_string = EntityUtils.toString(entity);
				System.out.println(profile_json_string);
				JSONObject jsonObj = new JSONObject(profile_json_string);
			
				
				checkstatus = jsonObj.getString("status");

				if(checkstatus.equalsIgnoreCase("succeeded"))
				{
					JSONObject obj = jsonObj.getJSONObject("processingResult");
					
					System.out.println("In identify, the id in json is "+ obj.getString("identifiedProfileId"));

					if(  ( (obj.getString("confidence").equalsIgnoreCase("high"))   ) &&    (!(obj.getString("identifiedProfileId")).equalsIgnoreCase("00000000-0000-0000-0000-000000000000")  )    ){

						System.out.println("The user is identified. Identified voice details:");
						
						String identifiedid=obj.getString("identifiedProfileId");
						user = dao.retrieveDetails(identifiedid);
						System.out.println("result identified id : "+identifiedid);
						
						
						ismatched = "success";
						this.setUsername(user.getName());
						this.setSid(user.getSid());
						
					}
					else
						ismatched="noresult";
				}
				else ismatched="noresult";



			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}



		return ismatched;


	}


}
