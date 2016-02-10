package com.nextnut.final2;


import android.content.Context;
import android.content.Intent;

import android.os.AsyncTask;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.perez.juan.jose.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.nextnut.mylibrary.MainActivityLibrary;


import java.io.IOException;



/**
 * Created by perez.juan.jose on 21/01/2016.
 */

public class EndpointsAsyncTaskold extends AsyncTask<Context,Void, String> {

    private static MyApi myApiService = null;
    private Context context;
    private ProgressBar spinner;
    private Button btnSat;

    public EndpointsAsyncTaskold(ProgressBar spinner,Button btnSat) {
        this.spinner = spinner;
        this.btnSat=btnSat;
    }



    @Override
    protected void onPreExecute() {
        Log.i("AsyncTaskOld", "onPreExecute");
        spinner.setVisibility(View.VISIBLE);
        btnSat.setEnabled(false);
        super.onPreExecute();
    }

    @Override
    public String doInBackground(Context... contexts) {


        Log.i("AsyncTaskOld", "doInBackground");
        if (myApiService == null) {  // Only do this once

            // This is to use de localhost:8080 server. Coment to use de web server after deply

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                     }
                    });

            // Uncoment this to use web server and coment the block above. Remember to deploy the the service.

//            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
//                    .setRootUrl("https://final2-1197.appspot.com/_ah/api/");



            myApiService = builder.build();
        }


            this.context = contexts[0];


            try {
                Log.i("AsyncTaskOld", "doInBackground-call service");
                return myApiService.sayHi().execute().getData();
            } catch (IOException e) {
                Log.i("AsyncTaskOld", "doInBackground-exception");
                return "error:"+ e.getMessage();
            }
        }



    @Override
  public void onPostExecute(String result) {
        Log.i("AsyncTaskOld", "onPostExecute: result," + result);
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();

        Intent myIntent = new Intent(context, MainActivityLibrary.class);
        myIntent.putExtra("joke", result);
        spinner.setVisibility(View.GONE);
        btnSat.setEnabled(true);
        context.startActivity(myIntent);


    }



}