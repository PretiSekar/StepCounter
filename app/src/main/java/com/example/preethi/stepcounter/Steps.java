package com.example.preethi.stepcounter;

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


public class Steps {

    private String startdate;
    private float steps;
    private float distance;
    private String starttime;
    private float distancemiles;

    //getters and setters for all the fields
    public String getStartdate() {
        return startdate;
    }

    public float getSteps() {
        return steps;
    }

    public float getDistance() {
        return distance;
    }

    public String getStarttime() {
        return starttime;
    }

    public float getDistancemiles() {
        return distancemiles;
    }
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public void setSteps(float steps) {
        this.steps = steps;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public void setDistancemiles(float distancemiles) {
        this.distancemiles = distancemiles;
    }

    //constructor for initializations
    public Steps(String startdate, float steps, float distance, String starttime,float distancemiles) {
        this.startdate = startdate;
        this.steps = steps;
        this.distance = distance;
        this.starttime = starttime;
        this.distancemiles = distancemiles;
    }
}
