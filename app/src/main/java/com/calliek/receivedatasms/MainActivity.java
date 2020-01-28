package com.calliek.receivedatasms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        String message = intent.getStringExtra(MESSAGE);

        if (message != null)
        {
            TextView messageTextView = (TextView) findViewById(R.id.textMessage);
            messageTextView.setText(message);
        }
    }
}
