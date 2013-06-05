package com.example.smartkasse.Act_Select;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.actionbarsherlock.view.MenuItem;
import com.example.smartkasse.R;
import com.example.smartkasse.R.id;
import com.example.smartkasse.R.layout;

public class RegisterListFragment extends Fragment {

	
	public static final String TAG = "RegisterListFragment";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setRetainInstance(true);
		// TODO Auto-generated method stub
		ArrayList<String> data = new ArrayList<String>();
		data.add("Kasse 1");
		
		View view = inflater.inflate(R.layout.act_select_list_fragment, container,false);
		

		ListView list = (ListView)view.findViewById(R.id.register_list);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(getActivity(), com.example.smartkasse.Act_Register.MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				getActivity().finish();
			}
		});
		RegisterListAdapter adapter = new RegisterListAdapter(getActivity(), data);
		list.setAdapter(adapter);
		
		return view;
	}
	
}
