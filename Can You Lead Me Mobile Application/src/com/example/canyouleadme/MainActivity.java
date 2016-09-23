package com.example.canyouleadme;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	GPSTracker gps;
	
	EditText textSMS;
	TextView output1;
	Button get_destination;
	
	String sdestination;
	String slatitude;
	String slongitude;
	double latitude;
	double longitude;
	
	final String phone = "+441597800018";
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        get_destination = (Button) findViewById(R.id.get_directions);
        
        gps = new GPSTracker(MainActivity.this);
        
        textSMS= (EditText) findViewById(R.id.destination);
        
        // check if GPS enabled     
    	if(gps.canGetLocation()){
    	   // latitude = gps.getLatitude();
    		latitude =53.472346;
    	   // longitude = gps.getLongitude();
    		longitude=-2.246416;
    	}else{
    	// can't get location
    	// GPS or Network is not enabled
    	// Ask user to enable GPS/network in settings
    	    gps.showSettingsAlert();  
    	    } 
    	
    	slatitude =String.valueOf(latitude);
    	slongitude=String.valueOf(longitude);
    	
    	
    	get_destination.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String sms = slatitude +"_" + slongitude + "_"+ textSMS.getText().toString();
				
				try {
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(phone, null, sms, null, null);
					Toast.makeText(getApplicationContext(), "SMS Sent!",
							Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"SMS faild, please try again later!",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}

			}
		});
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
        // as you specify` a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    }
