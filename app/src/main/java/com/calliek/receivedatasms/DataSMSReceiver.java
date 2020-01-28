package com.calliek.receivedatasms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by Android Studio.
 * User: CaLLIek
 * Date: 28.01.2020
 * Time: 12:25
 */

public class DataSMSReceiver extends BroadcastReceiver {
    // Based on: https://stackoverflow.com/questions/3757229/how-to-send-and-receive-data-sms-messages
// https://issuetracker.google.com/issues/36906421
    static final boolean DEBUG = true;
    static final String TAG = "DataSMSReceiver",
            ACTION_DATA_SMS = "android.intent.action.DATA_SMS_RECEIVED",
            KEY_PDUS = "pdus";


    @Override
    public void onReceive(Context context, Intent intent)
    {
        Bundle bundle = intent.getExtras();

        String recMsgString = "";
        String fromAddress = "";

        SmsMessage recMsg = null;
        byte[] data = null;

        if (bundle != null)
        {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            for (int i = 0; i < pdus.length; i++)
            {
                recMsg = SmsMessage.createFromPdu((byte[]) pdus[i]);

                try
                {
                    data = recMsg.getUserData();
                }
                catch (Exception e)
                {

                }
                if (data != null)
                {
                    for (int index = 0; index < data.length; ++index)
                    {
                        recMsgString += Character.toString((char) data[index]);
                    }
                    if(DEBUG)Log.e(TAG,recMsgString);
                }

                fromAddress = recMsg.getOriginatingAddress();
            }
        }

        Intent result = new Intent(context, MainActivity.class);
        result.putExtra(MainActivity.MESSAGE, "Received SMS from " + fromAddress + ": " + recMsgString);
        result.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(result);
    }
}

