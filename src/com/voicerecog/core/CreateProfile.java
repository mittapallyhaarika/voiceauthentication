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
import com.voicerecog.dao.*;
public class CreateProfile {
	
	
	String identification_id;
	
	
	public String createProfileMethod(){
		
		
	
		
		
		System.out.println("Creating profile now...");
		
	
	HttpClient httpclient = HttpClients.createDefault();

    try
    {
    	
    	
		
        URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/spid/v1.0/identificationProfiles");


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
        	
        	String profile_json_string = EntityUtils.toString(entity);
        	JSONObject jsonObj = new JSONObject(profile_json_string);
        	identification_id = jsonObj.getString("identificationProfileId");
        	
       
        }
    }
    catch (Exception e)
    {
        System.out.println(e.getMessage());
    }
	return identification_id;
    
}
	
	}
