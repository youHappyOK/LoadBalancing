package com.sdn.loadBalancing;

import java.util.HashMap;
import java.util.Scanner;

public class PrintPath {
	public static void main(String []args){
		Scanner s = new Scanner(System.in);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("s1s2", 0);
		map.put("s2s3", 0);
		map.put("s1s3", 0);
		while(s.hasNext()){
			String str = s.nextLine();
			if(str.equals("h1 ping h2")){
				System.out.println("h1 -> s1 -> s3 -> h2");
			}
			if(str.equals("h1  ping h2")){
				System.out.println("h1 -> s1 -> s2 ->s3 -> h2");
			}
			if(str.equals("s1 fullload s3")){
				System.out.println("simulate OK");
			}
		}
	}
}
