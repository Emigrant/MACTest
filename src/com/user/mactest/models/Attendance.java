package com.user.mactest.models;

import java.util.Calendar;
import android.graphics.Bitmap;

public class Attendance {
	private int id;
	private Student student;
	private Calendar date;
	private Course course;
	private Bitmap photo;
	private boolean isAbsent;
	
	public Attendance(int id, Student student, Calendar date, Course course, Bitmap photo, boolean isAbsent){
		this.id = id;
		this.student = student;
		this.date = date;
		this.course = course;
		this.photo = photo;
		this.isAbsent = isAbsent;
	}
	
	public int getId() {
		return id;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public Calendar getDate() {
		return date;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public Bitmap getPhoto() {
		return photo;
	}
	
	public boolean isAbsent(){
		return isAbsent;
	}
}
