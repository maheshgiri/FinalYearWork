package com.example.projfinal;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class CityListAct extends ListActivity {

	String message;
	
	static final String[] IssueType = new String[] { "Pune", "Aurangabad",
		"Nashik", "Delhi", "Mumbai", "Delhi", "Nagpur", "Nanded","Amaravati", "Delhi", "Nagpur", "Nanded","Amaravati" };
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Log.d("TAG", "oncreate");

		
		this.setListAdapter(new ArrayAdapter<String>(this, R.layout.city_list,R.id.label,
				IssueType));

		ListView list = getListView();

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				 message=arg1.toString();
				 Toast.makeText(getApplicationContext(), ((TextView)
				 arg1).getText() , Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getApplicationContext(), RequestDetails.class);
				intent.putExtra("message", message);
				startActivity(intent);
			}

		});
	}

}
