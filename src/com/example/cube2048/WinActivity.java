package com.example.cube2048;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class WinActivity extends Activity {
	ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_win);
		imageView =(ImageView) findViewById(R.id.win_img);
		super.onCreate(savedInstanceState);
	}

}
