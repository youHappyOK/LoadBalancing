package com.sdn.loadBalancing;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class GetLinkInfoRunable implements Runnable{

	@Override
	public void run() {
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
						//li.setDiffCost(diff);
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
			
		}

	}

}
