package com.example.cube2048;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	GridView gridView;
	private List<Integer> cubeList = new ArrayList<Integer>(16);
	private BaseAdapter adapter;
	private int[][] cubeNum = new int[][] { { -1, -1, -1, -1 }, { -1, -1, -1, -1 },
			{ -1, -1, -1, -1 }, { -1, -1, -1, -1 } };
	private Button upBtn;
	private Button leftBtn;
	private Button downBtn;
	private Button rightBtn;

	private GestureDetector gesture;

	public static Bitmap winMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gesture = new GestureDetector(this, new Listener());
		upBtn = (Button) findViewById(R.id.up_btn);
		leftBtn = (Button) findViewById(R.id.left_btn);
		downBtn = (Button) findViewById(R.id.down_btn);
		rightBtn = (Button) findViewById(R.id.right_btn);

		upBtn.setOnClickListener(this);
		leftBtn.setOnClickListener(this);
		downBtn.setOnClickListener(this);
		rightBtn.setOnClickListener(this);

		gridView = (GridView) findViewById(R.id.grid);
		adapter = new CubeAdapter(this, cubeList);
		gridView.setAdapter(adapter);
		gridView.setDrawingCacheEnabled(true);
		// gridView.setEnabled(false);
		initData();
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		gesture.onTouchEvent(event);
		return super.onTouchEvent(event);
	}

	public void initData() {
		Arithmetic.init(cubeNum);
		resetList();
	}

	private void resetList() {
		cubeList.clear();
		for (int m = 0; m < 4; m++) {
			for (int n = 0; n < 4; n++) {
				cubeList.add(cubeNum[m][n]);
			}
		}
		adapter.notifyDataSetChanged();
		if (Arithmetic.isWin(cubeNum)) {
			Toast.makeText(this, "你成功了", Toast.LENGTH_SHORT).show();
			winMap = gridView.getDrawingCache();
			Intent intent = new Intent(this, WinActivity.class);
			startActivity(intent);

		}
		if (Arithmetic.isOver(cubeNum)) {
			Toast.makeText(this, "game over", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onClick(View v) {
		if (v == upBtn) {
			boolean isOver = Arithmetic.next(cubeNum, Arithmetic.DIR_UP);
			resetList();
		}
		if (v == leftBtn) {
			boolean isOver = Arithmetic.next(cubeNum, Arithmetic.DIR_LEFT);
			resetList();
		}
		if (v == downBtn) {
			boolean isOver = Arithmetic.next(cubeNum, Arithmetic.DIR_DOWN);
			resetList();
		}
		if (v == rightBtn) {
			boolean isOver = Arithmetic.next(cubeNum, Arithmetic.DIR_RIGHT);
			resetList();

		}
	}

	private class Listener implements OnGestureListener {
		@Override
		public boolean onDown(MotionEvent e) {
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			return false;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			final int FLING_MIN_DISTANCE = 70, FLING_MIN_VELOCITY = 200;
			if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
					&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
				Arithmetic.next(cubeNum, Arithmetic.DIR_LEFT);
				resetList();
				return true;
			} else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
					&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
				Arithmetic.next(cubeNum, Arithmetic.DIR_RIGHT);
				resetList();
				return true;
			} else if (e1.getY() - e2.getY() > FLING_MIN_DISTANCE
					&& Math.abs(velocityY) > FLING_MIN_VELOCITY) {
				Arithmetic.next(cubeNum, Arithmetic.DIR_UP);
				resetList();
				return true;
			} else if (e2.getY() - e1.getY() > FLING_MIN_DISTANCE
					&& Math.abs(velocityY) > FLING_MIN_VELOCITY) {
				Arithmetic.next(cubeNum, Arithmetic.DIR_DOWN);
				resetList();
				return true;
			}
			return false;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == R.id.action_restart) {
			initData();
		}
		return super.onOptionsItemSelected(item);
	}
}
