package com.example.preethi.stepcounter;

import android.os.Environment;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
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


public class FileUtils {
    //for writing into the file
    public static void writeToFile(String startdate, float steps, float distance, String startTime,float distancemiles) {
        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
            File file = new File(path + "/StepCounter.txt");
            if(!file.exists())
            {
                file.createNewFile();

            }
            System.out.println("File Exists");
            FileOutputStream fos = new FileOutputStream(file, true);
            String data = startdate + "\t" + steps + "\t" + distance + "\t" + startTime + "\t" + distancemiles+ "\n";
            fos.write(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //for reading from the file
    public static LinkedList<Steps> readFromFile() {
        LinkedList<Steps> list = new LinkedList<>();

        try {

            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
            File file = new File(path + "/StepCounter.txt");
            if (file.exists()) {
                //file exist
                FileInputStream fIn = new FileInputStream(file);
                DataInputStream in = new DataInputStream(fIn);
                BufferedReader myReader = new BufferedReader(new InputStreamReader(in));

                String receiveString = "";


                while ((receiveString = myReader.readLine()) != null) {
                    String[] contactStr = receiveString.split("\\t");
                    Steps step = new Steps(contactStr[0], Float.valueOf(contactStr[1]), Float.valueOf(contactStr[2]), contactStr[3],Float.valueOf(contactStr[4]));
                    list.add(step);

                }

            } else
            {
                //do nothing
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
