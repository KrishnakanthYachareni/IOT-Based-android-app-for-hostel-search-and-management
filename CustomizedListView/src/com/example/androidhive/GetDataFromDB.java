package com.example.androidhive;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetDataFromDB {

	public String getDataFromDB() {
		try {

			HttpPost httppost;
			HttpClient httpclient;
			httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://192.168.43.237/webapp/GetUsers.php"); // change this to your URL.....
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			final String response = httpclient.execute(httppost,
					responseHandler);
			
			return response.trim();

		} catch (Exception e) {
			System.out.println("ERROR : " + e.getMessage());
			return "error";
		}
	}
}
