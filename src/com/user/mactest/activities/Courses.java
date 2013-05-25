package com.user.mactest.activities;

import java.util.List;

import com.user.calc.R;
import com.user.mactest.manager.SettingsActivity;
import com.user.mactest.models.Course;
import com.user.mactest.models.Element;
import com.user.mactest.models.Gruppa;
import com.user.mactest.utils.DatabaseHandler;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
//import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;


public class Courses extends ListActivity{
	
	Button b, d;
	GridView g;
	ListView listView;
	SearchView search;
	private List<Course> courses;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		
		search = (SearchView) findViewById(R.id.searchView1);
		search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onSearchRequested();
				
			}
		});
		
		
		readAndViewData();
		
		
	}
	
	private void readAndViewData() {
        DatabaseHandler db = new DatabaseHandler(this, Course.class.getName());
       
        courses = db.getAll(Course.class);
		ArrayAdapter<Course> adapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1, courses);
		setListAdapter(adapter);
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	
		Intent intent = new Intent(Courses.this, Groups.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("course", (Element)courses.get(position));
		intent.putExtras(bundle);
		startActivity(intent);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		startActivity(new Intent(this, SettingsActivity.class));
		return super.onOptionsItemSelected(item);
	}

}
