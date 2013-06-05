package com.example.smartkasse.Act_Register;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class ListFragment extends Fragment {

	
	public static final String TAG = "ListFragment";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setRetainInstance(true);
		// TODO Auto-generated method stub
		String[] arr = {"Artikelübersicht","Test","Test","Test","Test","Test","Test","Test","Test","Test","Test","Test","Test","Test"};
		
		View view = inflater.inflate(R.layout.act_register_list_fragment, container,false);
		

		ListView list = (ListView)view.findViewById(R.id.list);
	
		ListAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1, arr);
		
		list.setAdapter(adapter);
		
		return view;
	}
	
}
