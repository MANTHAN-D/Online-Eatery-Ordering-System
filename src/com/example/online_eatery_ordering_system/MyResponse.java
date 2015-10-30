package com.example.online_eatery_ordering_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyResponse {
	
	public static StringBuilder inputStreamToString(InputStream is){
		String line="";
		StringBuilder total = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		
		try{
			while((line=rd.readLine())!=null){
				total.append(line);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return total;
	}

}
