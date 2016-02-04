package com.nextnut.final2;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.perez.juan.jose.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import com.nextnut.mylibrary.MainActivityLibrary;


import java.io.IOException;



/**
 * Created by perez.juan.jose on 21/01/2016.
 */

public class EndpointsAsyncTaskold extends AsyncTask<Pair<Context, String>,Void, String> {

    private static MyApi myApiService = null;
    private Context context;
    private ProgressBar spinner;

    public EndpointsAsyncTaskold(ProgressBar spinner) {
        this.spinner = spinner;
    }



    @Override
    protected void onPreExecute() {
         Log.i("AsyncTaskOld", "onPreExecute");
      //  spinner.setVisibility(View.VISIBLE);
        super.onPreExecute();




    }

    @Override
    public String doInBackground(Pair<Context, String>... params) {


        Log.i("AsyncTaskOld", "doInBackground");
        if (myApiService == null) {  // Only do this once
//            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
//                    new AndroidJsonFactory(), null)
//                    // options for running against local devappserver
//                    // - 10.0.2.2 is localhost's IP address in Android emulator
//                    // - turn off compression when running against local devappserver
//                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
//                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                        @Override
//                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                            abstractGoogleClientRequest.setDisableGZipContent(true);
//                        }
//                    });


            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://final2-1197.appspot.com/_ah/api/");


            // end options for devappserver


            myApiService = builder.build();
        }


            context = params[0].first;
            String name = params[0].second;

            try {
                Log.i("AsyncTaskOld", "doInBackground-call service");
                return myApiService.sayHi("j").execute().getData();
            } catch (IOException e) {
                Log.i("AsyncTaskOld", "doInBackground-exception");
                return e.getMessage();
            }
        }



    @Override
  public void onPostExecute(String result) {
        Log.i("AsyncTaskOld", "onPostExecute: result," + result);
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        spinner.setVisibility(View.GONE);

        Intent myIntent = new Intent(context, MainActivityLibrary.class);
        myIntent.putExtra("joke",result);
        context.startActivity(myIntent);


    }



}