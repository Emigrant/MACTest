package com.user.mactest.models;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Course extends Element implements Serializable{
	
	public Course(int id, String className) {
		super(id, className);
	}

}
