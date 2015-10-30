package com.example.online_eatery_ordering_system;

import java.io.Serializable;

public class SelectedItemObject implements Serializable {

	public void addItem(String iname){
		itemName=iname;
	}
	public void addPrice(String p){
		price=p;
	}
	
	public String getItem(){
		return itemName;
	}
	public String getPrice(){
		return price;
	}
	private String itemName;
	private String price;
}
