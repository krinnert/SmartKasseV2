package com.example.smartkasse.Act_Register;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.actionbarsherlock.view.MenuItem;
import com.example.smartkasse.R;
import com.example.smartkasse.R.id;
import com.example.smartkasse.R.layout;

public class ControlFragment extends Fragment {

	
	public static final String TAG = "ControlFragment";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setRetainInstance(true);
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.act_register_control_fragment, container,false);
		
		
		return view;
	}
	
}
