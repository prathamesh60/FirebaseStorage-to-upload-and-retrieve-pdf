package com.example.uploadx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URLEncoder;

public class PdfViewer extends AppCompatActivity {
    TextView receiver_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);


        // create the get Intent object
        Intent intent = getIntent();

        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
         String str = intent.getStringExtra("message_key");
//        String str = "https://firebasestorage.googleapis.com/v0/b/uploadx-d08a4.appspot.com/o/uploads%2F1591295424663.pdf?alt=media&token=4f65da7b-69fa-4caf-94d9-3704ed2ed26f";
        // display the string into textView

        WebView webView=findViewById(R.id.webView);
        final ProgressBar progressBar=findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        String finalURL="https://drive.google.com/viewerng/viewer?embedded=true&url="+ Uri.encode(str, "ISO-8859-1");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                getSupportActionBar().setTitle("Loading...");
                if(newProgress==100)
                {
                    progressBar.setVisibility(View.GONE);
                    getSupportActionBar().setTitle(R.string.app_name);
                }

            }
        });
        webView.loadUrl(finalURL);

    }
}