package com.example.online_eatery_ordering_system;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EateryRegistration extends Activity {

	 protected void onCreate(Bundle savedInstanceState) {
	        
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.eatery_registration);
	 
		 Button b_submit=(Button) findViewById(R.id.button_submit);
		 final EditText et=(EditText) findViewById(R.id.et_name);
		 final FieldWatcher fwname=new FieldWatcher(et);
	     et.addTextChangedListener(fwname);
		 
		 street = (EditText)findViewById(R.id.et_address);
		 final FieldWatcher fwstreet=new FieldWatcher(street);
	     street.addTextChangedListener(fwstreet);
		 
		 mobile = (EditText) findViewById(R.id.et_mobile);
		 final FieldWatcher fwmobile=new FieldWatcher(mobile);
	     mobile.addTextChangedListener(fwmobile);
	     mobile.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
		 
		 area = (EditText) findViewById(R.id.et_area);
		 final FieldWatcher fwarea=new FieldWatcher(area);
	     area.addTextChangedListener(fwarea);
		 
		 pincode = (EditText) findViewById(R.id.et_pincode);
		 final FieldWatcher fwpin=new FieldWatcher(et);
	     pincode.addTextChangedListener(fwpin);
		 
	     
		 //mobile.setText(getIntent().getExtras().getString("cellNo"));
		
	     b_submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				fwn=fwname.isValue();
				fwm=fwmobile.isValue();
				fwa=fwarea.isValue();
				fws=fwstreet.isValue();
				fwp=fwpin.isValue();
				
				
			/*	
				SharedPreferences pref_addr = getSharedPreferences("MYPREF", 0);
				SharedPreferences.Editor editor = pref_addr.edit();
				editor.putString("mystreet", street.getText().toString());
				editor.putString("cellno", mobile.getText().toString());
				editor.putString("myarea", area.getText().toString());
				editor.putString("mypin", pincode.getText().toString());
				editor.commit();
				
				
				String address;
				address = street.getText().toString()+" ,"+area.getText().toString()+" ,"+pincode.getText().toString();
				
				List<NameValuePair> register=new ArrayList<NameValuePair>(3);
				register.add(new BasicNameValuePair("name",et.getText().toString()));
				register.add(new BasicNameValuePair("address",address));
				register.add(new BasicNameValuePair("cellNo",mobile.getText().toString()));
				try{
					httpPost.setEntity(new UrlEncodedFormEntity(register));
				}catch(UnsupportedEncodingException e){
					//do nothing
				}
				

			
				
				try{
					HttpResponse response = httpClient.execute(httpPost);
					reply=MyResponse.inputStreamToString(response.getEntity().getContent()).toString();
					
					
					
				}catch(ClientProtocolException e){
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				if(mobile.getText().toString().matches(regmobile))
				{
					format_mob=true;
				}
				
				if(pincode.getText().toString().matches(regpincode))
				{
					format_pin=true;
				}
				
				if(fwn && fwm && fws && fwa && fwp && format_mob && format_pin)
				{
					Intent in=new Intent(EateryRegistration.this, Home.class);
					in.putExtra("userName","Welcome,"+et.getText().toString());
					startActivity(in);
				}
				else
				{
					Toast pieceToast = Toast.makeText(getApplicationContext(), "All details must be filled properly.", Toast.LENGTH_LONG);
					pieceToast.show();
				}
			}
		});
	 
	 }
	 
	 
	 
	 boolean fws=false, fwa=false, fwp=false, fwm=false, fwn=false;
	 boolean format_mob=false, format_pin=false;
	 String reply;
	 EditText street,mobile,pincode,area;
	 String regmobile="^[0-9\\-\\(\\) ]{12}$";
	 String regpincode="^[0-9]{6}$";
	 //FieldWatcher fwmobile,fwstreet,fwpincode,fwarea,fwname;
	 HttpClient httpClient = new DefaultHttpClient();
	 HttpPost httpPost = new HttpPost("http://192.168.20.30:8090/Login");
}
