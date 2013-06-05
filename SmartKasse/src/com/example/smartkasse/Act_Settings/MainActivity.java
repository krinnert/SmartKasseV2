package com.example.smartkasse.Act_Settings;

import net.saik0.android.unifiedpreference.UnifiedPreferenceFragment;
import net.saik0.android.unifiedpreference.UnifiedSherlockPreferenceActivity;
import android.content.Context;
import android.os.Bundle;

import com.example.smartkasse.R;

public class MainActivity extends UnifiedSherlockPreferenceActivity {

	
	@Override public void onCreate(Bundle savedInstanceState) {
		// Set header resource MUST BE CALLED BEFORE super.onCreate
		setHeaderRes(R.xml.pref_headers);
		// Set desired preference file and mode (optional)
		setSharedPreferencesName("unified_preference_demo");
		setSharedPreferencesMode(Context.MODE_PRIVATE);
		
		

		
		super.onCreate(savedInstanceState);
		}

		public static class GeneralPreferenceFragment extends UnifiedPreferenceFragment {
		
		
		}

		public static class NotificationPreferenceFragment extends UnifiedPreferenceFragment {}

		public static class DataSyncPreferenceFragment extends UnifiedPreferenceFragment {}
	
}
