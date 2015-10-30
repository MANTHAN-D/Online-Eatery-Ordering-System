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
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.Color;


public class PlaceOrder extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.placeorder);
		
	}
	protected void onResume(){
		super.onResume();
		
		action();
	}
	
	private void action(){
	
		orderList=getIntent().getExtras().getBundle("orderList");
		
		parent=(LinearLayout) findViewById(R.id.orderScreen);
		lay=(TableLayout) findViewById(R.id.columnHeader);
		
		home = new Intent(PlaceOrder.this,Home.class);
		
		//getXml=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//pview=getXml.inflate(R.layout.remove_popup,);
		//pview=(View)getXml.inflate(R.layout.remove_popup,(ViewGroup)findViewById(R.id.popup_List));
		//pview=(View)getXml.inflate(R.layout.remove_popup,null);
		pview=getLayoutInflater().inflate(R.layout.remove_popup,null);
		popup=(LinearLayout)pview.findViewById(R.id.popup_List);
		caview=getLayoutInflater().inflate(R.layout.confirm_address,null);
		capopup=(LinearLayout)caview.findViewById(R.id.confirmAddrParent);
		button_ordernow=(Button)findViewById(R.id.button_orderNow);
		button_additem=(Button)findViewById(R.id.button_addItem);
		button_removeItem=(Button) findViewById(R.id.button_removeItem);
		button_confirmAddress=(Button)findViewById(R.id.button_confirmAddress);

		
		qitemNo=0;
		for(int m=1;m<=4;m++){
			
			int counter=1;
			while(qitemNo<=7 && counter<=5)
			{
				SelectedItemObject obj=(SelectedItemObject) orderList.get("c"+m+"_item"+counter);
				if(obj==null){
					counter++;
					continue;
					//break;
				}
				
				itemNo=3*qitemNo;
				
				srnoNameRateArray[itemNo] = new TextView(this);
				srnoNameRateArray[itemNo+1] = new TextView(this);
				qtyArray[qitemNo] = new EditText(this);
				srnoNameRateArray[itemNo+2] = new TextView(this);
				
				orderedItems[qitemNo]=new CheckBox(this);
				orderedItems[qitemNo].setLayoutParams(new LinearLayout.LayoutParams
									(LinearLayout.LayoutParams.MATCH_PARENT,
									LinearLayout.LayoutParams.WRAP_CONTENT));
		
				itemRowArray[qitemNo] = new TableRow(this);
				itemRowArray[qitemNo].setLayoutParams(new TableLayout.LayoutParams
								(TableRow.LayoutParams.MATCH_PARENT,
								TableRow.LayoutParams.MATCH_PARENT));
			
			
				srnoNameRateArray[itemNo].setText((CharSequence)(Integer.toString(qitemNo+1)));
				srnoNameRateArray[itemNo+1].setText((CharSequence)obj.getItem());
				qtyArray[qitemNo].setText((CharSequence)Integer.toString(1));
				srnoNameRateArray[itemNo+2].setText((CharSequence)obj.getPrice());
				
				orderedItems[qitemNo].setText((CharSequence)obj.getItem());
				orderedItems[qitemNo].setTextSize(17);
				orderedItems[qitemNo].setTextColor(Color.WHITE);
				
				
				attachListener(qtyArray[qitemNo],qitemNo);
			
				srnoNameRateArray[itemNo].setLayoutParams(new TableRow.LayoutParams
													(40,TableRow.LayoutParams.WRAP_CONTENT));
				srnoNameRateArray[itemNo+1].setLayoutParams(new TableRow.LayoutParams
													(165,TableRow.LayoutParams.WRAP_CONTENT));
				qtyArray[qitemNo].setLayoutParams(new TableRow.LayoutParams
													(45,TableRow.LayoutParams.WRAP_CONTENT));
				srnoNameRateArray[itemNo+2].setLayoutParams(new TableRow.LayoutParams
													(70,TableRow.LayoutParams.WRAP_CONTENT));

			
				srnoNameRateArray[itemNo].setTextSize(22);
				srnoNameRateArray[itemNo+1].setTextSize(22);
				qtyArray[qitemNo].setTextSize(22);
				srnoNameRateArray[itemNo+2].setTextSize(22);
			
				itemRowArray[qitemNo].addView(srnoNameRateArray[itemNo]);
				itemRowArray[qitemNo].addView(srnoNameRateArray[itemNo+1]);
				itemRowArray[qitemNo].addView(qtyArray[qitemNo]);
				itemRowArray[qitemNo].addView(srnoNameRateArray[itemNo+2]);
			
				lay.addView(itemRowArray[qitemNo]);
				//try{
					popup.addView(orderedItems[qitemNo]);
				//}catch(NullPointerException np){
					//Do nothing
				//}
			
				qitemNo++;
				counter++;
			}
		}
		
		button_ordernow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String reply;
				SharedPreferences pref_addr = getSharedPreferences("MYPREF", 0);
				mobile_no = pref_addr.getString("cellno", "");
				//if(address==""){
					//pieceToast.setText("Specify the Address.");
					//pieceToast.show();

				//}
				addToOrder();
				try{
					httpPost.setEntity(new UrlEncodedFormEntity(order));
				}catch(UnsupportedEncodingException e){
					//do nothing
				}
				try{
					HttpResponse response = httpClient.execute(httpPost);
					reply=MyResponse.inputStreamToString(response.getEntity().getContent()).toString();
					//pieceToast.setText(reply);
					
					Toast pieceToast = Toast.makeText(getApplicationContext(), reply, Toast.LENGTH_LONG);
					pieceToast.show();
					startActivity(home);
					
				}catch(ClientProtocolException e){
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			

			}
		});
		
		button_additem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Intent in=new Intent(PlaceOrder.this,ViewMenu.class);
				Home.viewm.putExtra("orderList",orderList);
				startActivity(Home.viewm);	
			}
		});
		
		button_removeItem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//PopupWindow pw;
				//LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				//View layout=inflater.inflate(R.layout.remove_popup,(ViewGroup)findViewById(R.id.popup));
				pw=new PopupWindow(pview,android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
						android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,true);
				pw.setOutsideTouchable(false);
				//pw.setBackgroundDrawable(new  BitmapDrawable(BitMap.createBitMap(pw.getWidth(),pw.getHeight(),BitMap.Config.ALPHA_8)));
				try{
					//Bitmap bitmap=Bitmap.createBitmap(pw.getWidth(),pw.getHeight(),Bitmap.Config.ALPHA_8);
					Bitmap bitmap=Bitmap.createBitmap(50,50,Bitmap.Config.ALPHA_8);
					bitmap.eraseColor(Color.WHITE);
					pw.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmap));
				}catch(IllegalArgumentException iae){
					//Do Nothing
				}
				pw.showAtLocation(popup,Gravity.CENTER,0,0);
				//pw.showAsDropDown(getXml.inflate(R.layout.placeorder,(ViewGroup)findViewById(R.id.orderScreen)));
				button_popup=(Button)pview.findViewById(R.id.button_popup);
				try{
					button_popup.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub				
								int i,max_no_item=0;
								for(i=1;i<=4;i++){
									int counter=1;
									while(max_no_item<=7 && counter<=4){
										SelectedItemObject curritem=(SelectedItemObject)orderList.get("c"+i+"_item"+counter);
										if(curritem==null){// || counter==4){
											counter++;
											continue;
										}
										
										CheckBox chkbox=orderedItems[max_no_item];
										if(chkbox.isChecked())
										{
											orderList.remove("c"+i+"_item"+counter);
										}
										max_no_item++;
										counter++;
									}
								}
								pw.dismiss();
								
								//Intent restart=getIntent();
								ViewMenu.placeorder.putExtra("orderList",orderList);
								startActivity(ViewMenu.placeorder);
								//startActivity(new Intent(PlaceOrder.this,PlaceOrder.this));
						}});
				}catch(NullPointerException np){
					//Do Nothing
				}
			}
		});
		
		button_confirmAddress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pwca=new PopupWindow(caview,android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
						android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,true);
				pwca.setOutsideTouchable(false);
				try{
					//Bitmap bitmap=Bitmap.createBitmap(pw.getWidth(),pw.getHeight(),Bitmap.Config.ALPHA_8);
					Bitmap bitmap=Bitmap.createBitmap(50,50,Bitmap.Config.ALPHA_8);
					bitmap.eraseColor(Color.WHITE);
					pwca.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmap));
				}catch(IllegalArgumentException iae){
					//Do Nothing
				}
				pwca.showAtLocation(capopup,Gravity.CENTER,0,0);
				button_confirmPopup=(Button)caview.findViewById(R.id.button_addr);
				addr=(EditText)caview.findViewById(R.id.tv_address);
				area=(EditText)caview.findViewById(R.id.et_area);
				pin=(EditText)caview.findViewById(R.id.et_pincode);
				
				button_confirmPopup.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						address=addr.getText().toString()+" ,"+area.getText().toString()+" ,"+pin.getText().toString();
						pwca.dismiss();
					}
				});
				
				button_loadAddr=(Button)caview.findViewById(R.id.button_load);
				
				button_loadAddr.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						SharedPreferences pref_addr = getSharedPreferences("MYPREF", 0);
						addr.setText(pref_addr.getString("mystreet", "Address Not Stored"));
						area.setText(pref_addr.getString("myarea", "-"));
						pin.setText(pref_addr.getString("mypin", "-"));
					}
				});


			}
		});
	
		
	}
	public void attachListener(final EditText qtyBox,final int itemNo){
		qtyBox.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View etbox, boolean hasFocus) {
				// TODO Auto-generated method stub
				
				if(!hasFocus){
					int new_qty=Integer.parseInt(qtyBox.getText().toString());
					int price=Integer.parseInt(srnoNameRateArray[(3*itemNo)+2].getText().toString());
					int rate=new_qty*(price/original_qty);
					
					srnoNameRateArray[(3*itemNo)+2].setText((CharSequence)Integer.toString(rate));
				}
				if(hasFocus){
					original_qty=Integer.parseInt(qtyBox.getText().toString());
				}
			}
		});
	}
	
	private void addToOrder(){
		int i=0;
		while(i<qitemNo){
			order.add(new BasicNameValuePair("item"+(i+1), srnoNameRateArray[3*i+1].getText().toString()));
			order.add(new BasicNameValuePair("qty"+(i+1), qtyArray[i].getText().toString()));
			i++;
		}
		order.add(new BasicNameValuePair("address", address));
		//order.add(new BasicNameValuePair("cellNo", mobile_no));
		
	}
	
	LinearLayout parent,popup,capopup;
	TableLayout lay;
	View pview,caview;
	 
	TableRow[] itemRowArray=new TableRow[10];
	TextView[] srnoNameRateArray=new TextView[30];
	EditText[] qtyArray=new EditText[10];
	EditText addr,area,pin;
	
	Button button_ordernow;
	Button button_additem;
	Button button_removeItem;
	Button button_popup;
	Button button_confirmAddress;
	Button button_confirmPopup,button_loadAddr;
	
	CheckBox[] orderedItems=new CheckBox[9];
	Bundle orderList;
	
	String mobile_no,address="";
	
	PopupWindow pw,pwca;

	LayoutInflater getXml;

	HttpClient httpClient = new DefaultHttpClient();
    HttpPost httpPost = new HttpPost("http://192.168.20.30:8090/Order");
    
    List<NameValuePair> order=new ArrayList<NameValuePair>(18);
	
	public static int itemNo=0;
	public static int qitemNo=0;
	static int original_qty;
	public Intent home;
}
