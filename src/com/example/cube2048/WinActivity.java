package com.example.cube2048;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class WinActivity extends Activity {
	ImageView imageView;
	public static final String WIN_IMAGE = "WIN_IMAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_win);
		Bitmap img = getIntent().getParcelableExtra(WIN_IMAGE);
		imageView = (ImageView) findViewById(R.id.win_img);
		Bitmap map = Bitmap.createBitmap(MainActivity.winMap);
		imageView.setImageBitmap(map);
		super.onCreate(savedInstanceState);
	}

}
