package com.user.calc;

import java.util.List;

import com.user.mactest.models.Course;
import com.user.mactest.models.Element;
import com.user.mactest.models.Gruppa;
import com.user.mactest.utils.DatabaseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class GroupEditor extends Activity {
	private EditText editGroupId;
	private EditText editGroupName;
	private Spinner groupSpinner; 
	private Button saveButton;
	private Button deleteButton;
	private DatabaseHandler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_editor);
	
		editGroupId = (EditText) findViewById(R.id.editGroupId);
		editGroupName = (EditText) findViewById(R.id.editGroupName);
		saveButton = (Button) findViewById(R.id.groupSubmit);
		deleteButton = (Button) findViewById(R.id.groupDeleteButton);
		groupSpinner = (Spinner)findViewById(R.id.groupSpinner);
		
		handler = new DatabaseHandler(GroupEditor.this, Gruppa.class.getSimpleName());

		List<Course> courses = handler.getAll(Course.class);
		ArrayAdapter<Course> adapter = new ArrayAdapter<Course>(this, android.R.layout.simple_dropdown_item_1line, courses);
		groupSpinner.setAdapter(adapter);
		saveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String id = editGroupId.getText().toString();
				String name = editGroupName.getText().toString();
				Course course = (Course)groupSpinner.getSelectedItem();
				Gruppa gruppa = new Gruppa(Integer.parseInt(id), name, course.getId());
				
				if (getIntent().hasExtra("gruppa"))
					handler.updateElement(gruppa);
				else
					handler.addElement(gruppa);
				finish();
			}
		});
		if (getIntent().hasExtra("gruppa"))
			loadData();
	}

	private void loadData() {
		final Element gruppa = (Element)getIntent().getSerializableExtra("gruppa");
		editGroupId.setText(String.valueOf(gruppa.getId()));
		editGroupName.setText(gruppa.getName());
		deleteButton.setVisibility(View.VISIBLE);
		deleteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				handler.deleteElement(gruppa);
				finish();
			}
		});
	}

}
