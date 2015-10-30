package com.example.online_eatery_ordering_system;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		viewm=new Intent(Home.this,ViewMenu.class);
		//action();
		
	}
	
	protected void onResume(){
		super.onResume();
		
		try{
			orderList=getIntent().getExtras().getBundle("orderList");
		}catch(NullPointerException np){
			//Do Nothing
		}
		action();
	}
		
	public void onBackPressed(){
		//Intent in=new Intent(Home.this, MainActivity.class);
		//in.putExtra("userName","Welcome,"+et.getText().toString());
		startActivity(MainActivity.home);
	}
	
	private void action(){

		//TextView un= (TextView) findViewById(R.id.home_username);
		//un.setText((CharSequence) getIntent().getExtras().get("userName"));

		ImageButton viewmenu_button=(ImageButton)findViewById(R.id.optionbutton);
		
		if(orderList!=null)
			viewm.putExtra("orderList", orderList);

		
		viewmenu_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Intent in=new Intent(Home.this, ViewMenu.class);
				//in.putExtra("userName","Welcome,"+et.getText().toString());
				startActivity(viewm);	
			}
		});
	
		ImageButton category1_button=(ImageButton)findViewById(R.id.item1button);
		
		category1_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Intent in=new Intent(Home.this, ViewMenu.class);
				//in.putExtra("userName","Welcome,"+et.getText().toString());
				startActivity(viewm);	
			}
		});
		
		ImageButton category2_button=(ImageButton)findViewById(R.id.item2button);
		
		category2_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Intent in=new Intent(Home.this, ViewMenu.class);
				//in.putExtra("userName","Welcome,"+et.getText().toString());
				startActivity(viewm);	
			}
		});
		
		ImageButton category3_button=(ImageButton)findViewById(R.id.item3button);
		
		category3_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//cat3 =new Intent(Home.this, ViewMenu.class);
				//in.putExtra("userName","Welcome,"+et.getText().toString());
				startActivity(viewm);	
			}
		});
		
		ImageButton category4_button=(ImageButton)findViewById(R.id.item4button);
		
		category4_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//cat4 =new Intent(Home.this, ViewMenu.class);			
				//in.putExtra("userName","Welcome,"+et.getText().toString());
				startActivity(viewm);	
			}
		});
		
		ImageButton feedback_button = (ImageButton) findViewById(R.id.button_feedback);
		
		feedback_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(Home.this,FeedBack.class);
				startActivity(in);
			}
		});

		
	}
	
	
	
	Bundle orderList;
	
	public static Intent viewm;

}
