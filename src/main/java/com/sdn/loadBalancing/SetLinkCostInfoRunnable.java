package com.sdn.loadBalancing;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;


public class SetLinkCostInfoRunnable implements Runnable{
	
	private JSONArray ja;
	private AddCost ac;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		while(true) {
			ArrayList<LinkInfo> al = new ArrayList<LinkInfo>();
			ArrayList<LinkInfo> al1 = new ArrayList<LinkInfo>();
			ArrayList<LinkInfo> al2 = new ArrayList<LinkInfo>();

			JSONUtil ju = new JSONUtil();
			try {
				al1 = ju.getLinkData();
				//System.out.println("2ms前统计：  " + al1);
				Thread.sleep(2000);
				al2 = ju.getLinkData();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("2ms后统计：  " +al2);
//			System.out.println(al2.size());
			for(int i = 0; i < al1.size(); i++) {
				for(int j = 0; j < al2.size(); j++) {
//					System.out.println(al1.get(i).equals(al2.get(j)));
					LinkInfo li = new LinkInfo();
					if(al1.get(i).equals(al2.get(j))) {
						li.setSrcSwitch(al2.get(j).getSrcSwitch());
						li.setDstSwitch(al2.get(j).getDstSwitch());
						li.setSrcPort(al2.get(j).getSrcPort());
						li.setDstPort(al2.get(j).getDstPort());
						li.setNowCost(al2.get(j).getNowCost());
						int diff = (al2.get(j).getNowCost() - al1.get(i).getNowCost()) / 2;
						if(diff == 0) {
							li.setDiffCost(1);
						}
						else {
							li.setDiffCost(diff);
						}
						al.add(li);
						continue;
					}
				}
			}
			System.out.println("此段时间流量：  " + al);
			/*
			 * 将cost通过restful api写入floodlight
			 */
			ja = new JSONArray();
			ac = new AddCost();
			int index = 0;
			Iterator<LinkInfo> it = al.iterator();
			while(it.hasNext()) {
				HashMap<String, String> map = new HashMap<String, String>();
				LinkInfo li = (LinkInfo) it.next();
				map.put("src", li.getSrcSwitch());
				map.put("outPort", "" + li.getSrcPort());
				map.put("dst", li.getDstSwitch());
				map.put("inPort", "" + li.getDstPort());
				map.put("cost", "" + li.getDiffCost());
				ja.put(index, map);
				++index;
			}
			ac.addCost(ja);
		}

	}
	

}
