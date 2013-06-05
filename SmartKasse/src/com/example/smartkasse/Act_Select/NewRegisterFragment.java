package com.example.smartkasse.Act_Select;


import com.example.smartkasse.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

public class NewRegisterFragment extends DialogFragment implements android.content.DialogInterface.OnClickListener{

	public NewRegisterFragment() {
	// TODO Auto-generated constructor stub
	}
	
	
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
       
		LayoutInflater inflater = (LayoutInflater) getActivity().getLayoutInflater();
		View v = inflater.inflate(R.layout.act_select_dialog_newregister, null);
		
		
        return new AlertDialog.Builder(getActivity())
        .setTitle("Neue Kasse erstellen")
        .setPositiveButton("Erstellen", null)
        .setView(v)
        .setNegativeButton("Abbrechen", null)
        .create();
}



	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		// TODO Auto-generated method stub
 	Toast.makeText(getActivity(), "Lala", Toast.LENGTH_LONG).show();
 	FragmentManager fm = getFragmentManager();
    NewRegisterFragment dialog = new NewRegisterFragment();
    dialog.setRetainInstance(true);
    dialog.show(fm, "fragment_name");
	}
}
