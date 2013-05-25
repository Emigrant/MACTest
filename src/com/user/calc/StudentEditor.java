package com.user.calc;

import java.util.List;

import com.user.mactest.models.Element;
import com.user.mactest.models.Gruppa;
import com.user.mactest.models.Student;
import com.user.mactest.utils.DatabaseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class StudentEditor extends Activity {
	private EditText editStudentId;
	private EditText editStudentName;
	private Button saveButton;
	private Spinner groupSpinner;
	private Button deleteButton;
	private DatabaseHandler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_editor);
	
		editStudentId = (EditText) findViewById(R.id.editStudentId);
		editStudentName = (EditText) findViewById(R.id.editStudentName);
		saveButton = (Button) findViewById(R.id.studentSubmit);
		groupSpinner = (Spinner)findViewById(R.id.groupSpinner);
		deleteButton = (Button)findViewById(R.id.studentDeleteButton);
		
		handler = new DatabaseHandler(StudentEditor.this, Student.class.getSimpleName());
		List<Gruppa> groups = handler.getAll(Gruppa.class);
		ArrayAdapter<Gruppa> adapter = new ArrayAdapter<Gruppa>(this, android.R.layout.simple_dropdown_item_1line, groups);
		groupSpinner.setAdapter(adapter);
		
		saveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String id = editStudentId.getText().toString();
				String name = editStudentName.getText().toString();
				Gruppa gruppa = (Gruppa)groupSpinner.getSelectedItem();
				Student student = new Student(Integer.parseInt(id), name, gruppa.getId());		
				if (getIntent().hasExtra("student"))
					handler.updateElement(student);
				else
					handler.addElement(student);
				finish();
			}
		});
		if (getIntent().hasExtra("student"))
			loadData();
	}

	private void loadData() {
		final Element student = (Element)getIntent().getSerializableExtra("student");
		editStudentId.setText(String.valueOf(student.getId()));
		editStudentName.setText(student.getName());
		deleteButton.setVisibility(View.VISIBLE);
		deleteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				handler.deleteElement(student);
				finish();
			}
		});
	}


}
