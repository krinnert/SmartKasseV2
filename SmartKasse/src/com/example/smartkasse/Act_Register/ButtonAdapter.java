package com.example.smartkasse.Act_Register;

import com.example.smartkasse.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ButtonAdapter extends BaseAdapter {

	Activity activity;
	View button;
	String[] attributes;
	LayoutInflater inflater = null;
	
	public ButtonAdapter(Activity activity, String[] attributes) {
		super();
		this.activity = activity;
		this.attributes = attributes;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 24;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	    View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.act_register_button, null);
 
        TextView number = (TextView)vi.findViewById(R.id.number); // title
        TextView description = (TextView)vi.findViewById(R.id.description); // artist name
        TextView price = (TextView)vi.findViewById(R.id.price); // duration
        
 
 
        // Setting all values in listview
        number.setText("2");
        description.setText("Artikel ist cool Test");
        price.setText("1.80 €");
        
        return vi;
        
    }
	
	}
