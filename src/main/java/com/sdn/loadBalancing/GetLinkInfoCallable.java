package com.sdn.loadBalancing;

import java.util.ArrayList;
import java.util.concurrent.Callable;


public class GetLinkInfoCallable implements Callable<ArrayList<LinkInfo>> {

	@Override
	public ArrayList<LinkInfo> call() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<LinkInfo> al = new ArrayList<LinkInfo>();
		ArrayList<LinkInfo> al1 = new ArrayList<LinkInfo>();
		ArrayList<LinkInfo> al2 = new ArrayList<LinkInfo>();

		while(true) {
			JSONUtil ju = new JSONUtil();
			al1 = ju.getLinkData();
			System.out.println(al1);
			Thread.sleep(2000);
			al2 = ju.getLinkData();
			System.out.println(al2);
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
						li.setDiffCost(diff);
						al.add(li);
						continue;
					}
				}
			}

			return al;
		}
		
	}
	
}
