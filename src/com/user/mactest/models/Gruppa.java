package com.user.mactest.models;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Gruppa extends Element implements Serializable{
	private int courseName;
	public Gruppa(int id, String groupName, int courseName){
		super(id, groupName);
		this.courseName = courseName;
	}
	
	
	public int getCourseName() {
		return courseName;
	}
	
}
