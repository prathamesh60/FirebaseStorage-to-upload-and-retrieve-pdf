package com.example.uploadx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestUploads extends AppCompatActivity {
    List<Upload> uploadX;
    ListView listView;
    DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_uploads);
        uploadX=new ArrayList<>();
        listView=(ListView)findViewById(R.id.listView);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    uploadX.add(upload);
                }
                Collections.sort(uploadX,new Sort());

                MyListAdaptor adaptor = new MyListAdaptor(getApplicationContext(), R.layout.my_custom_list, uploadX);

                listView.setAdapter(adaptor);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        Upload p=new Upload("firebase","www.pdf.com");
//        uploadX.add(p);
//        MyListAdaptor adaptor = new MyListAdaptor(getApplicationContext(), R.layout.my_custom_list, uploadX);
//        listView.setAdapter(adaptor);

    }
}