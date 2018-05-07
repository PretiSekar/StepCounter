package com.example.preethi.stepcounter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

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


public class Settings extends AppCompatActivity {

    Button about;
    ToggleButton toggle;
    Intent returnIntent=new Intent();
    @Override
    //for navigating to the about page and toggling between the activities
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent receiveIntent = getIntent();
        about = (Button) findViewById(R.id.button);


        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aboutintent = com.example.preethi.stepcounter.about.makeIntent(Settings.this);
                startActivityForResult(aboutintent,1002);
            }
        });

        toggle = (ToggleButton) findViewById(R.id.toggleButton);

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnIntent.putExtra("id",toggle.getText());
                if(toggle.getText().toString().contains("ON"))
                    toggle.setChecked(true);
                else
                    toggle.setChecked(false);
            }
        });

    }
    public static Intent makeIntent(Context context) {
        return new Intent(context, Settings.class);
    }

    //method on pressing back
    @Override
    public void onBackPressed()
    {
        if(returnIntent.getExtras()!=null)
        {
            setResult(Activity.RESULT_FIRST_USER,returnIntent);
        }
        else
        {
            setResult(Activity.RESULT_OK,returnIntent);
        }
        finish();

    }
    //results to be run on return back to the activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case 1002:
                if(resultCode==Activity.RESULT_FIRST_USER){
                    //do nothing
                }
                if(resultCode == Activity.RESULT_OK ){
                    //do nothing
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
