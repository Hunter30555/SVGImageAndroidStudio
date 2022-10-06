package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    Button switchToNextActivity;
    WebView web;
    //int arr[] = {0, 0, 0, 0, 0, 0};

    public class WebAppInterface {
        //Show a toast to show us if the connection was successfully made
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creates the web view and enables javascript to be used to process the SVG file
        web = findViewById(R.id.webview1);
        web.getSettings().setJavaScriptEnabled(true);
        web.addJavascriptInterface(new WebAppInterface(), "Android");

        String svg = loadSvg();
        web.loadData(svg, "image/svg+xml", "utf-8");


        // Connects next activity button to a variable
        switchToNextActivity = findViewById(R.id.intent1);

        // We could have this call a function in a lambda expression if we have a lot of buttons that do the same thing: leftArm.setOnClickListener(v -> toggle(0, leftArm));
        switchToNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calls the next activity based on its class name
                Intent switchActivityIntent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(switchActivityIntent);
            }
        });
    }

    //Loads the SVG file
    private String loadSvg() {
        try {
            // Reads the SVG file by reading each line individually
            BufferedReader input = new BufferedReader(new InputStreamReader( getAssets().open("HumanMapTest3.svg")));
            StringBuffer buffer = new StringBuffer();
            String s = null;
            while ((s = input.readLine()) != null) {
                buffer.append(s);
                buffer.append('\n');
            }
            input.close();
            return buffer.toString();
        } catch (IOException ex) {
            // Catch any errors and print this out in the web view (we might want this to make the webview invisible)
            ex.printStackTrace();
        }
        return null;
    }
}