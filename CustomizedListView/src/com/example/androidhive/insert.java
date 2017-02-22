package com.example.androidhive;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class insert extends Activity
{
	
	
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

	
	
	 public void onCreate(Bundle savedInstanceState)
	 
	 {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.insert);
            Button button = (Button) findViewById(R.id.button1);
	        button.setOnClickListener(new View.OnClickListener()
	        {
	               public void onClick(View view)
	                 {
	      
	            	   	String result = null;
	            	   	InputStream is = null;
	            	   	EditText editText = (EditText)findViewById(R.id.e1);
	            	   	String v1 = editText.getText().toString();
	            	   	EditText editText1 = (EditText)findViewById(R.id.e2);
	            	   	String v2 = editText1.getText().toString();
	            	   	EditText editText2 = (EditText)findViewById(R.id.e3);
	            	   	String v3 = editText2.getText().toString(); 
	            	   	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

	            	   	nameValuePairs.add(new BasicNameValuePair("f1",v1));
	            	   	nameValuePairs.add(new BasicNameValuePair("f2",v2));
	            	   	nameValuePairs.add(new BasicNameValuePair("f3",v3));

	            	   	StrictMode.setThreadPolicy(policy); 


	        	//http post
	        	try{
	        	        HttpClient httpclient = new DefaultHttpClient();
	        	        HttpPost httppost = new HttpPost("http://192.168.43.237/webapp/Data/insert.php");
	        	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        	        HttpResponse response = httpclient.execute(httppost); 
	        	        HttpEntity entity = response.getEntity();
	        	        is = entity.getContent();

	        	        Log.e("log_tag", "connection success ");
	        	        Toast.makeText(getApplicationContext(), "You are success fully Booked a hostel", Toast.LENGTH_SHORT).show();
	        	   }
	        	
	        	
	        	catch(Exception e)
	        	{
	        	        Log.e("log_tag", "Error in http connection "+e.toString());
	        	        Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

	        	}
	        	//convert response to string
	        	try{
	        	        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        	        StringBuilder sb = new StringBuilder();
	        	        String line = null;
	        	        while ((line = reader.readLine()) != null) 
	        	        {
	        	                sb.append(line + "\n");
	        	       	        Intent i = new Intent(getBaseContext(),CustomizedListView.class);
	        	                startActivity(i);
	        	        }
	        	        is.close();

	        	        result=sb.toString();
	        	}
	        	catch(Exception e)
	        	{
	        	       Log.e("log_tag", "Error converting result "+e.toString());
	           	}

	   
	        	try{
	        		
	        					JSONObject json_data = new JSONObject(result);

	        	                CharSequence w= (CharSequence) json_data.get("re");
	        	              
	        	                Toast.makeText(getApplicationContext(), w, Toast.LENGTH_SHORT).show();

	        	      
	        	     }
	        	catch(JSONException e)
	        	   {
	        	        Log.e("log_tag", "Error parsing data "+e.toString());
	        	        Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
	        	    }

	        	

	       }
	       });
	        
	        

	 }
	

}
