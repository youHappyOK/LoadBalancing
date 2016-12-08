package com.sdn.loadBalancing;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GetLinkInfoMainProject {
	
	private static ArrayList<LinkInfo> al;
	
	public static void main(String[] args) {  
//		ExecutorService threadPool = Executors.newSingleThreadExecutor();
//		GetLinkInfoCallable task = new GetLinkInfoCallable();
//		Future<ArrayList<LinkInfo>> future = threadPool.submit(task);
//		try {
//			al = future.get();
//			System.out.println(al);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		threadPool.execute(new GetLinkInfoRunable());
		
	}
}
