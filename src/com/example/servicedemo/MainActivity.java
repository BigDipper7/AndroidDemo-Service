package com.example.servicedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	private Button btnStart, btnPause, btnStop, btnExit, btnClose;
	private static final String TAG = "MainActivity";
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		Log.e(TAG, "OnCreate() -> init()");
		btnStart = (Button) findViewById(R.id.btn_start);
		btnPause = (Button) findViewById(R.id.btn_pause);
		btnStop  = (Button) findViewById(R.id.btn_stop);
		btnClose = (Button) findViewById(R.id.btn_close);
		btnExit = (Button) findViewById(R.id.btn_exit);
		
		btnStart.setOnClickListener(this);
		btnPause.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnClose.setOnClickListener(this);
		btnExit.setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
		intent = new Intent("com.example.service.localService");
		int op = -1;
		int itemId = v.getId();
		
		Log.e(TAG, "onClick() -> itemId = "+itemId);
		
		switch (itemId) {
		case R.id.btn_start: op = 1; break;

		case R.id.btn_pause: op = 2;	break;

		case R.id.btn_stop: op = 3; break;

		case R.id.btn_close: op = 4; Log.e(TAG, "btn_close! before this.finish");this.finish();  Log.e(TAG, "btn_close! after this.finish");break;
			
		case R.id.btn_exit: op = 5; Log.e(TAG, "btn_exit! before stop service");stopService(intent); this.finish(); break;

		default: break;
		}

		Bundle bundle = new Bundle();
		bundle.putInt("op", op);
		
		Log.e(TAG, "onClick op = "+op);
		
		intent.putExtras(bundle);
		Log.e(TAG, "startService()");
		startService(intent);
	}
}
