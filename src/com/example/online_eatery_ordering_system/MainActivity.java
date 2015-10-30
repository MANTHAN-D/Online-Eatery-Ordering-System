package com.example.online_eatery_ordering_system;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;

public class MainActivity extends Activity {

	
	public static DBAdapter dbcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        home = new Intent(MainActivity.this,Home.class);
        register= new Intent(MainActivity.this,EateryRegistration.class);
        
        //final EditText et=(EditText) findViewById(R.id.textfield_userName);
        final EditText et=(EditText) findViewById(R.id.et_cellno);
        
        final FieldWatcher fw_mobile=new FieldWatcher(et);
        et.addTextChangedListener(fw_mobile);
        
        Button b_login = (Button) findViewById(R.id.button_Login);
        Button b_signup = (Button) findViewById(R.id.button_Register);
        dbcon=new DBAdapter(this);
        
        dbcon=dbcon.createDatabase();
        /*
        Bundle b=new Bundle();
		b.putString("userName", "Welcome,"+et.getText().toString());
		b.putSerializable("DBAdapter",dbcon);
        */
        
        //	    String destPath=getBaseContext().getFilesDir().getPath()+"data/"+getPackageName()+"/databases/menu_v1";
        //       String destPath="/data/data/"+getPackageName()+"/databases/menu_v1";
	    /*	    try {
	    		File f=new File(destPath);
	    		if(!f.exists()){
	    			Log.d("Manthan", "inside if");	
	    			f.createNewFile();
	    			CopyDb(getBaseContext().getAssets().open("menu_v1"),new FileOutputStream(destPath));
	    			Log.d("Manthan", "in try");
	    		}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	finally{
	    		Log.d("Anand","completed");
	    	}
	    
	   
	    db.open();
	    Cursor c=db.getAllItems();
	    Log.d("D1", "Sgrdyuigi");
	    if(c.moveToFirst()){
	    	do{
	    		Display(c);
	    	} while(c.moveToNext());
	    }
	    db.close();
	*/    
	    
	    b_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//in.putExtra("dbobj",b);
				//home.putExtra("userName","Welcome,"+et.getText().toString());
				//in.putExtra("DBAdapter",dbcon);
				/*List<NameValuePair> cellNum=new ArrayList<NameValuePair>(1);
				cellNum.add(new BasicNameValuePair("cellNo",et.getText().toString()));
				try{
					httpPost.setEntity(new UrlEncodedFormEntity(cellNum));
				}catch(UnsupportedEncodingException e){
					//do nothing
				}
				
				
				try{
					HttpResponse response = httpClient.execute(httpPost);
					String reply=MyResponse.inputStreamToString(response.getEntity().getContent()).toString();
					
					
					if(reply.equals("New Registration")){
						register.putExtra("cellNo", et.getText().toString());
						startActivity(register);
					}
					else{
						startActivity(home);
					}
					
				}catch(ClientProtocolException e){
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
				fmobile=fw_mobile.isValue();
				if(fmobile==true && (et.getText().toString().matches(regmobile)))
				{
					List<NameValuePair> cellNum=new ArrayList<NameValuePair>(1);
					cellNum.add(new BasicNameValuePair("cellNo",et.getText().toString()));
					try{
						httpPost.setEntity(new UrlEncodedFormEntity(cellNum));
					}catch(UnsupportedEncodingException e){
						//do nothing
					}
					
					
					try{
						HttpResponse response = httpClient.execute(httpPost);
						String reply=MyResponse.inputStreamToString(response.getEntity().getContent()).toString();
						
						
						if(reply.equals("New Registration")){
							register.putExtra("cellNo", et.getText().toString());
							startActivity(register);
						}
						else{
							startActivity(home);
						}
						
					}catch(ClientProtocolException e){
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					startActivity(home);
					
				}
				else{
					Toast pieceToast = Toast.makeText(getApplicationContext(), "Enter valid mobile.", Toast.LENGTH_SHORT);
					pieceToast.show();
				}
				
				/*startActivity(home);*/
			}
			
		});
	    
	    et.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
		
        b_signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(register);
			}
		});
    }
    
    
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public static Intent home;
    public static Intent register;
    
    String regmobile="^[0-9\\-\\(\\) ]{12}$";
    boolean fmobile=false;
    HttpClient httpClient = new DefaultHttpClient();
    HttpPost httpPost = new HttpPost("http://192.168.20.30:8090/Login");
}