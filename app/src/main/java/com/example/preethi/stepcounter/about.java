package com.example.preethi.stepcounter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

public class about extends AppCompatActivity {

    //xml to invoke oncreate of the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
    public static Intent makeIntent(Context context)
    {
        return new Intent(context, about.class);
    }

    //action to be performed on press
    @Override
    public void onBackPressed()
    {
        Intent returnIntent=new Intent();
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }
}
