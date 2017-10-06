package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class Home_Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        mAuth = FirebaseAuth.getInstance();

        //shows that you are the right user
        TextView temp = (TextView)findViewById(R.id.login_status_textView);
        temp.setText(temp.getText()+"\n"+mAuth.getCurrentUser().getEmail());
    }

    public void onLogout(View v) {
        mAuth.signOut();
        startActivity(new Intent(Home_Activity.this,Welcome_Activity.class));
    }
    public void toRatSightings(View v){
        startActivity(new Intent(Home_Activity.this, Rat_Sightings_Activity.class));

    }
}