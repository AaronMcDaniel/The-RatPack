package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.controller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Button;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.R;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.Rat;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.RatFB;

/**
 * Class that displays a list of the rats sighted.
 */
public class Rat_Sightings_Activity extends AppCompatActivity {
    private static Rat[] ratList =new Rat[0];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_sightings);
        Log.d("TEST", "This is a test. please display");
        reload(findViewById(R.id.reload_button));
    }

    /**
     * Refreshes rat sightings page.
     * @param v the current view that the data is coming from.
     */
    public void reload(View v) {

        Log.d("TEST", "Called Reload");
        Rat[] oldRatList = ratList;
        updateRatList();
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_rat_sightings);
        for (int i = 0; i < oldRatList.length; i++) {//removes old buttons

            layout.removeView(findViewById(2 * i));
            layout.removeView(findViewById((2 * i) + 1));
        }
        for (int i = 0; i < ratList.length; i++) {//adds new buttons
            //rat button
            Button butt = new Button(this);
            Button parent = (Button)findViewById(R.id.add_rat_button);
            String newText = "Rat: " + ratList[i].getName();
            butt.setText(newText);
            butt.setId(2 * i);
            butt.setBackgroundColor(Color.WHITE);
            butt.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);

            butt.setHeight(parent.getHeight());
            butt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int targetView;
                    try {
                        targetView = v.getId() + (v.getId() / v.getId());
                    } catch(Exception e){
                        targetView = 1;
                    }
                    viewRat(findViewById(targetView));
                }
            });
            //rat lstView for info
            TextView buttDetails = new TextView(this);
            String newerText = "Unique ID: " + ratList[i].getUniqueKey()
                    + "\nName: " + ratList[i].getName() + "\nAddress: "
                    + ratList[i].getAddress() + "\nCity: " + ratList[i].getCity()
                    + "\nZipcode: " + ratList[i].getZipCode()
                    + "\nLocation Type: " + ratList[i].getLocationType()
                    + "\nBorough: " + ratList[i].getBorough()
                    + "\nDate: " + ratList[i].getDate() + "\nTime: "
                    + ratList[i].getTime() + "\nLatitude: "
                    + ratList[i].getLatitude() + "\nLongitude: "
                    + ratList[i].getLongitude();
            buttDetails.setText(newerText);
            buttDetails.setId((2 * i) + 1);
            final float defaultTextSize = 20f;
            buttDetails.setTextSize(defaultTextSize);
            buttDetails.setVisibility(View.GONE);

            layout.addView(butt);
            layout.addView(buttDetails);
        }

    }

    /**
     * Takes user to home page.
     * @param v the current view that the data is coming from.
     */
    public void onBack(View v) {
        startActivity(new Intent(Rat_Sightings_Activity.this, Home_Activity.class));
    }

    /**
     * Takes user to add rat page.
     * @param v the current view that the data is coming from.
     */
    public void addRat(View v) {
        startActivity(new Intent(Rat_Sightings_Activity.this, Rat_Input_Activity.class));

    }

    /**
     * Shows rat details.
     * @param v the current view that the data is coming from.
     */
    private void viewRat(View v) {
        if (v.getVisibility() == View.GONE) {
            v.setVisibility(View.VISIBLE);
        }
        else {
            v.setVisibility(View.GONE);
        }

    }

    /**
     * Shows first 50 entries of rat sightings.
     */
    private static void updateRatList() {

        Log.d("TEST", "entered updateRatList()");
        final int max = 50;
        Rat[] temp = RatFB.getAllRats();
        if (temp.length > max){
            ratList = new Rat[max];
            System.arraycopy(temp, 0, ratList, 0, ratList.length);
        }
        else {
            ratList = temp;
        }
        Log.d("TEST", "finished method. Updated ist to length: "+ ratList.length);
    }
}
