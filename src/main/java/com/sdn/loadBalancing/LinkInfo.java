package com.sdn.loadBalancing;

public class LinkInfo{
	
	private String srcSwitch;
	private String dstSwitch;
	private int srcPort;
	private int dstPort;
	private int nowCost;
	private int diffCost;
	
	public int getDiffCost() {
		return diffCost;
	}
	public void setDiffCost(int diffCost) {
		this.diffCost = diffCost;
	}
	public int getNowCost() {
		return nowCost;
	}
	public void setNowCost(int nowCost) {
		this.nowCost = nowCost;
	}
	public String getSrcSwitch() {
		return srcSwitch;
	}
	public void setSrcSwitch(String srcSwitch) {
		this.srcSwitch = srcSwitch;
	}
	public String getDstSwitch() {
		return dstSwitch;
	}
	public void setDstSwitch(String dstSwitch) {
		this.dstSwitch = dstSwitch;
	}
	public int getSrcPort() {
		return srcPort;
	}
	public void setSrcPort(int srcPort) {
		this.srcPort = srcPort;
	}
	public int getDstPort() {
		return dstPort;
	}
	public void setDstPort(int dstPort) {
		this.dstPort = dstPort;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "源交换机：" + srcSwitch + "  " + "目的交换机：" + dstSwitch +"  " + "源端口：" + srcPort + "  " + "目的端口：" + dstPort + "  " + "总流量："+ nowCost + "  " + "实时流量" + diffCost;
	}

	@Override
	public boolean equals(Object o){
		if(!(o instanceof LinkInfo)) {
			return false;
		}
		LinkInfo l = (LinkInfo)o;
		return (l.getSrcSwitch().equals(this.getSrcSwitch())) && (l.getDstSwitch().equals(this.getDstSwitch())) 
				&& (l.getSrcPort()== this.getSrcPort()) && (l.getDstPort() == this.getDstPort());
	}

}
