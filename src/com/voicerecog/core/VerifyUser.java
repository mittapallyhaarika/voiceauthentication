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
import org.json.JSONObject;

import com.voicerecog.dao.UserInfoImplementationDAO;

public class VerifyUser {
	
	String error = "";
	
	
	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}


	public String startVerificationProcess(String name, String verifyid){
        HttpClient httpclient = HttpClients.createDefault();
        String isverified = "";

        try
        {
        	
 
    	
        	
        	
        	UserInfoImplementationDAO dao = new UserInfoImplementationDAO();
        	
            URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/spid/v1.0/verify?verificationProfileId="+verifyid);


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", "fd7048f4a3674955a1cd27a3579e9bbb");


            // Request body
            //StringEntity reqEntity = new StringEntity("{body}");
            
            FileEntity reqEntity = new FileEntity(new File("E:/audio/idaudio.wav"), ContentType.APPLICATION_OCTET_STREAM);
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                String result = EntityUtils.toString(entity);
                JSONObject jsonObj = new JSONObject(result);
                System.out.println("This is the output of verification: "+result);
                System.out.println("the json of this is : "+jsonObj);
                
                if(jsonObj.has("error")){
                	
                	isverified = "failed";
                }
                else{
                String verifyresult = jsonObj.getString("result");
                String confidence = jsonObj.getString("confidence");
                String phrase = jsonObj.getString("phrase");
                
                if(  (verifyresult.equalsIgnoreCase("Accept")) && (  (confidence.equalsIgnoreCase("Normal")) || (confidence.equalsIgnoreCase("High")) ) ){
                	
                	System.out.println("The user is verified as "+name);
                	isverified = phrase;
                	
                }
                else isverified = "failed";
                }
            }
        }
        catch (Exception e)
        {
            this.setError("exception");
        	System.out.println(e.getMessage());
        }
        
        return isverified;
    }

}
