package com.user.mactest.activities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import com.user.calc.R;
import com.user.mactest.utils.DatabaseHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
//import android.view.View;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Camera extends Activity implements OnClickListener {

	ImageView iv;
	Button next;
	Button quit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);
		
		iv = (ImageView) findViewById(R.id.imageView1);
		
		Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, 0);
		
		next = (Button)findViewById(R.id.next);
		quit = (Button)findViewById(R.id.quit);
		
		next.setOnClickListener(this);
		quit.setOnClickListener(this);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		long dateInMillis = System.currentTimeMillis();
		Date date = new Date(dateInMillis);
		Toast.makeText(this, date.toLocaleString(), Toast.LENGTH_LONG).show();
		
		Bitmap bm = (Bitmap) data.getExtras().get("data");
		iv.setImageBitmap(bm);
		saveImageToFileAndDatabase(bm);
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
					switch (v.getId()){
					    case R.id.quit:
					    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
					    	builder.setMessage("Are you sure?");
					    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									Intent intent = new Intent(Intent.ACTION_MAIN);
									intent.addCategory(Intent.CATEGORY_HOME);
									intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
									startActivity(intent);
								}
							});
					    	
					    	builder.setNegativeButton("No", null);
					    	AlertDialog dialog = builder.create();
					    	dialog.show();
					    	break;
					    case R.id.next:
					    	setResult(RESULT_OK);
					    	finish();
					    	break;
				    }
		
	}
	
	public void saveImageToFileAndDatabase(Bitmap image){
		int imageId = image.hashCode();
		DatabaseHandler dh = new DatabaseHandler(this, DatabaseHandler.TABLE_ATTND);
		SQLiteDatabase attnd = dh.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(DatabaseHandler.KEY_PHOTO, imageId);
		attnd.insert(DatabaseHandler.TABLE_ATTND, 	null, values);
		
		
		String imagePath = getFilesDir().getAbsolutePath();
		imagePath += "/"+imageId+".jpg";
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(imagePath);
			image.compress(CompressFormat.JPEG, 75, fos);
			Log.i("@@DB@@", "Image saved to "+imagePath);
			
		} catch (FileNotFoundException e) {
			Log.e("@ERR@", e.getMessage());
		}
		
	}
}
