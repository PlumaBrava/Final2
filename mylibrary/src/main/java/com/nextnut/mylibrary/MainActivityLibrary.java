package com.nextnut.mylibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivityLibrary extends AppCompatActivity {
    public String args="sin datos";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_library);
        Log.i("Libreria Android", "init");
        if(savedInstanceState==null){
            args =  getIntent().getExtras().getString("joke");
            Log.i("Libreria Android", "Save instance null");
            if (args==null){
                Log.i("Libreria Android", "arg Null");
            } else {Log.i("Libreria Android", "arl: "+args);}
        }


        Log.i("Libreria Android", "arl" + args);
        TextView libText =(TextView)findViewById(R.id.libraryText);
        libText.setText(args);

    }
}
