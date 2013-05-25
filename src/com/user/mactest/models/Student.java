package com.user.mactest.models;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Student extends Element implements Serializable{
	private int groupId;
	
	public Student(int id, String fullName, int groupId){
		super(id, fullName);
		this.groupId = groupId;
	}
		
	public int getGroupId() {
		return groupId;
	}
}
