package com.sdn.loadBalancing;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;

public class AddCost {
	
	private HttpURLConnection httpcon;
	private CloseableHttpClient httpClient;
	
	public void addCost(JSONArray ja) {

		try {
			httpClient = HttpClients.createDefault();  
			httpcon = (HttpURLConnection) ((new URL("http://localhost:8080/wm/ccbalancer/topocosts/json").openConnection()));
			httpcon.setDoOutput(true);
			httpcon.setRequestProperty("Content-Type", "application/json");
			httpcon.setRequestProperty("Accept", "application/json");
			httpcon.setRequestMethod("POST");
			httpcon.connect();

			DataOutputStream out = new DataOutputStream(
					httpcon.getOutputStream());
	        
	
	        out.writeBytes(ja.toString());
	        out.flush();
	        out.close();
	
	        //读取响应
	        BufferedReader reader = new BufferedReader(new InputStreamReader(
	                httpcon.getInputStream()));
	        String lines;
	        StringBuffer sb = new StringBuffer("");
	        while ((lines = reader.readLine()) != null) {
	            lines = new String(lines.getBytes(), "utf-8");
	            sb.append(lines);
	        }
	        System.out.println(sb);
	        reader.close();
	        // 断开连接
	        httpcon.disconnect();
		} 
		catch (MalformedURLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
