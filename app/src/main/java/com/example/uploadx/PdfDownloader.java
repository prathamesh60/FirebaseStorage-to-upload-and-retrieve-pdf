package com.example.uploadx;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class PdfDownloader {
    private Context context;
    private String url;
    private File file;
    private ProgressDialog progressDialog;


    PdfDownloader(Context context, File file, String url) {

        this.context = context;
        this.file = file;
        this.url = url;
        progressDialog = new ProgressDialog(context);
    }

    public void downloadFiles(){
        new Downloader().execute();
    }

    private class Downloader extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
            //    Looper.prepare();
                URL download_url =  new URL(url);
                HttpURLConnection connection = (HttpURLConnection) download_url.openConnection();
               // connection.setRequestMethod("GET");
                connection.connect();
                Log.i("TAG","Download started");
                FileOutputStream fOutput = new FileOutputStream(file);
                InputStream iStream = connection.getInputStream();
                byte[] buffer = new byte[1024*1024];
                int len = 0;
                while ((len = iStream.read(buffer)) != -1 ){
                    fOutput.write(buffer, 0, len);
                }
                fOutput.close();
                iStream.close();
                Log.i("TAG","Download completed");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.e(TAG, "downloadFile() error" + e.getMessage());
                Log.e(TAG, "downloadFile() error" + e.getStackTrace());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e(TAG, "downloadFile() error" + e.getMessage());
                Log.e(TAG, "downloadFile() error" + e.getStackTrace());
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "downloadFile() error" + e.getMessage());
                Log.e(TAG, "downloadFile() error" + e.getStackTrace());
            }
             return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Downloading File");
            progressDialog.setMessage("Please wait");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String absoluteFilePath = file.getAbsolutePath();
            Uri uri = Uri.parse("content://"+"com.example.uploadx"+"/" + absoluteFilePath);
            //intent.setDataAndType(FileProvider.getUriForFile(context, "com.example.uploadx.provider", file), "application/pdf");
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Intent intentChooser = Intent.createChooser(intent, "Choose Pdf Application");
            context.startActivity(intentChooser);
        }
    }

}
