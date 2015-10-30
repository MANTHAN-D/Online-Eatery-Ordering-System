package com.example.online_eatery_ordering_system;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


public class Category1 extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.category1);
        //action();
	}
	
	protected void onResume(){
		super.onResume();
		
		try{
			orderedItems=getIntent().getExtras().getBundle("orderList");
		}catch(NullPointerException np){
			//Do nothing
		}
		
		if(orderedItems==null){
			orderedItems=new Bundle();
		}
		
		action();
	}

	public void onBackPressed(){
		//Intent in=new Intent(Category1.this, ViewMenu.class);
		if(orderedItems!=null)
			Home.viewm.putExtra("orderList",orderedItems);
		startActivity(Home.viewm);
	}
	
	private void action(){

		button_ordernow=(Button) findViewById(R.id.button_ordernow);
		
		item[0]=(CheckBox) findViewById(R.id.checkBox_item1);
		item[1]=(CheckBox) findViewById(R.id.checkBox_item2);
		item[2]=(CheckBox) findViewById(R.id.checkBox_item3);
		item[3]=(CheckBox) findViewById(R.id.checkBox_item4);
		
		p[0]=(TextView) findViewById(R.id.textView_price1);
		p[1]=(TextView) findViewById(R.id.textView_price2);
		p[2]=(TextView) findViewById(R.id.textView_price3);
		p[3]=(TextView) findViewById(R.id.textView_price4);
		
	    db.open();
	    Cursor c=db.getAllItems("specialities");
	    i=0;
	    if(c.moveToFirst()){
	    	do{
	    		item[i].setText((CharSequence)c.getString(1));
	    		p[i].setText((CharSequence)c.getString(2));
	    		i++;
	    	} while(c.moveToNext());
	    }
	    db.close();

	    button_ordernow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//orderedItems=checkAndAddItems();
				checkAndAddItems();
				//Intent in=new Intent(Category1.this, PlaceOrder.class);
				ViewMenu.placeorder.putExtra("orderList",orderedItems);
				startActivity(ViewMenu.placeorder);
			}
		});
		
	}
	public void checkAndAddItems(){
		
		int counter=0;
		for(int i=0;i<item.length;i++){
			if(item[i].isChecked()){
				counter++;
				while(true){
					SelectedItemObject obj1=(SelectedItemObject) orderedItems.get("c1_item"+counter);
					if(obj1==null)
						break;
					counter++;
				}
				if(counter==5)
					break;
				SelectedItemObject obj=new SelectedItemObject();
				obj.addItem((String) item[i].getText());
				obj.addPrice((String) p[i].getText());
				orderedItems.putSerializable("c1_item"+counter,obj);
			}
		}
		
		//return orderedItems;
	}
	
	
	DBAdapter db=MainActivity.dbcon;
	CheckBox[] item=new CheckBox[4];
	TextView[] p=new TextView[4];
	
	Bundle orderedItems;
	Button button_ordernow;
	
	int i;
}
