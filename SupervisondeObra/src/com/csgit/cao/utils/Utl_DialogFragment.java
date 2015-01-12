package com.csgit.cao.utils;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.csgit.cao.R;

public class Utl_DialogFragment extends DialogFragment implements OnEditorActionListener {

	private EditText mEditText;
	private String titulo;
	private int tipo;
	
	public Utl_DialogFragment(int tipo){
		this.tipo = tipo;
	}
	
	public interface DialogFragmentListener {
        void onFinishEditDialog(String inputText);
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.lyt_adp_dialog_fragment, container);
        mEditText = (EditText) view.findViewById(R.id.edt_cantidad_maquinaria);
        getDialog().setTitle(getTitulo());
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mEditText.setOnEditorActionListener(this);
        
        if(tipo==1){
        	mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }else{
        	mEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }

        return view;
	}
	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		// TODO Auto-generated method stub
		if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
			DialogFragmentListener activity = (DialogFragmentListener) getActivity();
            activity.onFinishEditDialog(mEditText.getText().toString());
            mEditText.setText("");
            this.dismiss();
            return true;
        }
        return false;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}	

}
