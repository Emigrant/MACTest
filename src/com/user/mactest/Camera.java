package com.user.mactest;

import com.user.calc.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
//import android.view.View;
import android.view.View;
import android.widget.ImageView;

public class Camera extends Activity {

	ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);
		
		iv = (ImageView) findViewById(R.id.imageView1);
		
		Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, 0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		Bitmap bm = (Bitmap) data.getExtras().get("data");
		iv.setImageBitmap(bm);
		
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
					switch (v.getId()){
					    case R.id.quit:
					    	finish();					       
					    	break;
					    case R.id.next:
					    	//readAndViewData();
					    	break;
				    }
		
	}
}
