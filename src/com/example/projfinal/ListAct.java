package com.example.projfinal;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListAct extends ListActivity{
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setListAdapter( new ArrayAdapter<String>(this,R.layout.issues_list,IssueType));
		
		ListView list= getListView();
		list.setTextFilterEnabled(true);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				Toast.makeText(getApplicationContext(), ((TextView) arg1).getText() , Toast.LENGTH_SHORT).show();
			}
			
		});
	}

	static final String[] IssueType =new String[]{
		"Likage Problem","Garbage Thrown","Wall poster","Light gone","Likage Problem","Garbage Thrown","Wall poster","Light gone","Likage Problem","Garbage Thrown","Wall poster","Light gone","Likage Problem","Garbage Thrown","Wall poster","Light gone"
		
	};

}
