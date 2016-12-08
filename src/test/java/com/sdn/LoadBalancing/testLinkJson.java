package com.sdn.LoadBalancing;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;



import java.util.Set;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sdn.loadBalancing.JSONUtil;
import com.sdn.loadBalancing.LinkInfo;
import com.sdn.loadBalancing.PortData;

public class testLinkJson {
	
	HttpURLConnection httpcon;
	OutputStream os;
	InputStream is;
	CloseableHttpClient httpClient;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetJson() throws MalformedURLException, IOException{
		HashMap<String, ArrayList<Integer>> switchInfo = new HashMap<String, ArrayList<Integer>>();
		StringBuffer temp = new StringBuffer();
		String url = "http://localhost:8080/wm/topology/links/json";
		System.out.println(url);
		URLConnection uc = new URL(url).openConnection();
		uc.setConnectTimeout(10000);
		uc.setDoOutput(true);
		InputStream in = new BufferedInputStream(uc.getInputStream());
		Reader rd = new InputStreamReader(in, "utf-8");
		int c = 0;
		while ((c = rd.read()) != -1) {
			temp.append((char) c);
		}
		in.close();
		String strJson;
		strJson = temp.toString();

		//JSONObject json = new JSONObject(strJson);
		JSONArray linksArray = new JSONArray(strJson);
		//System.out.println(linkJson);
		for (int i = 0; i < linksArray.length(); i++) {
		  JSONObject o = linksArray.getJSONObject(i);
		  String srcSwitch = o.getString("src-switch");
		  int srcPort = o.getInt("src-port");
		  if(switchInfo.containsKey(srcSwitch)) {
			  switchInfo.get(srcSwitch).add(srcPort);
		  }
		  else {
			  ArrayList<Integer> ports = new ArrayList<Integer>();
			  ports.add(srcPort);
			  switchInfo.put(srcSwitch, ports);
		  }
		}
		//System.out.println(switchInfo);
	}
	
