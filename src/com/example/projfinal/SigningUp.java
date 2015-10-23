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
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SigningUp extends Activity{

	EditText  signup_email;
	EditText  signup_password;
	EditText  confirm_signuppassword;
	EditText  signup_city;
	 Spinner spinner2;
	 String   signUpServerUri="http://192.168.43.87:8086/CivicEngagement/SignUpTry";
		
	 Button sign_up;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up);
		Button fb_sign = (Button) findViewById(R.id.sign_fb);
		Button done = (Button) findViewById(R.id.done);
		
		Button sign_up = (Button) findViewById(R.id.sign_ups);
		Button skips = (Button) findViewById(R.id.skips);
		signup_email = (EditText) findViewById(R.id.email);
		signup_password= (EditText) findViewById(R.id.password);
		confirm_signuppassword = (EditText) findViewById(R.id.cpassword);
		spinner2=(Spinner)findViewById(R.id.spinner2);
	    
		addListenerOnButton();
		addListenerOnSpinnerItemSelection();

	}
		
		public void addListenerOnSpinnerItemSelection() {
			spinner2 = (Spinner) findViewById(R.id.spinner2);
//			spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		  }
		 
		  // get the selected dropdown list value
		  public void addListenerOnButton() {
		 
			spinner2 = (Spinner) findViewById(R.id.spinner2);
//			spinner2 = (Spinner) findViewById(R.id.spinner2);
		   sign_up = (Button) findViewById(R.id.sign_ups);
		
		sign_up.setOnClickListener(new OnClickListener() {
		 
			  @Override
			  
			  public void onClick(View v) {
		 
				 
			   // Toast.makeText(SigningUp.this,"City : " +  "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem())  ,Toast.LENGTH_SHORT).show();
			 
				 
				  
				 String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
				 
				 int flag=0;
				 
				 if(signup_email.getText().toString().matches(""))
				 {
					 signup_email.setError("Enter email address.");
					 flag=1;
					 return;
				 }
				 if(signup_password.getText().toString().matches(""))
					 
				 {  flag=1;
					 signup_password.setError("Enter password.");
					 return;
				 }
				 if(confirm_signuppassword.getText().toString().matches(""))
				 {   flag=1;
					 confirm_signuppassword.setError("Enter password");
					 return;
				 }
				 
				 if(signup_email.getText().toString().matches(emailPattern)==false && signup_email.getText().toString().length() > 0)
				 {   flag=1;
					 signup_email.setError("Enter valid email address.");
						return;
					 
				 }
				if(signup_password.getText().toString().matches("")==false&&signup_password.getText().toString().length()<6)
				{   flag=1;
					signup_password.setError("Password should have minimum 6 characters.");
					return;
				}
				if(confirm_signuppassword.getText().toString().matches("")==false&&confirm_signuppassword.getText().toString().length()<6)
				{  flag=1;
					confirm_signuppassword.setError("Password should have minimum 6 characters.");
					return;
				}
				if(confirm_signuppassword.getText().toString().matches("")==false&&confirm_signuppassword.getText().toString().length()>6&&signup_password.getText().toString().matches("")==false&&signup_password.getText().toString().length()>6 &&confirm_signuppassword.getText().toString().matches(signup_password.getText().toString())==false)
				{
					confirm_signuppassword.setError("Password do not match.");
					return;
				}
				
				
                
				
				   if(flag==0)
				   {
				
				  try{
						
					  
					 String email=signup_email.getText().toString();
					  
				         String pass=signup_password.getText().toString();
						 
						  String city=String.valueOf(spinner2.getSelectedItem());
				         
						
				         // Open a HTTP  connection to  the URL
						 
				         DefaultHttpClient httpClient = new DefaultHttpClient();
				         
				         HttpPost httpPost = new HttpPost( signUpServerUri);
				         
				          List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				        	nameValuePairs.add(new BasicNameValuePair("signup_email", email));    
				          //nameValuePairs.add(new BasicNameValuePair("password", user));
				        	    nameValuePairs.add(new BasicNameValuePair("signup_password", pass));
				        	//    nameValuePairs.add(new BasicNameValuePair("signup_confirmpassword", conpass));
				        	  //  nameValuePairs.add(new BasicNameValuePair("signup_city",city1));
				        	    nameValuePairs.add(new BasicNameValuePair("signup_city", city));
				        	    
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
				           
				           if(output.contains("102"))
				           {
				        	   
				        	   Intent intent = new Intent(getApplicationContext(),LoginOne.class);
								startActivity(intent);
				           }
				           else if(output.contains("101"))
				           {
				        	   Toast.makeText(getApplicationContext(),"User is already exists .", Toast.LENGTH_SHORT).show();
				           }
				           else
				           {
				        	   Toast.makeText(getApplicationContext(),"Sign Up can not be completed.", Toast.LENGTH_SHORT).show(); 
				           }
				           
				           
				            
				         }
				        
				          
				          
				         
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
				   }
				  
				 
				
			  }
		
			  
		 
			});
		
		  }
		
		
		
	
	}

