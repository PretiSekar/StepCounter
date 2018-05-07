package com.example.preethi.stepcounter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import java.util.LinkedList;

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

public class History extends AppCompatActivity {

    private int requestCode;
    private int grantResults[];
    private CustomAdapter adapter;
    @Override
    //for reading the file into memory
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent receiveIntent = getIntent();
        ListView listView = (ListView)findViewById(R.id.listview);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ) {
            //if you dont have required permissions ask for it (only required for API 23+)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, requestCode);
            onRequestPermissionsResult(requestCode, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, grantResults);
        }
        LinkedList<Steps> file = FileUtils.readFromFile();
        if(file == null)
        {
            Toast.makeText(History.this,"Nothing to display!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            adapter=new CustomAdapter(getApplicationContext(),file);
            listView.setAdapter(adapter);
        }
    }

    @Override // android recommended class to handle permissions
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d("permission", "granted");
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.uujm
                    Toast.makeText(History.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();

                    //app cannot function without this permission for now so close it...
                    onDestroy();
                }
                return;
            }
        }
    }



    public static Intent makeIntent(Context context) {
        return new Intent(context, History.class);
    }

    @Override
    public void onBackPressed()
    {
        Intent returnIntent=new Intent();
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }


}
