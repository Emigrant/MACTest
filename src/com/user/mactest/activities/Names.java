package com.user.mactest.activities;

import java.util.List;

import android.app.Activity;
import android.app.ListActivity;

import com.user.calc.R;
import com.user.mactest.models.Student;
import com.user.mactest.utils.DatabaseHandler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Names extends ListActivity {
	
	Button b, d;
	GridView g;
	ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		readAndViewData();
		}
	
	private void readAndViewData() {
        final DatabaseHandler db = new DatabaseHandler(this, Student.class.getName());
       
		final List<Student> students = db.getAll(Student.class);
        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, students);
		setListAdapter(adapter);
		//final String a = db.getAllContacts().toString();
        // Reading all contacts
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		startActivityForResult(new Intent(Names.this, Camera.class), 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK)
			finish();
	}
	
}
