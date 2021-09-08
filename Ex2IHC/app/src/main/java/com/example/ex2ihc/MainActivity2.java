package com.example.ex2ihc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView receiver_msg = (TextView) findViewById(R.id.received_value_id);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
        receiver_msg.setText(str);
    }
}