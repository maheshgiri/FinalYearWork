package com.example.projfinal;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
public class LoginOne extends Activity{

	String loginServerUri = "http://192.168.43.149:8086/CivicEngagement/TryLogin";
	 ;
	EditText user_name;
	EditText pass_word;
	SharedPreferences session;
	String user;
	String keyName;
    // Shared pref mode
    int PRIVATE_MODE = 0;
     
    
    private static final String PREFER_NAME = "session";
	
    
    protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		session=getApplication().getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
		
		user=session.getString("user", "none");
		if(user.equals("none")==false)
		{
		Intent i =new Intent(getApplicationContext(), Functionality.class);
		startActivity(i);
		}
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_page);
		Button opt_1 = (Button) findViewById(R.id.done);
		Button fb_log = (Button) findViewById(R.id.log_fb);
		Button sign_up = (Button) findViewById(R.id.sign_ups);
		Button loggs = (Button) findViewById(R.id.logins);
		
		 user_name = (EditText) findViewById(R.id.user_name);
		 pass_word = (EditText) findViewById(R.id.passwords);
		
		sign_up.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						SigningUp.class);
				startActivity(intent);
			}
		});
		
		
		
		
	
	}
	
	public void LoginUser(View v) 
	{
		
		
		
		try{
		
		 
         
		 String username=user_name.getText().toString();
		 String password=pass_word.getText().toString();
         // Open a HTTP  connection to  the URL
		 
         DefaultHttpClient httpClient = new DefaultHttpClient();
 
         HttpPost httpPost = new HttpPost(loginServerUri);
          List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        	nameValuePairs.add(new BasicNameValuePair("username", username));    
          //nameValuePairs.add(new BasicNameValuePair("password", user));
        	    nameValuePairs.add(new BasicNameValuePair("password", password));
        	//    nameValuePairs.add(new BasicNameValuePair("signup_confirmpassword", conpass));
        	  //  nameValuePairs.add(new BasicNameValuePair("signup_city",city1));
        	    
	     httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
         HttpResponse httpResponse = httpClient.execute(httpPost);
         StatusLine statusLine = httpResponse.getStatusLine();
         HttpResponse response;
		if(statusLine.getStatusCode() == HttpStatus.SC_OK){
             ByteArrayOutputStream out = new ByteArrayOutputStream();
             httpResponse.getEntity().writeTo(out);
             out.close();
            String  output = out.toString();
           System.out.println(output);
           
           if(output.contains("true"))
           {
        	   Editor editor=session.edit();
        	   editor.putString("user",username );
        	   editor.commit();
        	   Toast.makeText(this, "Welcome to your profile", Toast.LENGTH_SHORT).show();
        	   //Toast.makeText(getApplicationContext(), "saved"+session.getString("user", "none"), Toast.LENGTH_SHORT).show();
        	    
        	   Intent i = new Intent(getApplicationContext(),Functionality.class);
				startActivity(i);
           }
           else
           {
        	   Toast.makeText(this,"User name or password is wrong.", Toast.LENGTH_LONG).show();
           }
           
           
            
         }
        
          
          
         
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    
        
	


	}	
}
