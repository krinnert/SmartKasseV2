package com.example.smartkasse.Act_Select;

import java.util.List;

import com.example.smartkasse.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterListAdapter extends BaseAdapter {
	
	 private static LayoutInflater inflater=null;
	
	private Context mContext;
	private List<String> data;
	
	public RegisterListAdapter(Context con, List<String> values) {
		super();
		this.mContext = con;
		this.data = values;
		
		inflater = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = null;
		
		if(convertView==null){
			
			view = (View)inflater.inflate(R.layout.act_register_select_listview, null);
			
			TextView title = (TextView)view.findViewById(R.id.title);
	        TextView artist = (TextView)view.findViewById(R.id.artist);
	        TextView duration = (TextView)view.findViewById(R.id.duration);
	        ImageView thumb_image=(ImageView)view.findViewById(R.id.list_image);
	        
	        title.setText(data.get(position));
	        artist.setText(data.get(position));
	        duration.setText(data.get(position));
	        thumb_image.setImageResource(R.drawable.ic_launcher);
	        
	        return view;
		}
		else{
		
			TextView title = (TextView)convertView.findViewById(R.id.title);
	        TextView artist = (TextView)convertView.findViewById(R.id.artist);
	        TextView duration = (TextView)convertView.findViewById(R.id.duration);
	        ImageView thumb_image=(ImageView)convertView.findViewById(R.id.list_image);
	        
	        title.setText(data.get(position));
	        artist.setText(data.get(position));
	        duration.setText(data.get(position));
	        thumb_image.setImageResource(R.drawable.ic_launcher);
			
	        return convertView;
		}
		
		
	}

}
