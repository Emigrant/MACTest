package com.user.mactest;

import com.user.calc.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
//import android.view.Menu;
import android.widget.Toast;


public class Second extends Activity implements View.OnClickListener{
	
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
        DatabaseHandler db = new DatabaseHandler(this, DatabaseHandler.TABLE_CONTACTS);
       
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        for (Contact cn: db.getAllContacts())
        	db.deleteContact(cn);
        
        db.addContact(new Contact(1, "CSI"));
        db.addContact(new Contact(2, "Software Engineering"));
        db.addContact(new Contact(3, "Java"));
        db.addContact(new Contact(4, "Diploma"));
		ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, db.getAllContacts());
		listView.setAdapter(adapter);
		final String a = db.getAllContacts().toString();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(Second.this, a, Toast.LENGTH_LONG).show();
				startActivity(new Intent(Second.this, Groups.class));
			}
		});		
        // Reading all contacts
	}
	
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
					switch (v.getId()){
					    case R.id.quit:
					    	finish();					       
					    	break;
					    case R.id.next:
					    	readAndViewData();
					    	break;
				    }
		
	}
	
}
