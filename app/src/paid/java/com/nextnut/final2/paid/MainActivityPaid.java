package com.nextnut.final2.paid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nextnut.final2.EndpointsAsyncTaskold;
import com.nextnut.final2.R;

public class MainActivityPaid extends AppCompatActivity {
    public ProgressBar spinner;
    private Button btnSat;
    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_paid);

        Log.i("Final1", "initButton");
        btnSat = (Button) findViewById(R.id.jokebutton);
        btnSat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("Final1", "presButton");
                tellJoke();


            }
        });

        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

    }

    public void tellJoke(){
        Log.i("Final1", "tellJoke");
//        Toast.makeText(this, "Se llama sa backend", Toast.LENGTH_SHORT).show();
        new EndpointsAsyncTaskold(this,spinner,btnSat).execute();

    }
}
