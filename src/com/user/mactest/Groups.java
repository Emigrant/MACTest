package com.user.mactest;

import com.user.calc.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class Groups extends Activity implements View.OnClickListener{
	
	Button b, d;
	GridView g;
	ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		listView = (ListView) findViewById(R.id.listView);
		readAndViewData();
		}
	
	private void readAndViewData() {
        DatabaseHandler db = new DatabaseHandler(this, DatabaseHandler.TABLE_GROUPS);
       
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        for (Contact cn: db.getAllContacts())
        	db.deleteContact(cn);

        db.addContact(new Contact(10, "A-04"));
        db.addContact(new Contact(11, "B-04"));
        db.addContact(new Contact(12, "D-04"));
        db.addContact(new Contact(13, "E-04"));
        db.addContact(new Contact(14, "F-04"));
        db.addContact(new Contact(15, "A-03"));
        db.addContact(new Contact(16, "B-03"));
		ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, db.getAllContacts());
		listView.setAdapter(adapter);
		//final String a = db.getAllContacts().toString();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
					long id) {
				// TODO Auto-generated method stub
				 Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(),
				          Toast.LENGTH_SHORT).show();
				startActivity(new Intent(Groups.this, Names.class));				
			}
		});		
        // Reading all contacts
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
