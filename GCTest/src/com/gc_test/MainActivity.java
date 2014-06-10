package com.gc_test;

import java.util.HashMap;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {



	static boolean state = false;
	static TextView logTview = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment()).commit();
		}

		Button runStopButton = (Button)findViewById(R.id.button1);

		runStopButton.setOnClickListener(this) ;


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	@Override
	public void onClick(View v) {
		
		Spinner typeSpinner = (Spinner)findViewById(R.id.spinner1);
		
		String typeselction = (String)typeSpinner.getSelectedItem() ; 
		GCTest mygc = new GCTestByte();
		if ("string".equals(typeselction)) {
			mygc = new GCTestString();
		} else if ("int".equals(typeselction)) {
			mygc = new GCTestInt();
		} else if ("byte".equals(typeselction)) {
			mygc = new GCTestByte();
		} else if ("bitmap".equals(typeselction)) {
			mygc = new GCTestBitmap();
		} else if ("choko".equals(typeselction)) {
			mygc = new GCTestChoko();
		} 
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		CheckBox cbox = (CheckBox)findViewById(R.id.checkBox1) ;
		if (cbox.isChecked()) {
			params.put("randomRemoval", Boolean.valueOf(true)) ;
		} else {
			params.put("randomRemoval", Boolean.valueOf(false)) ;
		}
		cbox = (CheckBox)findViewById(R.id.checkBox2) ;
		if (cbox.isChecked()) {
			params.put("forceGc", Boolean.valueOf(true)) ;
		} else {
			params.put("forceGc", Boolean.valueOf(false)) ;
		}
		cbox = (CheckBox)findViewById(R.id.checkBox3) ;
		if (cbox.isChecked()) {
			params.put("descOrasc", "desc") ;
		} else {
			params.put("descOrasc", "asc") ;
		}
		
		
		if (!state) {
			try {
				mygc.doWork(params);
			} catch (Exception e) {
				Log.i("gctest" , "problem" , e);
			}
		}
		state=state;
	}


}
