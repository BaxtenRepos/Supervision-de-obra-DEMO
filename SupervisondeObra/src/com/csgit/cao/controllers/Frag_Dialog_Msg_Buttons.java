package com.csgit.cao.controllers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.csgit.cao.R;

public class Frag_Dialog_Msg_Buttons extends DialogFragment{
	
	public static Frag_Dialog_Msg_Buttons newInstance(int title, int message){
		Frag_Dialog_Msg_Buttons frag = new Frag_Dialog_Msg_Buttons();
		Bundle args = new Bundle();
		args.putInt("title", title);
		args.putInt("message", message);
		frag.setArguments(args);
		return frag;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		int title = getArguments().getInt("title");
		int message = getArguments().getInt("message");
		return new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_action_warning)
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton(R.string.str_dialog_fragment_aceptar,
						new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						((Act_Documentos)getActivity()).doPositiveClick();
						dismiss();
					}
				})
				.setNegativeButton(R.string.str_dialog_fragment_cancelar,
						new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						((Act_Documentos)getActivity()).doNegativeClick();
						dismiss();
					}
				}).create();
	}
}
