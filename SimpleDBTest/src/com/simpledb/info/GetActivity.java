package com.simpledb.info;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GetActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get);
		try {
			ListView movieListview = (ListView) findViewById(R.id.listView1);
			Movie [] movieList= new ReadFromDB().execute().get();
			String[] list= new String[movieList.length];
			for (int i = 0; i < list.length; i++) {
				list[i]= movieList[i].movieName;
				}
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,R.layout.activity_listview,list);
			movieListview.setAdapter(adapter);
	       
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get, menu);
		return true;
	}

}
