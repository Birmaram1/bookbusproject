package com.bus;

import java.util.ArrayList;

;

public class Arraylist {

	static   ArrayList <String>list = new ArrayList<String>();
	
	  
	public  Arraylist(){
		System.out.println("arraylist");
	}
	  
	public Arraylist(String name,String gander,String age) {
		
		list.add(name);
		list.add(gander);
		list.add( age);
		System.out.println("data added in list");
	}
	
	
	public  String []   show() {
		String arr[]= new String[list.size()];
		System.out.println("array length "+arr.length);
		for(int i=0;i<list.size();i++) {
			arr[i]=list.get(i);
			
			System.out.println(arr[1]);
		}
		return arr;
		 
	
	}
void remove() {
		list.clear();
	}
	
}
