package com.example.online_eatery_ordering_system;

import org.apache.http.client.HttpClient;

public class UniversalClient {
	
	public static String URL = "";
	static HttpClient httpClient;
	
	public static HttpClient getHttpClient(){
		return httpClient;
	}
	
	public static void setHttpClient(HttpClient hc){
		httpClient=hc;
	}

}
