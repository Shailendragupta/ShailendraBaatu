package com.baatu.network;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 *
 * @author Shailendra This is a receiver class
 * In this class, we are getting result data from back-end
 *
 */

public class ApiResultReceiver extends ResultReceiver {



    private Receiver mReceiver;

    public ApiResultReceiver(Handler handler) {
        super(handler);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if (mReceiver != null) {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }

    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle resultData);
    }
}
