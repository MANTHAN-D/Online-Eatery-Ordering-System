package com.example.online_eatery_ordering_system;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
//import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.CheckedTextView;

public class ViewMenu extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewmenu);
        
		cat1 = new Intent(ViewMenu.this,Category1.class);
        cat2 = new Intent(ViewMenu.this,Category2.class);
        cat3 = new Intent(ViewMenu.this,Category3.class);
        cat4 =new Intent(ViewMenu.this,Category4.class);
        placeorder = new Intent(ViewMenu.this,PlaceOrder.class);

        //action();
	}
	
	public void onBackPressed(){
		//Intent in=new Intent(ViewMenu.this,Home.class);
		//in.putExtra("userName","Welcome,"+et.getText().toString());
		if(orderList!=null)
			MainActivity.home.putExtra("orderList",orderList);
		startActivity(MainActivity.home);
	}
	
	protected void onResume(){
		
		super.onResume();
		try{
        	orderList=getIntent().getExtras().getBundle("orderList");
        }catch(NullPointerException np){
        	//Do nothing
        }
		action();
	}

	private void action(){

        ct1=(CheckedTextView) findViewById(R.id.category1);
        ct2=(CheckedTextView) findViewById(R.id.category2);
        ct3=(CheckedTextView) findViewById(R.id.category3);
        ct4=(CheckedTextView) findViewById(R.id.category4);
        
        ct1.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				if(orderList!=null)
					cat1.putExtra("orderList", orderList);
				startActivity(cat1);
				return true;
			}
		});
        
        ct2.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				if(orderList!=null)
					cat2.putExtra("orderList", orderList);
				startActivity(cat2);
				return true;
			}
		});

        ct3.setOnTouchListener(new OnTouchListener() {
	
        	@Override
        	public boolean onTouch(View arg0, MotionEvent arg1) {
        		// TODO Auto-generated method stub
				if(orderList!=null)
					cat3.putExtra("orderList", orderList);
        		startActivity(cat3);
        		return true;
        	}
        });

        ct4.setOnTouchListener(new OnTouchListener() {
	
        	@Override
        	public boolean onTouch(View arg0, MotionEvent arg1) {
        		// TODO Auto-generated method stub
				if(orderList!=null)
					cat4.putExtra("orderList", orderList);
         		startActivity(cat4);
        		return true;
        	}
        });
		
	}
	
	Bundle orderList;
	
	CheckedTextView ct1;
    CheckedTextView ct2;
    CheckedTextView ct3;
    CheckedTextView ct4;
    
	public static Intent cat1;
	public static Intent cat2;
	public static Intent cat3;
	public static Intent cat4;

	public static Intent placeorder;
}
