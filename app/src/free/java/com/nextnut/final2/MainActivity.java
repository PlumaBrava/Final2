package com.nextnut.final2;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;

import android.widget.Toast;

import com.example.perez.juan.jose.backend.myApi.MyApi;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.InterstitialAd;



public class MainActivity extends AppCompatActivity {
    public ProgressBar spinner;
    private Button btnSat;
    private InterstitialAd mInterstitial;
    private AdView mAddView;


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




        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        Log.i("Final1", "initButton spiner visibility: " + spinner.getVisibility());
        btnSat = (Button) findViewById(R.id.jokebutton);
        btnSat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("Final1", "presButton");

                showInsterstitial();
            }
        });


        // Banner advertising
        mAddView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("A086DD273532AA15DD4D5A83D2A6880E")
                .addTestDevice("4A2790CC2F2F210B9805A86F1289FEAF")
                .build();
            //mAddView.setAdListener(new ToastAdListener(this));
        mAddView.loadAd(adRequest);

        // interstitial advertising
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

        //Toast.makeText(this, "Se llama a backend", Toast.LENGTH_SHORT).show();

        EndpointsAsyncTaskold p=new EndpointsAsyncTaskold(this,spinner,btnSat);

        p.execute();

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
        interstitialAd.setAdListener(new AdListener() {


            @Override
            public void onAdLeftApplication() {
//                Toast.makeText(getApplicationContext(),"LeftApplication",Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onAdOpened() {
//                Toast.makeText(getApplicationContext(),"on AdOpened",Toast.LENGTH_SHORT).show();

            }



            @Override
            public void onAdLoaded() {
//                Toast.makeText(getApplicationContext(),"on onAdLoaded",Toast.LENGTH_SHORT).show();
               if(spinner.getVisibility()!=View.VISIBLE){ btnSat.setEnabled(true);}
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // When Failed, prepear a new add and call tellJoke.
                String mErrorReason="";
                switch (errorCode){

                    case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                        mErrorReason ="Internal Error";
                        break;

                    case AdRequest.ERROR_CODE_INVALID_REQUEST:
                        mErrorReason ="Invalid Request";
                        break;

                    case AdRequest.ERROR_CODE_NETWORK_ERROR:
                        mErrorReason ="Network Error";
                        break;

                    case AdRequest.ERROR_CODE_NO_FILL:
                        mErrorReason ="No Fill";
                        break;
                }
                Toast.makeText(getApplicationContext(),
                        String.format("on onAdFailedToLoad (%S)", mErrorReason ),
                        Toast.LENGTH_SHORT).show();
                super.onAdFailedToLoad(errorCode);


                mInterstitial = newInterstitialAd();
                loadInterstitial();
                btnSat.setEnabled(true);
            }



            @Override
            public void onAdClosed() {
//                Toast.makeText(getApplicationContext(),"on Ad Closed",Toast.LENGTH_SHORT).show();
                // When Closed the add is clossed, prepear a new add and call tellJoke.
                mInterstitial = newInterstitialAd();
                loadInterstitial();
                tellJoke();
            }
        });
        return interstitialAd;
    }


}
