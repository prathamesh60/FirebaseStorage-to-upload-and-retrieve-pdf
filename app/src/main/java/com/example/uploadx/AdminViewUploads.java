package com.example.uploadx;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;
        import java.util.List;

public class AdminViewUploads extends AppCompatActivity {
    ListView listView;
    DatabaseReference mDatabaseReference;
    List<Uploads> uploadList;
    Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_uploads);
        uploadList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        bt1=(Button) findViewById(R.id.logout);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(AdminViewUploads.this,MainActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Uploads upload=uploadList.get(i);
                String url=upload.getUrl().toString();
                Intent intent = new Intent(getApplicationContext(), PdfViewer.class);
                intent.putExtra("message_key", url);
//                Intent intent=new Intent();
//                intent.setType(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(upload.getUrl()));
                startActivity(intent);
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
}