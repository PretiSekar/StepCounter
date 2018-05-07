package com.example.preethi.stepcounter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static com.example.preethi.stepcounter.FileUtils.writeToFile;

/*************************************************************************************************************************
 * Project:
 * StepCounter
 *
 * Submitted By:
 *
 * Preethi Sekar pxs163930
 *
 * Description:
 *
 * The application StepCounter counts the number of steps
 * The Main Application displays the start and stop button for counting the steps.
 * Note that the application starts with the previously counted steps since it uses step counter and not step detector
 * Starts initiates counting whereas stop finishes it
 * The user can view the history from the more options on the top right
 * The user can read about the application from the about page in the settings
 * The user can toggle between the distance metric in the settings.
 * All the updates are made the "StepCounter.txt" after each modification
 *
 * Class Description:
 *
 * MainActivity is the Main Activity that displays the step counter application.
 * Settings is the activity that takes care of the about and toggle between distance metrics
 * about displays the activity that displays about the application
 * History activity takes care of the list of steps taken
 * Fileutils handles the read and write to the "StepCounter.txt" text file.
 * Steps defines the step list attributes startdate starttime, distance in kms, distance in miles, steps taken
 * CustomAdapter handles the temporary list that stores the contacts.
 *
 ****************************************************************************************************************************/


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager sensorManager;
    TextView steps;
    TextView distancemiles;
    TextView distance;
    ImageButton start,stop;
    boolean miles = false;
    boolean running = false;
    private int requestCode;
    private int grantResults[];
    Sensor count;
    @Override
    // on create takes care of initialting the stepcounter sensor and the counting the steps
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        start = (ImageButton) findViewById(R.id.imageButton2);
        steps= (TextView) findViewById(R.id.textView2);

        distance= (TextView) findViewById(R.id.textView);
        distancemiles= (TextView) findViewById(R.id.textView9);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        count = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        running = false;
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = true;
                onStart();

            }
        });

        stop = (ImageButton) findViewById(R.id.imageButton3);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // stop the sensor
                onStop();
            }
        });

        if(miles == false)
        {
            distance.setVisibility(View.VISIBLE);
            distancemiles.setVisibility(View.INVISIBLE);
        }
        if(miles == true)
        {
            distancemiles.setVisibility(View.VISIBLE);
            distance.setVisibility(View.INVISIBLE);
        }

    }

    //overriding the onstop method for stepcounter
    @Override
    public void onStop()
    {
        super.onStop();
        sensorManager.unregisterListener(this,count);
        // add the contents to file
        Date c = Calendar.getInstance().getTime();
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
        String currentDateTimeString = sdf.format(d);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ) {
            //if you dont have required permissions ask for it (only required for API 23+)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
            onRequestPermissionsResult(requestCode, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, grantResults);
        }
        writeToFile(formattedDate,Float.valueOf((String) steps.getText()),Float.valueOf((String)distance.getText()),currentDateTimeString,Float.valueOf((String)distancemiles.getText()));
    }

    // for overriding the permissions on android
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d("Permission", "Granted");
                } else {

                    Toast.makeText(MainActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                    onDestroy();
                }
                return;
            }
        }
    }

    //overriding the on start for step counter activity
    @Override
    protected void onStart()
    {
        super.onStart();
        if(running)
        {
            if(count != null)
            {
                sensorManager.registerListener(this,count,SensorManager.SENSOR_DELAY_UI);
                Toast.makeText(this, "Started!",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Sensor Not Found!",Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // on resume for stepcounter
    protected void onResume()
    {
        super.onResume();
        if(!running)
        {
            sensorManager.unregisterListener(this,count);
        }

    }

    //onpause for stepcounter
    protected void onPause()
    {
        super.onPause();
        if(!running)
        {
            sensorManager.unregisterListener(this,count);
        }

    }

    // selecting the items for navigation to settings and history
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settings = com.example.preethi.stepcounter.Settings.makeIntent(MainActivity.this);
            startActivityForResult(settings,1001);
            running = false;
        }
        if (id == R.id.action_history) {
            Intent createhistory = History.makeIntent(MainActivity.this);
            startActivityForResult(createhistory,1001);
            running = false;
        }

        return super.onOptionsItemSelected(item);
    }

    //get the distance in kms
    public float getDistanceRun(float steps){

        float distancekms = (steps*78)/(float)100000;
        return distancekms;
    }
    //get the distance  in miles
    public float getDistanceRunMiles(float steps){

        float distancekms = (steps*78)/(float)100000;
        float distancemiles = (float) (distancekms/1.609344);
        return distancemiles;
    }

    //Sensorchanged for counting the number of steps
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(running)
        {
            steps.setText(String.valueOf(sensorEvent.values[0]));
            distance.setText(String.valueOf(getDistanceRun(sensorEvent.values[0])));
            distancemiles.setText(String.valueOf(getDistanceRunMiles(sensorEvent.values[0])));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    //activity that needs to be performed on returning back to the page
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case 1001:
                if(resultCode==Activity.RESULT_FIRST_USER){

                    //miles = data.getStringExtra("id");
                    //Toast.makeText(this, data.getStringExtra("id"),Toast.LENGTH_SHORT).show();
                    if(data.getStringExtra("id").contains("ON"))
                    {
                        if(miles == false)
                        {
                            distance.setVisibility(View.VISIBLE);
                            distancemiles.setVisibility(View.INVISIBLE);
                            miles = true;
                        }
                        else
                        {
                            distancemiles.setVisibility(View.VISIBLE);
                            distance.setVisibility(View.INVISIBLE);
                            miles = false;
                        }

                    }
                    running = false;
                }
                if(resultCode == Activity.RESULT_OK ){
                    //do nothing
                    running = false;
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
