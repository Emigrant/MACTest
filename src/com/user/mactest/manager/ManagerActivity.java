package com.user.mactest.manager;

import java.util.List;

import com.user.calc.CourseEditor;
import com.user.calc.GroupEditor;
import com.user.calc.R;
import com.user.calc.R.layout;
import com.user.calc.R.menu;
import com.user.calc.StudentEditor;
import com.user.mactest.activities.Courses;
import com.user.mactest.models.Course;
import com.user.mactest.models.Element;
import com.user.mactest.models.Gruppa;
import com.user.mactest.models.Student;
import com.user.mactest.utils.DatabaseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ManagerActivity extends ListActivity implements OnClickListener{
	private Button addView;
	private int order;
	private ArrayAdapter adapter;
	private List<Course> courses;
	private List<Gruppa> gruppas;
	private List<Student> students;
	private DatabaseHandler db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager);
		order = getIntent().getIntExtra("order", 0);
		
		addView = (Button)findViewById(R.id.managerAddButton);
		addView.setOnClickListener(this);
		db = new DatabaseHandler(this, Student.class.getName());

		
		switch(order){
			case 0:
				courses = db.getAll(Course.class);
				adapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1, courses);
				setListAdapter(adapter);				
				break;
			case 1:
				gruppas = db.getAll(Gruppa.class);
		        adapter = new ArrayAdapter<Gruppa>(this, android.R.layout.simple_list_item_1, gruppas);
				setListAdapter(adapter);
				break;
			case 2:
				students = db.getAll(Student.class);
		        adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, students);
				setListAdapter(adapter);
				break;
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent;
		switch(order){
		case 0:
			Course course = (Course)courses.toArray()[position];
			intent = new Intent(this, CourseEditor.class);
			intent.putExtra("course", course);
			startActivityForResult(intent, 0);
			break;
		case 1:
			Gruppa gruppa = (Gruppa)gruppas.toArray()[position];
			intent = new Intent(this, GroupEditor.class);
			intent.putExtra("gruppa", gruppa);
			startActivityForResult(intent, 1);
			break;
		case 2:
			Student student = (Student)students.toArray()[position];
			intent = new Intent(this, StudentEditor.class);
			intent.putExtra("student", student);
			startActivityForResult(intent, 2);
			break;
	}

	}
	
	@Override
	public void onClick(View v) {
		switch(order){
		case 0:
			startActivityForResult(new Intent(ManagerActivity.this, CourseEditor.class), 0);
			break;
		case 1:
			startActivityForResult(new Intent(ManagerActivity.this, GroupEditor.class), 1);
			break;
		case 2:
			startActivityForResult(new Intent(ManagerActivity.this, StudentEditor.class), 2);
			break;
		}	
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			switch(order){
				case 0:
					courses.clear();
					courses = db.getAll(Course.class);
					adapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1, courses);
					break;
				case 1:
					gruppas.clear();
					gruppas = db.getAll(Gruppa.class);
					adapter = new ArrayAdapter<Gruppa>(this, android.R.layout.simple_list_item_1, gruppas);
					break;
				case 2:
					students.clear();
					students = db.getAll(Student.class);
					adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, students);
					break;				
			}
			setListAdapter(adapter);
			
		

	}
}