	@Test
	public void getLinkData() throws MalformedURLException, IOException{
		ArrayList<LinkInfo> linkArray = new ArrayList<LinkInfo>();
		StringBuffer temp = new StringBuffer();
		String url = "http://localhost:8080/wm/topology/links/json";
		System.out.println(url);
		URLConnection uc = new URL(url).openConnection();
		uc.setConnectTimeout(10000);
		uc.setDoOutput(true);
		InputStream in = new BufferedInputStream(uc.getInputStream());
		Reader rd = new InputStreamReader(in, "utf-8");
		int c = 0;
		while ((c = rd.read()) != -1) {
			temp.append((char) c);
		}
		in.close();
		String strJson;
		strJson = temp.toString();

		//JSONObject json = new JSONObject(strJson);
		JSONArray linksArray = new JSONArray(strJson);
		//System.out.println(linkJson);
		for (int i = 0; i < linksArray.length(); i++) {
		  LinkInfo li = new LinkInfo();
		  JSONObject o = linksArray.getJSONObject(i);
		  String srcSwitch = o.getString("src-switch");
		  li.setSrcSwitch(srcSwitch);
		  String dstSwitch = o.getString("dst-switch");
		  li.setDstSwitch(dstSwitch);
		  int srcPort = o.getInt("src-port");
		  li.setSrcPort(srcPort);
		  int dstPort = o.getInt("dst-port");
		  li.setDstPort(dstPort);
		  linkArray.add(li);
		}
		System.out.println(linkArray);
//		return linkArray;
	}
	
	
	@Test
	public void testGetLinkData() throws MalformedURLException, IOException {
		ArrayList<LinkInfo> al = new ArrayList<LinkInfo>();
		JSONUtil ju = new JSONUtil();
		al = ju.getLinkData();
		System.out.println(al);
	}
	
	
	@Test
	public void getFlowData() throws MalformedURLException, IOException {
		StringBuffer temp = new StringBuffer();
		String url = "http://localhost:8080/wm/core/switch/all/port/json";
		System.out.println(url);
		URLConnection uc = new URL(url).openConnection();
		uc.setConnectTimeout(10000);
		uc.setDoOutput(true);
		InputStream in = new BufferedInputStream(uc.getInputStream());
		Reader rd = new InputStreamReader(in, "utf-8");
		int c = 0;
		while ((c = rd.read()) != -1) {
			temp.append((char) c);
		}
		in.close();
		String strJson;
		strJson = temp.toString();
		JSONObject json = new JSONObject(strJson);
		
		Set<String> setSwitch = json.keySet();
		for(String switchID : setSwitch) {
			//if(switchID == sw)
		}
	}
//	@Test
//	public void nmb() {
//		for(int i = 0; i <=255; i++) {
//			int[] str = new int[8];
//			String binStr = Integer.toBinaryString(i); 
//			int len = binStr.length();
//			for(int j = 0; j < 8 - len; ++j) {
//				str[j] = 0;
//			}
//			for(int k = 8 - len, m = 0; k <= 7 && m < len; ++k, ++m) {
//				str[k] = Integer.parseInt(String.valueOf(binStr.charAt(m)));
//			}
//			for(int n = 0; n <= 7; ++n) {
//				System.out.print(str[n]);
//			}
//			System.out.println();
//		}
//	}
	
//	@Test
//	public void testrest(JSONObject obj) {
//		try {
//			httpClient = HttpClients.createDefault();  
//			httpcon = (HttpURLConnection) ((new URL("http://localhost:8080/wm/ccbalancer/topocosts/json").openConnection()));
//			httpcon.setDoOutput(true);
//			httpcon.setRequestProperty("Content-Type", "application/json");
//			httpcon.setRequestProperty("Accept", "application/json");
//			httpcon.setRequestMethod("POST");
//			httpcon.connect();
//
//			DataOutputStream out = new DataOutputStream(
//					httpcon.getOutputStream());
//	        
//	
//	        out.writeBytes(obj.toString());
//	        out.flush();
//	        out.close();
//	
//	        //读取响应
//	        BufferedReader reader = new BufferedReader(new InputStreamReader(
//	                httpcon.getInputStream()));
//	        String lines;
//	        StringBuffer sb = new StringBuffer("");
//	        while ((lines = reader.readLine()) != null) {
//	            lines = new String(lines.getBytes(), "utf-8");
//	            sb.append(lines);
//	        }
//	        System.out.println(sb);
//	        reader.close();
//	        // 断开连接
//	        httpcon.disconnect();
//		} 
//		catch (MalformedURLException e) {
//		    // TODO Auto-generated catch block
//		    e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//		    // TODO Auto-generated catch block
//		    e.printStackTrace();
//		} catch (IOException e) {
//		    // TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	@Test
	public void testAddCost(){
//		JSONArray ja = new JSONArray("[{\'src\':\'00:00:00:00:00:00:00:01\',\'outPort\':\'2\',\'dst\':\'00:00:00:00:00:00:00:02\',\'inPort\':\'1\',\'cost\':\'10\'}]");
		JSONArray ja = new JSONArray();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("src", "00:00:00:00:00:00:00:01");
		map.put("outPort", "2");
		map.put("dst", "00:00:00:00:00:00:00:02");
		map.put("inPort", "1");
		map.put("cost", "9");
		ja.put(0, map);

//		System.out.println(ja);
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
	
	@Test
	public void testLB() throws MalformedURLException, IOException {
		while(true) {
			ArrayList<LinkInfo> linkArray = new ArrayList<LinkInfo>();
			StringBuffer temp = new StringBuffer();
			String url = "http://localhost:8080/wm/ccbalancer/topocosts/json";
			//System.out.println(url);
			URLConnection uc = new URL(url).openConnection();
			uc.setConnectTimeout(10000);
			uc.setDoOutput(true);
			InputStream in = new BufferedInputStream(uc.getInputStream());
			Reader rd = new InputStreamReader(in, "utf-8");
			int c = 0;
			while ((c = rd.read()) != -1) {
				temp.append((char) c);
			}
			in.close();
			String strJson;
			strJson = temp.toString();
			System.out.println(temp);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void test1() {
		ArrayList<LinkInfo> al = new ArrayList<LinkInfo>();
		JSONUtil ju = new JSONUtil();
		try {
			al = ju.getLinkData();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(al);
	}
}
