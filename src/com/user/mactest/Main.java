package com.user.mactest;

import com.user.calc.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity {

	private EditText et;
	private EditText pass;
	Button main;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		putDataToDB();
		et = (EditText) findViewById(R.id.editText1);
		pass = (EditText) findViewById(R.id.password);
		
		SharedPreferences settings = getSharedPreferences("MYPREFS", 0);
		et.setText(settings.getString("tvalue", ""));
		pass.setText(settings.getString("pass", ""));
	
		main = (Button) findViewById(R.id.mainBut);
		main.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v)
			{
				switch (v.getId()) 
				{
				    case R.id.mainBut:
				        Intent intent = new Intent(Main.this, Second.class);
				        startActivity(intent);
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
		editor.putString("pass", pass.getText().toString());
		editor.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
