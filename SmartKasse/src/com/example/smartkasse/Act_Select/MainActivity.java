package com.example.smartkasse.Act_Select;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.smartkasse.R;


public class MainActivity extends SherlockFragmentActivity {
	

	RegisterListFragment registerListFragment;
HelpFragment helpFragment;
	
	boolean helpVisible = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		 
		//Grund-Layout für die Activity festlegen
		setContentView(R.layout.act_select_main);
		
		addFragments();
		
		supportInvalidateOptionsMenu();
		setLargeInLandscape();
		//ListView, die mit den bestehenden Kassen gefüllt werden soll	
		//ListView register_lst = (ListView)this.findViewById(R.id.register_list);
		
		//Adapter der den ListView mit Views füllt an die ListView angängen
		//adapter = new RegisterListAdapter(this, data);
		//register_lst.setAdapter(adapter);
		/*
		//Was passiert wenn ein Listenelement gedrückt wird
		register_lst.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				
				//Ruft zweite Activity auf: Keine Ahnung ob die geschlossen wird oder nur wartet
				Intent i = new Intent(Act_register_select.this, com.example.smartkasse.Act_Register.MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				
			}
		});
		*/
	}
	
	//Was passiert wenn der Menüknopf gedrückt wird

	// Inflate the menu; this adds items to the action bar if it is present.
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		// Zeigt nur die die nicht in Actionbar sind, kann aber eingestellt werden im XML-File
		
			if(helpVisible){
				getSupportMenuInflater().inflate(R.menu.act_select_menu_help_visible, menu);
				
			}
			else {
				getSupportMenuInflater().inflate(R.menu.act_select_menu_registerlist_visible, menu);
			}
			
		return true;
	}
	
	//Was passiert wenn ein Menüpunkt ausgewählt wird
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    //Das wenn der Knopf das Plus war
	    case R.id.menu_help:
	    	changeFragment();
	    	//this.supportInvalidateOptionsMenu();
	    	break;
	    case android.R.id.home:
	    	changeFragment();
	    	//this.supportInvalidateOptionsMenu();
	    	break;
	    case R.id.menu_add_register:
	    	FragmentManager fm = getSupportFragmentManager();
        NewRegisterFragment dialog = new NewRegisterFragment();
        dialog.setRetainInstance(true);
        dialog.show(fm, "fragment_name");
        break;
	    	//Listenelement hinzufügen und dem Adapter sagen es hat sich was geändert, mach neu
	        

	    	
	    }
	    return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if(helpVisible=true){
				//changeFragment();
				helpVisible = false;
				getSupportActionBar().setDisplayHomeAsUpEnabled(false);
				supportInvalidateOptionsMenu();
				
			}
			break;
			
			
			
		}

		return super.onKeyDown(keyCode, event);
	}
	
	private void addFragments(){

		//Zuerst versuchen Fragmente aus dem Layout zu laden
		registerListFragment = (RegisterListFragment)getSupportFragmentManager().findFragmentById(R.id.registerListFragment);
		helpFragment = (HelpFragment)getSupportFragmentManager().findFragmentById(R.id.HelpFragment);
		
		//Wenn die Fragmente nicht im Layout sind, füge sie dynamisch hinzu
		if(registerListFragment==null&&helpFragment==null){
			
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			
		registerListFragment = new RegisterListFragment();
		helpFragment = new HelpFragment();
		
		ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in,android.R.anim.fade_out );
		
		ft.add(R.id.Fragmentframe, registerListFragment);
		ft.add(R.id.Fragmentframe, helpFragment);
		ft.show(registerListFragment);
		ft.hide(helpFragment);
		ft.commit();
		
		//OnclickListener für Fragmentwechseln
		
		
		}
	
	}
	
private void changeFragment(){
		
		//Fragmente austauschen
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		if(registerListFragment.isVisible()){
		ft.hide(registerListFragment);
		ft.show(helpFragment);
		
		helpVisible = true;
		supportInvalidateOptionsMenu();
		
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		if(getSupportFragmentManager().findFragmentByTag("RegisterListFragment")==null){
			ft.addToBackStack("RegisterListFragment");
			}
		}
		else{
		ft.hide(helpFragment);
		
		helpVisible = false;
		supportInvalidateOptionsMenu();
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		
		fm.popBackStack();
		}
		
		ft.commit();
	}

private void  setLargeInLandscape(){
	if ((getResources().getConfiguration().screenLayout&Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {     
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		findViewById(R.id.menu_help).setVisibility(View.GONE);
	}
	if ((getResources().getConfiguration().screenLayout&Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {     
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		findViewById(R.id.menu_help).setVisibility(View.GONE);
	}
	
}
}
