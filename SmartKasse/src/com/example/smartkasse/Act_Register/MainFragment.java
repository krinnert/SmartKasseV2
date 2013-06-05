package com.example.smartkasse.Act_Register;

import com.example.smartkasse.R;
import com.example.smartkasse.R.id;
import com.example.smartkasse.R.layout;
import com.viewpagerindicator.TitlePageIndicator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class MainFragment extends Fragment{
	
	public static final String TAG = "MainFragment";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setRetainInstance(true);
		
		View view = inflater.inflate(R.layout.act_register_mainfragment, container,false);
		
		ViewPager vp = (ViewPager) view.findViewById(R.id.pager);
		ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity());
		
		vp.setAdapter(adapter);
		

		//Bind the title indicator to the adapter
		TitlePageIndicator titleIndicator = (TitlePageIndicator)view.findViewById(R.id.titles);
		titleIndicator.setViewPager(vp);
		
		
		return view;
	}
}
