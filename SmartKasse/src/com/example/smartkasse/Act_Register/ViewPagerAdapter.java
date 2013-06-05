package com.example.smartkasse.Act_Register;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;

import com.example.smartkasse.R;
import com.example.smartkasse.R.id;


public class ViewPagerAdapter extends PagerAdapter {

	final Context con;
	public ViewPagerAdapter(Context c) {
		con = c;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == ((View) arg1);
	}
	
	@Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }
	
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		String name ="";
		
		switch (position) {
		case 0:
			name = "Essen";
			break;
		case 1:
			name = "Trinken";
			break;
		case 2:
			name = "Nachtisch";
			break;
		case 3:
			name = "Sonstiges";
			break;
		case 4:
			name = "Extras";
			break;
		default:
			name = "undefined";
			break;
		}
		return name;
		}
	
	public Object instantiateItem(View collection, int position) {
        LayoutInflater inflater = (LayoutInflater) collection.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        View view = inflater.inflate(R.layout.act_register_buttongrid, null);
        
        GridView grid = (GridView)view.findViewById(R.id.buttonframe);
        
        ButtonAdapter adapter = new ButtonAdapter((Activity) con,null);
		grid.setAdapter(adapter);
        
        ((ViewPager) collection).addView(view, 0);
        return view;
    }

}
