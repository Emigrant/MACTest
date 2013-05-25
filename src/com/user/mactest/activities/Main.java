package com.user.mactest.activities;

import com.user.calc.R;
import com.user.mactest.utils.Util;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity {

	private EditText et;
	private EditText pass;
	Button main;
	private EditText pass1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Log.i("@@",	"dsfdsf");
		//Util.writeData(this);
		//putDataToDB();
		et = (EditText) findViewById(R.id.editText1);
		pass = (EditText) findViewById(R.id.password);
		
		final SharedPreferences settings = getSharedPreferences("MYPREFS", 0);
		et.setText(settings.getString("pass", ""));
		pass.setText(settings.getString("pass", ""));
	
		pass1 = (EditText) findViewById(R.id.password);
		main = (Button) findViewById(R.id.mainBut);
		main.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v)
			{
				switch (v.getId()) 
				{
				    case R.id.mainBut:
				        if (pass.getText().toString().equals(settings.getString("pass", ""))){
				        	Intent intent = new Intent(Main.this, Courses.class);
				        	startActivity(intent); //}
				        }
				        else et.setText(pass.getText());
				        break;
			    }
			}
		});
	}
	
	private void putDataToDB() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		SharedPreferences settings = getSharedPreferences("MYPREFS", 0);
		SharedPreferences.Editor editor = settings.edit();
		
		editor.putString("tvalue", et.getText().toString());
		editor.clear();
		editor.putString("pass", pass.getText().toString());
		editor.commit();
	}


}
