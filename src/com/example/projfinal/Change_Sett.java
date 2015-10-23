package com.example.projfinal;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Change_Sett extends ListActivity{

	
	static final String[] ChangeType = new String[] { "About Pune", "About Civic Engagement",
		"Change City", "Refresh Widgets" };
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setListAdapter(new ArrayAdapter<String>(this, R.layout.change_city,R.id.label,
				ChangeType));
		ListView list = getListView();

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(getApplicationContext(),
				Functionality.class);
				startActivity(intent);
			}

		});
		
	}
	
}
