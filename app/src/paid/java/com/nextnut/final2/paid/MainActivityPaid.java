package com.nextnut.final2.paid;

import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nextnut.final2.EndpointsAsyncTask;
import com.nextnut.final2.R;

public class MainActivityPaid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_paid);

        Log.i("Final1", "initButton");
        Button btnSat = (Button) findViewById(R.id.jokebutton);
        btnSat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("Final1", "presButton");
                tellJoke();


            }
        });

    }

    public void tellJoke(){
        Toast.makeText(this, "Se llama a backend", Toast.LENGTH_SHORT).show();
        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));
//        Intent myIntent = new Intent(this, MainActivityLibrary.class);
//        myIntent.putExtra("joke",new Joke().getJoke());
//        startActivity(myIntent);
    }
}
