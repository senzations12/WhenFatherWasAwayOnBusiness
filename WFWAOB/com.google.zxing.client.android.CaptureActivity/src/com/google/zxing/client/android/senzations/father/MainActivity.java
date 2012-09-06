package com.google.zxing.client.android.senzations.father;

import com.google.zxing.client.android.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
 
@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		Resources ressources = getResources(); 
		TabHost tabHost = getTabHost(); 
 
		// Capture tab
		Intent intentCapture = new Intent().setClass(this, com.google.zxing.client.android.CaptureActivity.class);
		TabSpec tabSpecCapture = tabHost
		  .newTabSpec("Capture")
		  .setIndicator("Capture")
		  .setContent(intentCapture);
 TextView tab1 = new TextView (this);
 //tab1.setText("")
		// Sensor tab
		Intent intentSensor = new Intent().setClass(this, com.google.zxing.client.android.senzations.father.SensorsActivity.class);
		TabSpec tabSpecSensor = tabHost
		  .newTabSpec("Sensors")
		  .setIndicator("")
		  .setContent(intentSensor);	
 
		// add all tabs 
		tabHost.addTab(tabSpecCapture);
		tabHost.addTab(tabSpecSensor);
 
		//set Windows tab as default (zero based)
		tabHost.setCurrentTab(0);
	}
 
}