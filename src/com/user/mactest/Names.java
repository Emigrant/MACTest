package com.user.mactest;

import android.app.Activity;
import com.user.calc.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Names extends Activity implements View.OnClickListener{
	
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
        final DatabaseHandler db = new DatabaseHandler(this, DatabaseHandler.TABLE_NAMES);
       
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        for (Contact cn: db.getAllContacts())
        	db.deleteContact(cn);

        db.addContact(new Contact(1, "Abdussaitova Nazgum"));
        db.addContact(new Contact(2, "Abiyev Nursultan"));
        db.addContact(new Contact(3, "Bagatov Bazarkhan"));
        db.addContact(new Contact(4, "Dildabekov Nurbek"));
        db.addContact(new Contact(5, "Dzhachiyeva Kamila"));
        db.addContact(new Contact(6, "Kadyrgaziyev Sabit"));
        db.addContact(new Contact(7, "Kazkeeva Gulfaram"));
		ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, db.getAllContacts());
		listView.setAdapter(adapter);
		//final String a = db.getAllContacts().toString();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View itemClicked, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				 Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(),
				          Toast.LENGTH_SHORT).show();
				startActivity(new Intent(Names.this, Camera.class));
					
			}
		});		
        // Reading all contacts
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
