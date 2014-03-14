package com.example.cube2048;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CubeAdapter extends BaseAdapter {
	private List<Integer> mCubeList;
	private Context mContext;

	CubeAdapter(Context context, List<Integer> cubeList) {
		mContext = context;
		mCubeList = cubeList;
	}

	@Override
	public int getCount() {
		return 16;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_cube, parent, false);
			holder.layout = convertView;
			holder.txt = (TextView) convertView.findViewById(R.id.cube_txt);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		int num = mCubeList.get(position);
		String string;
		if (num > 0) {
			string = String.valueOf(num);
			String resString = "num" + string;
			int id = mContext.getResources().getIdentifier(resString, "color", mContext.getPackageName());
			holder.txt.setBackgroundResource(id);
			if(num==2||num==4){
				holder.txt.setTextColor(Color.rgb(119, 119, 119));
			}else{
				holder.txt.setTextColor(Color.rgb(256, 256, 256));
			}
		} else {
			string = "";
			holder.txt.setBackgroundResource(android.R.color.darker_gray);
		}
		holder.txt.setText(string);
		return convertView;
	}

	class ViewHolder {
		View layout;
		TextView txt;
	}
}
