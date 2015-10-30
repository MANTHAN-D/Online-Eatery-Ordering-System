package com.example.online_eatery_ordering_system;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class FieldWatcher implements TextWatcher {
	EditText ref;
	boolean value;
	
	public FieldWatcher(EditText ref){
		super();
		this.ref=ref;
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub

		if(ref.getText().toString().length()==0)
			value = false;
		else
			value = true;		
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	/*	if(ref.getText().toString().length()==0)
			value = false;
		else
			value = true;*/		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}

	public boolean isValue() {
		return value;
	}	

}
