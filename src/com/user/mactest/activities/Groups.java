package com.user.mactest.activities;

import java.util.List;

import com.user.calc.R;
import com.user.mactest.models.Course;
import com.user.mactest.models.Element;
import com.user.mactest.models.Gruppa;
import com.user.mactest.utils.DatabaseHandler;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

public class Groups extends ListActivity {
	
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
        DatabaseHandler db = new DatabaseHandler(this, Gruppa.class.getName());
       
		Element course = (Element)getIntent().getExtras().getSerializable("course");
		final List<Gruppa> groups = db.getGroupsByCourse(course.getId());
        ArrayAdapter<Gruppa> adapter = new ArrayAdapter<Gruppa>(this, android.R.layout.simple_list_item_1, groups);
		setListAdapter(adapter);
		//final String a = db.getAllContacts().toString();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(Groups.this, Names.class);
		startActivity(intent);				
	}

}
