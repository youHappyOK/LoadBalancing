package com.sdn.loadBalancing;

public class PortData {
	private String switchId;
	private int portNumber;
	private int receiveBytes;
	private int receivePackets;
	private int transmitBytes;
	private int transmitPackets;
	
	public String getSwitchId() {
		return switchId;
	}
	public void setSwitchId(String switchId) {
		this.switchId = switchId;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	public int getReceiveBytes() {
		return receiveBytes;
	}
	public void setReceiveBytes(int receiveBytes) {
		this.receiveBytes = receiveBytes;
	}
	public int getReceivePackets() {
		return receivePackets;
	}
	public void setReceivePackets(int receivePackets) {
		this.receivePackets = receivePackets;
	}
	public int getTransmitBytes() {
		return transmitBytes;
	}
	public void setTransmitBytes(int transmitBytes) {
		this.transmitBytes = transmitBytes;
	}
	public int getTransmitPackets() {
		return transmitPackets;
	}
	public void setTransmitPackets(int transmitPackets) {
		this.transmitPackets = transmitPackets;
	}
	
}
