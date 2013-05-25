package com.user.calc;

import com.user.mactest.models.Course;
import com.user.mactest.models.Element;
import com.user.mactest.models.Gruppa;
import com.user.mactest.utils.DatabaseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CourseEditor extends Activity {
	private EditText editCourseId;
	private EditText editCourseName;
	private Button saveButton;
	private Button deleteButton;
	private DatabaseHandler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_editor);
	
		editCourseId = (EditText) findViewById(R.id.editCourseId);
		editCourseName = (EditText) findViewById(R.id.editCourseName);
		saveButton = (Button) findViewById(R.id.courseSubmit);
		deleteButton = (Button)findViewById(R.id.courseDeleteButton);
		handler = new DatabaseHandler(CourseEditor.this, Course.class.getSimpleName());
		
		saveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String id = editCourseId.getText().toString();
				String name = editCourseName.getText().toString();
				Course course = new Course(Integer.parseInt(id), name);
				
				if (getIntent().hasExtra("course"))
					handler.updateElement(course);
				else
					handler.addElement(course);
				finish();
			}
		});
		
		
		if (getIntent().hasExtra("course")){	
			setResult(RESULT_OK);
			loadData();
		}
	}

	private void loadData() {
		final Element course = (Element)getIntent().getSerializableExtra("course");
		editCourseId.setText(String.valueOf(course.getId()));
		editCourseName.setText(course.getName());
		deleteButton.setVisibility(View.VISIBLE);
		deleteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				handler.deleteElement(course);
				finish();
			}
		});
	}

}
