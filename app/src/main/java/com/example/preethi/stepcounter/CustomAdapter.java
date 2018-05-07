package com.example.preethi.stepcounter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

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


public class CustomAdapter extends BaseAdapter {

    //the temp place for displaying the lists
    private Context mContext;
    private List<Steps> filecontents;
    //constructor
    public CustomAdapter(Context mContext, List<Steps> fc) {
        this.mContext = mContext;
        this.filecontents = fc;
    }
    //all the methods that need to be overridden
    @Override
    public int getCount() {
        return filecontents.size();
    }

    @Override
    public Object getItem(int i) {
        return filecontents.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext,R.layout.listviewcontents,null);
        //EditText editText =(EditText) v.findViewById(R.id.edit_text);
        TextView date = (TextView) v.findViewById(R.id.tdate);
        TextView steps = (TextView) v.findViewById(R.id.tsteps);
        TextView distance = (TextView) v.findViewById(R.id.tdistance);
        TextView starttime = (TextView) v.findViewById(R.id.tstarttime);
        //editText.setText(filecontents.get(i).getStartdate());
        steps.setText(( String.valueOf(filecontents.get(i).getSteps())));
        distance.setText(String.valueOf(filecontents.get(i).getDistance()));
        starttime.setText(filecontents.get(i).getStarttime());
        date.setText(filecontents.get(i).getStartdate());

        return v;
    }
}
