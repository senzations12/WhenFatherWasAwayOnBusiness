package com.google.zxing.client.android.senzations.father;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Calendar;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.ws4d.coap.client.Client;

import com.google.zxing.client.android.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;




	public class SensorsActivity extends Activity {
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_sensor1);
	        
	        Button btn = (Button) findViewById(R.id.button1);
	        
		 //ListView listView1 = (ListView) findViewById(R.id.listView1);
	       
	        //String[] items = { "Mecavnik" };
	       
	        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	                    //android.R.layout.simple_list_item_1, items);
	       
	        //listView1.setAdapter(adapter);

	        //listView1.setOnItemClickListener(new OnItemClickListener() {
	        btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try{
		            	AlertDialog.Builder builder = new AlertDialog.Builder(SensorsActivity.this);
		            	builder.setMessage("Temperature: " +  new RequestTask().execute("http://172.29.25.229:8080/temperature").get()
	+"\nHumidity: " +new RequestTask().execute("http://172.29.25.229:8080/humidity").get()+" %")
		            	       .setCancelable(true);
		            	AlertDialog alert = builder.create();
		            	alert.show();
		            	} catch (Exception e){}
					
				}
			}); 
	            
	            	
	            	
	            	
	            	
	              /* Client client = new Client();
	            	AlertDialog.Builder builder = new AlertDialog.Builder(SensorsActivity.this);
	            	try {
	            	builder.setMessage("Temperature: " + client.getTemperature().get() + " C\nHumidity: " + client.getHumidity().get() + " %")
	            	       .setCancelable(true);
	            	AlertDialog alert = builder.create();
	            	alert.show();
	            	} catch (UnknownHostException ex) {
	            		builder.setMessage("Sensors unreachable")
	            	       .setCancelable(true);
	            	AlertDialog alert = builder.create();
	            	alert.show();
	            	}
	            	*/
	            	//}
	            //});
	    }}
	    