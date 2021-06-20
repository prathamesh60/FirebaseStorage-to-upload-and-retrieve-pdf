package com.example.uploadx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import java.lang.*;
import java.io.*;
import java.util.*;
import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewUploadsActivity extends AppCompatActivity {
    ListView listView;
    DatabaseReference mDatabaseReference;
    List<Uploads> uploadList;
    Button bt1;
    private static final int STORAGE_REQUEST_CODE=400;
    String storagePermission[];
    String readPermission[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_uploads);
        uploadList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        bt1=(Button) findViewById(R.id.logout);
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        readPermission = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
        if(!checkStoragePermission()){
            requestStoragePermission();
        }
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(ViewUploadsActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Uploads upload=uploadList.get(i);
                String url=upload.getUrl().toString();
                String name=upload.getName().toString();
                HandleDocument(ViewUploadsActivity.this,name,url);
//                Intent intent = new Intent(getApplicationContext(), PdfViewer.class);
//                intent.putExtra("message_key", url);
//                startActivity(intent);
            }
        });

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("uploads");

       //retrieving upload data from firebase database
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Uploads upload = postSnapshot.getValue(Uploads.class);
                    uploadList.add(upload);

                }
                Collections.sort(uploadList,new SortByName());
                String[] uploads = new String[uploadList.size()];

                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = uploadList.get(i).getName();
                }

                //displaying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this,storagePermission,STORAGE_REQUEST_CODE);
    }

    private boolean checkStoragePermission() {
        boolean result= ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result;
    }
    private void readStoragePermission() {
        ActivityCompat.requestPermissions(this,readPermission,STORAGE_REQUEST_CODE);
    }
    private boolean checkReadPermission() {
        boolean result= ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result;
    }


    private void HandleDocument(Context context, String name, String url) {

        File directory=new File(Environment.getExternalStorageDirectory().getPath()+"/"+"Raymond Documents");
        if(!directory.exists())
        {
            directory.mkdir();
            Log.i("TAG","Directory getting created");
        }
        Log.i("TAG",url);
        int index=isSubstring(".pdf",url);
        int count=0;
        String str="";

        for(int i=index-1;count<13 && i>=0;i--)
        {
           // str=str + "" + url.charAt(i);
            str= url.charAt(i)+""+str;
            count++;
        }
        File file=new File(directory,str+".pdf");
        if(!file.exists())
        {
            try{
                file.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            Log.i("TAG","Running");
            PdfDownloader downloader =new PdfDownloader(context,file,url);
            downloader.downloadFiles();

        }
        else
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            String absoluteFilePath = file.getAbsolutePath();
            String mimeType = "application/pdf";
            Uri uri = Uri.parse("content://"+"com.example.uploadx"+"/" + absoluteFilePath);
            intent.setDataAndType(uri, mimeType);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Intent intentChooser = Intent.createChooser(intent, "Choose Pdf Application");
            startActivity(intentChooser);
//            intent.setDataAndType(FileProvider.getUriForFile(context,"com.example.uploadx.provider",file),"application/pdf");
//            context.startActivity(intent);




        }

    }
    static int isSubstring(
            String s1, String s2)
    {
        int M = s1.length();
        int N = s2.length();

        /* A loop to slide pat[] one by one */
        for (int i = 0; i <= N - M; i++) {
            int j;

            /* For current index i, check for
            pattern match */
            for (j = 0; j < M; j++)
                if (s2.charAt(i + j)
                        != s1.charAt(j))
                    break;

            if (j == M)
                return i;
        }

        return -1;
    }
}