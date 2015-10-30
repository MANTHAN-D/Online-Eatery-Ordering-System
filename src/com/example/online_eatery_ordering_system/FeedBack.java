package com.example.online_eatery_ordering_system;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedBack extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);
		
		home = new Intent(FeedBack.this,Home.class);

		btn_submit=(Button) findViewById(R.id.btn_submitFeedback);
		et_feedback=(EditText) findViewById(R.id.et_feedback);
		
		btn_submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String feedback;
				feedback=et_feedback.getText().toString();
				/*List<NameValuePair> feedback=new ArrayList<NameValuePair>(1);
				cellNum.add(new BasicNameValuePair("feedback",feedback));
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
				Toast pieceToast = Toast.makeText(getApplicationContext(), "Thank you for the feedback!", Toast.LENGTH_LONG);
				pieceToast.show();
				startActivity(home);
				
			}
		});
	}
	
	Button btn_submit;
	EditText et_feedback;
    
    HttpClient httpClient = new DefaultHttpClient();
    HttpPost httpPost = new HttpPost("http://192.168.20.30:8090/Login");
	public Intent home;

}
