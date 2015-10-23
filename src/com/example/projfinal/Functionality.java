package com.example.projfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class Functionality extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.functions_page);
		Button req_buts = (Button) findViewById(R.id.req_but);
		Button req_2 = (Button) findViewById(R.id.button2);
		Button req_3 = (Button) findViewById(R.id.button3);
		Button req_4 = (Button) findViewById(R.id.button4);
		Button req_5 = (Button) findViewById(R.id.button5);
		Button req_6 = (Button) findViewById(R.id.button6);
		Button opt_1 = (Button) findViewById(R.id.button7);
		Button opt_2 = (Button) findViewById(R.id.button8);
		
		Log.d("TAG", "oncreate");
		
		req_buts.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent("com.example.projfinal.REQUEST"));
			}
		});

		req_2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});

		req_3.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});

		req_4.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});

		req_5.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});

		req_6.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});

		
		
		opt_1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent("com.example.projfinal.LOGINS"));
			}
		});
		
		opt_2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent("com.example.projfinal.CHANGES"));
			}
		});
		
	}

}
