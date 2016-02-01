package com.example.phil.phlam1_fueltrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.phil.phlam1_fueltrack.MESSAGE";

    //variables
    private static final String FILENAME = "file.sav";
    private EditText dateText;
    private EditText stationText;
    private EditText odometerText;
    private EditText gradeText;
    private EditText amountText;
    private EditText unitText;
    private EditText costText;
    private ListView entryList;

    private ArrayList<entry> entries = new ArrayList<entry>();
    private ArrayAdapter<entry> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findviewbyid
        dateText = (EditText) findViewById(R.id.date);
        stationText = (EditText) findViewById(R.id.station);
        odometerText = (EditText) findViewById(R.id.odometer);
        gradeText = (EditText) findViewById(R.id.grade);
        amountText = (EditText) findViewById(R.id.amount);
        unitText = (EditText) findViewById(R.id.unit);
        costText = (EditText) findViewById(R.id.cost);

        Button addButton = (Button) findViewById(R.id.add);
        Button logButton = (Button) findViewById(R.id.log);
        Button cancelButton = (Button) findViewById(R.id.cancel);


        addButton.setOnClickListener(new View.OnClickListener() {

            /** Called when the user clicks the Add button */
            public void onClick(View view){
            //Do something in response to button

                setResult(RESULT_OK);
                String date = dateText.getText().toString();
                String station = stationText.getText().toString();
                double odometer = Double.parseDouble(odometerText.getText().toString());
                String grade = gradeText.getText().toString();
                double amount = Double.parseDouble(amountText.getText().toString());
                double unit = Double.parseDouble(unitText.getText().toString());
                double cost = Double.parseDouble(costText.getText().toString());

                //Create new entry
                entry latestentry = new entry(date, station, odometer, grade, amount, unit, cost);

                //Add entry to arraylist
                entries.add(latestentry);
                adapter.notifyDataSetChanged();
                saveInFile();

            }

        });

        logButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //new activity to show log


                setResult(RESULT_OK);
                Intent i = new Intent(MainActivity.this, DisplayLog.class);
                i.putExtra("EXTRA_MESSAGE", entries);
                startActivity(i);

            }

        });

        cancelButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view){
                //Stops current input
            }
        });

    }

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//String[] tweets = loadFromFile();
		loadFromFile();
		adapter = new ArrayAdapter<entry>(this,
				R.layout.list_item, entries);
		entryList.setAdapter(adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 01-19 2016
            Type listType = new TypeToken<ArrayList<entry>>() {}.getType();
            entries = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            entries = new ArrayList<entry>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(entries, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
