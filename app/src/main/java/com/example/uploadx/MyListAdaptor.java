package com.example.uploadx;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class MyListAdaptor extends ArrayAdapter<Upload> {
    List<Upload> uploadX;
    Context context;
    int resource;

    public MyListAdaptor(Context context,int resource,List<Upload> uploadX){
        super(context, resource, uploadX);
        this.context=context;
        this.resource=resource;
        this.uploadX=uploadX;


    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.my_custom_list,null);

        TextView textView=view.findViewById(R.id.filename);

        final Upload obj=uploadX.get(position);
        textView.setText(obj.getName());
        final String url=obj.getUrl();
        view.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mDatabaseReference;
                mDatabaseReference = FirebaseDatabase.getInstance().getReference();
                Query deletequery=mDatabaseReference.child("uploads").orderByChild("name").equalTo(obj.getName());
                deletequery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot delSnapshot: dataSnapshot.getChildren()) {
                            delSnapshot.getRef().removeValue();
                        }
                      }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                StorageReference urlRef= FirebaseStorage.getInstance().getReferenceFromUrl(url);
                urlRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Success","deleted file");
                    }
                });

                uploadX.clear();
                notifyDataSetChanged();


            }
        });
        return view;
    }


}

