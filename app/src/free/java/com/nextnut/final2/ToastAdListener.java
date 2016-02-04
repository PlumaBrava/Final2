package com.nextnut.final2;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;

/**
 * Created by perez.juan.jose on 14/01/2016.
 */
public class ToastAdListener extends AdListener {
    private Context mContext;
    private String mErrorReason;


    public ToastAdListener(Context context) {
        super();
        this.mContext=context;

    }

    @Override
    public void onAdClosed() {
        Toast.makeText(mContext,"on Ad Closed",Toast.LENGTH_SHORT).show();
        super.onAdClosed();
    }

    @Override
    public void onAdFailedToLoad(int errorCode) {
        mErrorReason="";
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
        Toast.makeText(mContext,
                String.format("on onAdFailedToLoad (%S)", mErrorReason ),
                Toast.LENGTH_SHORT).show();
        super.onAdFailedToLoad(errorCode);
    }

    public String getmErrorReason() {
        return mErrorReason==null ? "":mErrorReason;
    }

    @Override
    public void onAdLeftApplication() {
        Toast.makeText(mContext,"LeftApplication",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAdOpened() {
        Toast.makeText(mContext,"on AdOpened",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAdLoaded() {
        Toast.makeText(mContext,"on onAdLoaded",Toast.LENGTH_SHORT).show();

    }
}
