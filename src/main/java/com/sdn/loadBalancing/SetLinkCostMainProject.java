package com.sdn.loadBalancing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SetLinkCostMainProject {
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		threadPool.execute(new SetLinkCostInfoRunnable());
	}
}
