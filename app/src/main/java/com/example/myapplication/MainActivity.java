package com.example.myapplication;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    WebView web;
    //int arr[] = {0, 0, 0, 0, 0, 0};

    public class WebAppInterface {
        //Show a toast from svg
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web = (WebView) findViewById(R.id.webview1);

        web.getSettings().setJavaScriptEnabled(true);
        //web.loadUrl("file:///android_res/drawable/humanmaptest3.svg");
        web.addJavascriptInterface(new WebAppInterface(), "Android");

        String svg = loadSvg();
        web.loadData(svg, "image/svg+xml", "utf-8");
    }

    private String loadSvg() {
        try {
            //AssetManager assetFiles = getAssets();
            // MyHtmlFiles is the name of folder from inside our assets folder
            //String[] files = assetFiles.list("");
            //System.out.println(files);
            BufferedReader input = new BufferedReader(new InputStreamReader( getAssets().open("HumanMapTest3.svg")));//redCircle.svg
            StringBuffer buf = new StringBuffer();
            String s = null;
            while ((s = input.readLine()) != null) {
                buf.append(s);
                buf.append('\n');
            }
            input.close();
            return buf.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}