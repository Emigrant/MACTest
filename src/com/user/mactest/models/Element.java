package com.user.mactest.models;

import java.io.Serializable;

public class Element implements Serializable{
	private int id;
	private String name;
	
	public Element(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
