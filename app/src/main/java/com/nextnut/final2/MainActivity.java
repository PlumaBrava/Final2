package com.nextnut.final2;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perez.juan.jose.backend.myApi.MyApi;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.nextnut.final2.R;
import com.nextnut.mylibrary.MainActivityLibrary;

import junit.framework.TestCase;

import java.io.IOException;
//import com.nextnut.Joke;

public class MainActivity extends AppCompatActivity {
    public ProgressBar spinner;
    private InterstitialAd mInterstitial;
    private AdView mAddView;
    private Button btnSat;

    public void setSpinnerVisibility(int v) {
        this.spinner.setVisibility(v);
    }

    private static MyApi myApiService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        Log.i("Final1", "initButton spiner visibility: " + spinner.getVisibility());
        btnSat = (Button) findViewById(R.id.jokebutton);
        btnSat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("Final1", "presButton");
                TextView txt = (TextView) findViewById(R.id.instructions_text_view);
                txt.setText(null);
                showInsterstitial();
                //tellJoke();


            }
        });



        mAddView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("A086DD273532AA15DD4D5A83D2A6880E")
                .addTestDevice("4A2790CC2F2F210B9805A86F1289FEAF")
                .build();
        mAddView.setAdListener(new ToastAdListener(this));
        mAddView.loadAd(adRequest);




        mInterstitial = newInterstitialAd();
        loadInterstitial();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(){
        spinner.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Se llama a backend", Toast.LENGTH_SHORT).show();
//        EndpointsAsyncTaskold p=new EndpointsAsyncTaskold(spinner,this);
        EndpointsAsyncTaskold p=new EndpointsAsyncTaskold(spinner);
//        p.execute(new Pair<Context, String>(this, "Manfred"));
        p.execute(new Pair<Context, String>(this, "juan"));
//
// Intent myIntent = new Intent(this, MainActivityLibrary.class);
//        myIntent.putExtra("joke",new Joke().getJoke());
//        startActivity(myIntent);
    }




    public void loadInterstitial(){


            // Disable the next level button and load the ad.
        btnSat.setEnabled(false);
        AdRequest adRequest = new AdRequest.Builder()
                    .setRequestAgent("android_studio:ad_template")
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .addTestDevice("A086DD273532AA15DD4D5A83D2A6880E")
                    .addTestDevice("4A2790CC2F2F210B9805A86F1289FEAF")
                    .build();
        mInterstitial.loadAd(adRequest);
    }









    public void showInsterstitial(){
        if(mInterstitial != null && mInterstitial.isLoaded()){
            mInterstitial.show();

        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();

        }


    }

    private InterstitialAd newInterstitialAd() {

        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new ToastAdListener(this) {
            @Override
            public void onAdLoaded() {
                btnSat.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                btnSat.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                mInterstitial = newInterstitialAd();
                loadInterstitial();
                spinner.setVisibility(View.GONE);
                tellJoke();
            }
        });
        return interstitialAd;
    }


}
