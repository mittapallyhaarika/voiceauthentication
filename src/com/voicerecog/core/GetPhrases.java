package com.voicerecog.core;

import java.io.FileInputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetPhrases {
	String phrases = "";
	List<String> phraselist = new ArrayList<>();
	
	   public List<String> getAllPhrases() 
	    {
	        HttpClient httpclient = HttpClients.createDefault();

	        try
	        {
	        	
	        	
	        	
	            URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/spid/v1.0/verificationPhrases?locale=en-us");


	            URI uri = builder.build();
	            HttpGet request = new HttpGet(uri);
	            request.setHeader("Ocp-Apim-Subscription-Key", "fd7048f4a3674955a1cd27a3579e9bbb");


	            HttpResponse response = httpclient.execute(request);
	            HttpEntity entity = response.getEntity();

	            
	          
	            if (entity != null) 
	            {
	            	String phrases = EntityUtils.toString(entity);
	            	JSONArray jsonarray = new JSONArray(phrases);
	            	for (int i = 0; i < jsonarray.length(); i++) {
	            	    JSONObject jsonobject = jsonarray.getJSONObject(i);
	            	    String phrase = jsonobject.getString("phrase");
	            	    phraselist.add(phrase);
	            	}
	                
	            }
	        }
	        catch (Exception e)
	        {
	            System.out.println(e.getMessage());
	        }
	        
	        return phraselist;
	    }
	   
	   
	   
	}


