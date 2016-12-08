package com.sdn.loadBalancing;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONUtil {
	
	public ArrayList<LinkInfo> getLinkData() throws MalformedURLException, IOException{
		ArrayList<LinkInfo> linkArray = new ArrayList<LinkInfo>();
		StringBuffer temp = new StringBuffer();
		String url = "http://localhost:8080/wm/topology/links/json";
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
		//JSONObject json = new JSONObject(strJson);
		JSONArray linksArray = new JSONArray(strJson);
		//System.out.println(linkJson);
		JSONObject portJson = returnJson("http://localhost:8080/wm/core/switch/all/port/json");
		for (int i = 0; i < linksArray.length(); i++) {
		  PortData pd1 = new PortData();
		  PortData pd2 = new PortData();
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
		  pd1 = getPortInfo(portJson, srcSwitch, srcPort);
		  int srcTra = pd1.getTransmitBytes();
		  pd2 = getPortInfo(portJson, dstSwitch, dstPort);
		  int dstRec = pd2.getReceiveBytes();
		  int sum = srcTra + dstRec;
//		  System.out.println(srcSwitch + "  " + srcPort + " Tra:" + srcTra);
//		  System.out.println(dstSwitch + "  " + dstPort + " Rec:" + dstRec);
//		  int sum = srcTra;
		  li.setNowCost(sum); 
		  linkArray.add(li);
		  pd1 = null;
		  pd2 = null;
		}
		//System.out.println(linkArray);
		return linkArray;
	}
	
	//返回指定switch的指定port的transmitBytes和receiveBytes
	public PortData getPortInfo2(String sw, int port) throws MalformedURLException, IOException {
		PortData pd = new PortData();
		int transmitBytes;
		int receiveBytes;
		StringBuffer temp = new StringBuffer();
		String url = "http://localhost:8080/wm/core/switch/all/port/json";
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
		JSONObject json = new JSONObject(strJson);
//		System.out.println(json);
		Set<String> setSwitch = json.keySet();
		for(String switchID : setSwitch) {
//			System.out.println(switchID);
			if(switchID.equals(sw)) {
				pd.setSwitchId(switchID);
				JSONArray jsonSwitches = json.getJSONArray(switchID);
				for(int i = 0; i < jsonSwitches.length(); i++) {
					JSONObject obj = jsonSwitches.getJSONObject(i);
					if(obj.getInt("portNumber") == port) {
						pd.setPortNumber(port);
						transmitBytes = obj.getInt("transmitBytes");
						receiveBytes = obj.getInt("receiveBytes");
						pd.setTransmitBytes(transmitBytes);
						pd.setReceiveBytes(receiveBytes);
						break;
					}
				}
			}
		}
		return pd;
	}
	
	//返回指定switch的指定port的transmitBytes和receiveBytes
		public PortData getPortInfo(JSONObject json,String sw, int port) throws MalformedURLException, IOException {
			PortData pd = new PortData();
			int transmitBytes;
			int receiveBytes;

//			System.out.println(json);
			Set<String> setSwitch = json.keySet();
			for(String switchID : setSwitch) {
//				System.out.println(switchID);
				if(switchID.equals(sw)) {
					pd.setSwitchId(switchID);
					JSONArray jsonSwitches = json.getJSONArray(switchID);
					for(int i = 0; i < jsonSwitches.length(); i++) {
						JSONObject obj = jsonSwitches.getJSONObject(i);
						if(obj.getInt("portNumber") == port) {
							pd.setPortNumber(port);
							transmitBytes = obj.getInt("transmitBytes");
							receiveBytes = obj.getInt("receiveBytes");
							pd.setTransmitBytes(transmitBytes);
							pd.setReceiveBytes(receiveBytes);
							break;
						}
					}
				}
			}
			return pd;
		}
		
		//返回指定url的json对象
		public JSONObject returnJson(String url) throws MalformedURLException, IOException {
			StringBuffer temp = new StringBuffer();
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
			JSONObject json = new JSONObject(strJson);
			return json;
		}
}	
