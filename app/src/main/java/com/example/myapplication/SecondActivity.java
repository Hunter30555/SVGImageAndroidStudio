package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;


public class SecondActivity extends AppCompatActivity {

    Button switchToLastActivity;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Connects next activity button to a variable
        switchToLastActivity = findViewById(R.id.intent1);

        // We could have this call a function in a lambda expression if we have a lot of buttons that do the same thing: leftArm.setOnClickListener(v -> toggle(0, leftArm));
        switchToLastActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calls the next activity based on its class name
                Intent switchActivityIntent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(switchActivityIntent);
            }
        });
    }
}