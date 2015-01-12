package com.csgit.cao.utils;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DatePickerFragment extends DialogFragment {

	OnDateSetListener ondateSet;
	private int anio, mes, dia;

	public DatePickerFragment() {
	}

	public void setCallBack(OnDateSetListener ondate) {
		ondateSet = ondate;
	}

	@Override
	public void setArguments(Bundle args) {
		super.setArguments(args);
		anio = args.getInt("year");
		mes = args.getInt("month");
		dia = args.getInt("day");
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new DatePickerDialog(getActivity(), ondateSet, anio, mes, dia);
	}
} 