package com.example.Ex1IHC;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sumNumbers(View view) {
        int n1,n2,sum;
        EditText e1 = (EditText)findViewById(R.id.num1);
        EditText e2 = (EditText)findViewById(R.id.num2);
        TextView t1 = (TextView)findViewById(R.id.result);
        n1=Integer.parseInt(e1.getText().toString());
        n2=Integer.parseInt(e2.getText().toString());
        sum = n1 + n2;
        t1.setText(Integer.toString(sum));
    }
}